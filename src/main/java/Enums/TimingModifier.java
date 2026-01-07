/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enums;

import Jsons.EnumAlias;
import Jsons.EnumAliases;
import Jsons.JsonEnum;
import Jsons.JsonEnumConfig;
import Jsons.NullableEnumValue;

@JsonEnumConfig(
    caseSensitive = false,
    strict = true,
    unknownFallback = "NONE",
    serializeAsObject = false,
    aliases = {}
)
@EnumAliases({
    @EnumAlias(value = "BEFORE_MEALS", aliases = {"before_meals", "ac", "ante_cibum", "pre_meal", "empty_stomach"}),
    @EnumAlias(value = "AFTER_MEALS", aliases = {"after_meals", "pc", "post_cibum", "post_meal", "with_food"}),
    @EnumAlias(value = "BEDTIME", aliases = {"bedtime", "hs", "hora_somni", "at_bedtime", "night"}),
    @EnumAlias(value = "MORNING", aliases = {"morning", "am", "ante_meridiem", "morning_time", "upon_waking"}),
    @EnumAlias(value = "NONE", aliases = {"none", "unspecified", "any_time", "no_timing", "as_directed"})
})
@NullableEnumValue(nullEquivalent = "NONE")
public enum TimingModifier implements JsonEnum {
    BEFORE_MEALS("Before meals", "before_meals", "AC", true),
    AFTER_MEALS("After meals", "after_meals", "PC", true),
    BEDTIME("At bedtime", "bedtime", "HS", false),
    MORNING("In the morning", "morning", "AM", false),
    NONE("No specific timing", "none", "", false);
    
    private final String description;
    private final String jsonValue;
    private final String medicalCode;
    private final boolean isMealRelated;
    
    TimingModifier(String description, String jsonValue, String medicalCode, boolean isMealRelated) {
        this.description = description;
        this.jsonValue = jsonValue;
        this.medicalCode = medicalCode;
        this.isMealRelated = isMealRelated;
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
    
    // Original method preserved
    public String getMedicalCode() {
        return medicalCode;
    }
    
    // Enhanced methods
    public boolean isMealRelated() {
        return isMealRelated;
    }
    
    public boolean hasSpecificTiming() {
        return this != NONE;
    }
    
    public boolean isTimeOfDaySpecific() {
        return this == MORNING || this == BEDTIME;
    }
    
    // Factory methods
    public static TimingModifier fromMedicalCode(String code) {
        if (code == null || code.trim().isEmpty()) return NONE;
        
        String normalized = code.trim().toUpperCase();
        for (TimingModifier modifier : values()) {
            if (modifier.medicalCode.equals(normalized)) {
                return modifier;
            }
        }
        return NONE;
    }
    
    public static TimingModifier fromString(String modifier) {
        if (modifier == null) return NONE;
        
        String normalized = modifier.trim().toLowerCase();
        switch (normalized) {
            case "ac": case "before_meals": case "ante_cibum": case "empty_stomach":
                return BEFORE_MEALS;
            case "pc": case "after_meals": case "post_cibum": case "with_food":
                return AFTER_MEALS;
            case "hs": case "bedtime": case "hora_somni": case "at_bedtime":
                return BEDTIME;
            case "am": case "morning": case "ante_meridiem": case "morning_time":
                return MORNING;
            default:
                return NONE;
        }
    }
    
    // Medical logic
    public boolean requiresFoodConsideration() {
        return isMealRelated;
    }
    
    public String getInstructions() {
        switch (this) {
            case BEFORE_MEALS: return "Take 30-60 minutes before meals";
            case AFTER_MEALS: return "Take immediately after meals";
            case BEDTIME: return "Take at bedtime";
            case MORNING: return "Take in the morning";
            default: return "Take as directed";
        }
    }
}