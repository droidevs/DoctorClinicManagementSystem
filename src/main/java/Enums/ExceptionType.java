/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enums;

import Jsons.BooleanEnum;
import Jsons.EnumAlias;
import Jsons.EnumAliases;
import Jsons.JsonEnum;
import Jsons.JsonEnumConfig;
import Jsons.NullableEnumValue;

@JsonEnumConfig(
    caseSensitive = false,
    strict = true,
    unknownFallback = "CUSTOM_SLOTS",
    serializeAsObject = false,
    aliases = {}
)
@EnumAliases({
    @EnumAlias(value = "CLOSED", aliases = {"closed", "holiday", "shutdown", "no_service", "unavailable"}),
    @EnumAlias(value = "EMERGENCY", aliases = {"emergency", "urgent", "crisis", "critical", "emergency_only"}),
    @EnumAlias(value = "CUSTOM_SLOTS", aliases = {"custom", "modified", "special_hours", "adjusted", "limited"})
})

@NullableEnumValue(nullEquivalent = "CUSTOM_SLOTS")
public enum ExceptionType implements JsonEnum, BooleanEnum {
    CLOSED("Closed - No services available", "closed", false),
    EMERGENCY("Emergency services only", "emergency", true),
    CUSTOM_SLOTS("Custom time slots", "custom_slots", true);
    
    private final String description;
    private final String jsonValue;
    private final boolean hasServices;
    
    ExceptionType(String description, String jsonValue, boolean hasServices) {
        this.description = description;
        this.jsonValue = jsonValue;
        this.hasServices = hasServices;
    }
    
    // JsonEnum implementation
    @Override
    public String getJsonValue() {
        return jsonValue;
    }
    
    @Override
    public String getDescription() {
        return description;
    }
    
    @Override
    public boolean isDeprecated() {
        return false;
    }
    
    // BooleanEnum implementation (for service availability)
    @Override
    public boolean getBooleanValue() {
        return hasServices;
    }
    
    @Override
    public String getBooleanRepresentation() {
        return hasServices ? "services_available" : "no_services";
    }
    
    // Business methods
    public boolean isOperational() {
        return hasServices;
    }
    
    public boolean isFullyClosed() {
        return this == CLOSED;
    }
    
    public boolean requiresSpecialHandling() {
        return this != CLOSED;
    }
    
    // Factory methods
    public static ExceptionType fromAvailability(boolean servicesAvailable) {
        return servicesAvailable ? CUSTOM_SLOTS : CLOSED;
    }
    
    public static ExceptionType fromString(String type) {
        if (type == null) return CUSTOM_SLOTS;
        
        String normalized = type.trim().toLowerCase();
        switch (normalized) {
            case "emergency": case "urgent": case "critical":
                return EMERGENCY;
            case "closed": case "holiday": case "shutdown":
                return CLOSED;
            default:
                return CUSTOM_SLOTS;
        }
    }
}