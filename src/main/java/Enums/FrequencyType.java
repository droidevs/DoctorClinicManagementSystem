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
    unknownFallback = "RECURRING",
    serializeAsObject = false,
    aliases = {}
)
@EnumAliases({
    @EnumAlias(value = "RECURRING", aliases = {"regular", "scheduled", "repeating", "routine", "periodic"}),
    @EnumAlias(value = "STAT", aliases = {"immediate", "urgent", "now", "asap", "statim", "emergency"}),
    @EnumAlias(value = "PRN", aliases = {"as_needed", "pro_re_nata", "when_necessary", "prn_basis", "pm"})
})
@NullableEnumValue(nullEquivalent = "RECURRING")
public enum FrequencyType implements JsonEnum {
    RECURRING("Recurring scheduled frequency", "recurring"),
    STAT("Immediate/stat dose", "stat"),
    PRN("As needed/pro re nata", "prn");
    
    private final String description;
    private final String jsonValue;
    
    FrequencyType(String description, String jsonValue) {
        this.description = description;
        this.jsonValue = jsonValue;
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
    
    // Business methods
    public boolean isScheduled() {
        return this == RECURRING;
    }
    
    public boolean isImmediate() {
        return this == STAT;
    }
    
    public boolean isAsNeeded() {
        return this == PRN;
    }
    
    public boolean requiresTiming() {
        return this == RECURRING;
    }
    
    // Factory methods
    public static FrequencyType fromUrgency(boolean isUrgent) {
        return isUrgent ? STAT : RECURRING;
    }
    
    public static FrequencyType fromScheduleType(String type) {
        if (type == null) return RECURRING;
        
        String normalized = type.trim().toLowerCase();
        switch (normalized) {
            case "stat": case "immediate": case "urgent": case "emergency":
                return STAT;
            case "prn": case "as_needed": case "pro_re_nata": case "when_necessary":
                return PRN;
            default:
                return RECURRING;
        }
    }
    
    // Medical domain logic
    public boolean canHaveMultipleDoses() {
        return this != STAT;
    }
    
    public boolean requiresFrequencyDetails() {
        return this == RECURRING;
    }
    
    public boolean isStandingOrder() {
        return this == RECURRING || this == PRN;
    }
}
