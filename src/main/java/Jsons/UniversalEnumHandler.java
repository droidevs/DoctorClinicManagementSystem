/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Jsons;


import jakarta.annotation.PostConstruct;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.inject.Produces;
import jakarta.inject.Inject;
import jakarta.json.bind.Jsonb;
import jakarta.json.bind.JsonbBuilder;
import jakarta.json.bind.JsonbConfig;
import jakarta.json.bind.JsonbException;
import jakarta.json.bind.annotation.JsonbCreator;
import jakarta.json.bind.annotation.JsonbProperty;
import jakarta.json.bind.annotation.JsonbTypeDeserializer;
import jakarta.json.bind.annotation.JsonbTypeSerializer;
import jakarta.json.bind.serializer.*;
import jakarta.json.stream.JsonGenerator;
import jakarta.json.stream.JsonParser;
import jakarta.json.bind.config.PropertyVisibilityStrategy;
import java.io.IOException;
import java.lang.annotation.Annotation;
import java.lang.reflect.*;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;


@ApplicationScoped
public class UniversalEnumHandler 
    implements JsonbSerializer<Enum<?>>, JsonbDeserializer<Enum<?>> {
    
    // Cache for performance
    private final Map<Class<?>, EnumMetadata> metadataCache = new ConcurrentHashMap<>();
    private final Map<Class<?>, Map<String, Enum<?>>> lookupCache = new ConcurrentHashMap<>();
    
    // Custom deserialization logic
    @Override
    public Enum<?> deserialize(JsonParser parser, DeserializationContext ctx, Type rtType) {
        if (!(rtType instanceof Class) || !((Class<?>) rtType).isEnum()) {
            throw new JsonbException("Expected enum type: " + rtType);
        }
        
        Class<Enum<?>> enumClass = (Class<Enum<?>>) rtType;
        JsonParser.Event event = parser.next();
        
        // Handle different JSON input types
        Object value = parseValue(parser, event, enumClass);
        
        return convertToEnum(value, enumClass);
    }
    
    @Override
    public void serialize(Enum<?> obj, JsonGenerator generator, SerializationContext ctx) {
        if (obj == null) {
            generator.writeNull();
            return;
        }
        
        Class<?> enumClass = obj.getClass();
        EnumMetadata metadata = getMetadata(enumClass);
        
        if (metadata.config.serializeAsObject()) {
            serializeAsObject(obj, generator, metadata);
        } else {
            serializeAsString(obj, generator, metadata);
        }
    }
    
    // ========== CORE LOGIC ==========
    
    private Object parseValue(JsonParser parser, JsonParser.Event event, Class<?> enumClass) {
        switch (event) {
            case VALUE_STRING:
                return parser.getString();
            case VALUE_NUMBER:
                // Handle numeric enum values if applicable
                if (parser.isIntegralNumber()) {
                    return parser.getLong();
                }
                return parser.getBigDecimal();
            case VALUE_TRUE:
                return true;
            case VALUE_FALSE:
                return false;
            case START_OBJECT:
                return parseObject(parser, enumClass);
            case VALUE_NULL:
                return null;
            default:
                throw new JsonbException("Unexpected JSON event for enum: " + event);
        }
    }
    
    private Map<String, Object> parseObject(JsonParser parser, Class<?> enumClass) {
        Map<String, Object> map = new HashMap<>();
        while (parser.hasNext()) {
            JsonParser.Event event = parser.next();
            if (event == JsonParser.Event.KEY_NAME) {
                String key = parser.getString();
                parser.next();
                map.put(key, parseValue(parser, event, enumClass));
            } else if (event == JsonParser.Event.END_OBJECT) {
                break;
            }
        }
        return map;
    }
    
    private Enum<?> convertToEnum(Object value, Class<Enum<?>> enumClass) {
        if (value == null) {
            return handleNullValue(enumClass);
        }
        
        EnumMetadata metadata = getMetadata(enumClass);
        Map<String, Enum<?>> lookup = getLookupMap(enumClass, metadata);
        
        // Try different strategies
        Enum<?> result = null;
        
        if (value instanceof String) {
            result = lookup.get(((String) value).toLowerCase());
            
            // Try aliases if direct lookup fails
            if (result == null) {
                result = findByAlias((String) value, metadata.aliases, enumClass);
            }
            
            // Try numeric string
            if (result == null && ((String) value).matches("\\d+")) {
                try {
                    int ordinal = Integer.parseInt((String) value);
                    result = findByOrdinal(ordinal, enumClass);
                } catch (NumberFormatException ignored) {}
            }
        } 
        else if (value instanceof Number) {
            result = findByOrdinal(((Number) value).intValue(), enumClass);
        }
        else if (value instanceof Map) {
            result = findByObject((Map<String, Object>) value, enumClass, metadata);
        }
        else if (value instanceof Boolean) {
            result = findByBoolean((Boolean) value, enumClass);
        }
        
        // Handle unknown values
        if (result == null) {
            result = handleUnknownValue(value, enumClass, metadata);
        }
        
        return result;
    }
    
    // ========== HELPER METHODS ==========
    
    private EnumMetadata getMetadata(Class<?> enumClass) {
        return metadataCache.computeIfAbsent(enumClass, cls -> {
            JsonEnumConfig config = cls.getAnnotation(JsonEnumConfig.class);
            if (config == null) {
                config = new DefaultJsonEnumConfig();
            }
            
            Map<String, String[]> aliases = new HashMap<>();
            if (cls.isAnnotationPresent(EnumAliases.class)) {
                EnumAliases annotation = cls.getAnnotation(EnumAliases.class);
                for (EnumAlias alias : annotation.value()) {
                    aliases.put(alias.value(), alias.aliases());
                }
            }
            
            return new EnumMetadata(config, aliases);
        });
    }
    
    private Map<String, Enum<?>> getLookupMap(Class<Enum<?>> enumClass, EnumMetadata metadata) {
        return lookupCache.computeIfAbsent(enumClass, cls -> {
            Map<String, Enum<?>> map = new HashMap<>();
            Enum<?>[] constants = enumClass.getEnumConstants();
            
            for (Enum<?> constant : constants) {
                // Primary key: enum name (case-insensitive if configured)
                String key = metadata.config.caseSensitive() 
                    ? constant.name() 
                    : constant.name().toLowerCase();
                map.put(key, constant);
                
                // JsonEnum interface support
                if (constant instanceof JsonEnum) {
                    String jsonValue = ((JsonEnum) constant).getJsonValue();
                    if (jsonValue != null && !jsonValue.isEmpty()) {
                        String jsonKey = metadata.config.caseSensitive() 
                            ? jsonValue 
                            : jsonValue.toLowerCase();
                        map.put(jsonKey, constant);
                    }
                }
                
                // @JsonbProperty support
                try {
                    Field field = enumClass.getField(constant.name());
                    JsonbProperty annotation = field.getAnnotation(JsonbProperty.class);
                    if (annotation != null && !annotation.value().isEmpty()) {
                        String propKey = metadata.config.caseSensitive()
                            ? annotation.value()
                            : annotation.value().toLowerCase();
                        map.put(propKey, constant);
                    }
                } catch (NoSuchFieldException ignored) {}
            }
            
            return Collections.unmodifiableMap(map);
        });
    }
    
    private Enum<?> handleNullValue(Class<Enum<?>> enumClass) {
        // Check for @NullableEnumValue annotation
        NullableEnumValue nullable = enumClass.getAnnotation(NullableEnumValue.class);
        if (nullable != null) {
            try {
                return Enum.valueOf((Class) enumClass, nullable.nullEquivalent());
            } catch (IllegalArgumentException e) {
                // fall through
            }
        }
        return null;
    }
    
    private Enum<?> handleUnknownValue(Object value, Class<Enum<?>> enumClass, EnumMetadata metadata) {
        if (!metadata.config.strict()) {
            // Use fallback if configured
            if (!metadata.config.unknownFallback().isEmpty()) {
                try {
                    return Enum.valueOf((Class) enumClass, metadata.config.unknownFallback());
                } catch (IllegalArgumentException ignored) {}
            }
            // Return first enum constant as default
            return enumClass.getEnumConstants()[0];
        }
        
        // Strict mode: throw detailed error
        String allowed = Arrays.stream(enumClass.getEnumConstants())
            .map(this::enumToString)
            .collect(Collectors.joining(", "));
        
        String valueStr = value instanceof String ? "'" + value + "'" : String.valueOf(value);
        throw new JsonbException(
            String.format("Invalid value %s for enum %s. Allowed values: %s",
                valueStr, enumClass.getSimpleName(), allowed)
        );
    }
    
    private String enumToString(Enum<?> enumValue) {
        if (enumValue instanceof JsonEnum) {
            JsonEnum jsonEnum = (JsonEnum) enumValue;
            return String.format("%s (%s)", enumValue.name(), jsonEnum.getJsonValue());
        }
        return enumValue.name();
    }
    
    // ========== SERIALIZATION ==========
    
    private void serializeAsString(Enum<?> obj, JsonGenerator generator, EnumMetadata metadata) {
        if (obj instanceof JsonEnum) {
            generator.write(((JsonEnum) obj).getJsonValue());
        } else {
            generator.write(obj.name());
        }
    }
    
    private void serializeAsObject(Enum<?> obj, JsonGenerator generator, EnumMetadata metadata) {
        generator.writeStartObject();
        generator.write("value", obj.name());
        
        if (obj instanceof JsonEnum) {
            JsonEnum jsonEnum = (JsonEnum) obj;
            generator.write("jsonValue", jsonEnum.getJsonValue());
            generator.write("description", jsonEnum.getDescription());
            generator.write("deprecated", jsonEnum.isDeprecated());
        }
        
        generator.writeEnd();
    }
    
    // ========== FINDER METHODS ==========
    
    private Enum<?> findByAlias(String value, Map<String, String[]> aliasesMap, Class<Enum<?>> enumClass) {
        for (Map.Entry<String, String[]> entry : aliasesMap.entrySet()) {
            for (String alias : entry.getValue()) {
                if (alias.equalsIgnoreCase(value)) {
                    return Enum.valueOf((Class) enumClass, entry.getKey());
                }
            }
        }
        return null;
    }
    
    private Enum<?> findByOrdinal(int ordinal, Class<Enum<?>> enumClass) {
        Enum<?>[] constants = enumClass.getEnumConstants();
        if (ordinal >= 0 && ordinal < constants.length) {
            return constants[ordinal];
        }
        return null;
    }
    
    private Enum<?> findByObject(Map<String, Object> map, Class<Enum<?>> enumClass, EnumMetadata metadata) {
        // Look for "value", "name", "id" keys
        for (String key : Arrays.asList("value", "name", "id")) {
            Object val = map.get(key);
            if (val instanceof String) {
                return convertToEnum(val, enumClass);
            }
        }
        return null;
    }
    
    private Enum<?> findByBoolean(Boolean bool, Class<Enum<?>> enumClass) {
        // Useful for enums like Status { ACTIVE(true), INACTIVE(false) }
        for (Enum<?> constant : enumClass.getEnumConstants()) {
            if (constant instanceof BooleanEnum) {
                if (((BooleanEnum) constant).getBooleanValue() == bool) {
                    return constant;
                }
            }
        }
        return null;
    }
    
    // ========== SUPPORTING CLASSES ==========
    
    private static class EnumMetadata {
        final JsonEnumConfig config;
        final Map<String, String[]> aliases;
        
        EnumMetadata(JsonEnumConfig config, Map<String, String[]> aliases) {
            this.config = config;
            this.aliases = aliases;
        }
    }
    
    // Default config when annotation is not present
    private static class DefaultJsonEnumConfig implements JsonEnumConfig {
        @Override public boolean caseSensitive() { return false; }
        @Override public boolean strict() { return true; }
        @Override public String unknownFallback() { return ""; }
        @Override public boolean serializeAsObject() { return false; }
        @Override public String[] aliases() { return new String[0]; }
        @Override public Class<? extends Annotation> annotationType() { return JsonEnumConfig.class; }
    }
}
