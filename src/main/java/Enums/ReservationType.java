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
    unknownFallback = "SLOTTED",
    serializeAsObject = false,
    aliases = {}
)
@EnumAliases({
    @EnumAlias(value = "CLOSED", aliases = {"closed", "unavailable", "no_reservations", "blocked", "shutdown"}),
    @EnumAlias(value = "DAILY", aliases = {"daily", "full_day", "whole_day", "24_hours", "entire_day"}),
    @EnumAlias(value = "HALF_DAY", aliases = {"half_day", "halfday", "morning_afternoon", "am_pm", "partial_day"}),
    @EnumAlias(value = "SLOTTED", aliases = {"slotted", "time_slots", "appointments", "scheduled", "time_blocked"})
})
@NullableEnumValue(nullEquivalent = "SLOTTED")
public enum ReservationType implements JsonEnum {
    CLOSED("Closed - No reservations", "closed"),
    DAILY("Daily reservation", "daily"),
    HALF_DAY("Half-day reservation", "half_day"),
    SLOTTED("Time-slotted reservations", "slotted");
    
    private final String description;
    private final String jsonValue;
    
    ReservationType(String description, String jsonValue) {
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
    public boolean isAvailable() {
        return this != CLOSED;
    }
    
    public boolean requiresTimeSlots() {
        return this == SLOTTED;
    }
    
    public boolean isFullDay() {
        return this == DAILY;
    }
    
    public boolean isPartialDay() {
        return this == HALF_DAY;
    }
    
    public int getMaxSlotsPerDay() {
        switch (this) {
            case DAILY: return 1;
            case HALF_DAY: return 2;
            case SLOTTED: return 24; // Assuming hourly slots
            default: return 0;
        }
    }
    
    // Factory methods
    public static ReservationType fromAvailability(boolean isAvailable) {
        return isAvailable ? SLOTTED : CLOSED;
    }
    
    public static ReservationType fromString(String type) {
        if (type == null) return SLOTTED;
        
        String normalized = type.trim().toLowerCase();
        switch (normalized) {
            case "closed": case "unavailable": case "blocked":
                return CLOSED;
            case "daily": case "full_day": case "whole_day":
                return DAILY;
            case "half_day": case "halfday": case "partial":
                return HALF_DAY;
            default:
                return SLOTTED;
        }
    }
    
    // Scheduling logic
    public boolean supportsMultipleReservations() {
        return this == SLOTTED;
    }
    
    public boolean isFlexibleTiming() {
        return this == DAILY || this == HALF_DAY;
    }
    
    public boolean requiresSpecificDuration() {
        return this != CLOSED;
    }
}