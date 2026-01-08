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
import java.time.Duration;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

@JsonEnumConfig(
    caseSensitive = false,
    strict = true,
    unknownFallback = "HOURLY",
    serializeAsObject = false,
    aliases = {}
)
@EnumAliases({
    @EnumAlias(value = "CLOSED", aliases = {"closed", "unavailable", "no_reservations", "blocked", "shutdown"}),
    @EnumAlias(value = "DAILY", aliases = {"daily", "full_day", "whole_day", "24_hours", "entire_day"}),
    @EnumAlias(value = "HALF_DAY", aliases = {"half_day", "halfday", "morning_afternoon", "am_pm", "partial_day"}),
    @EnumAlias(value = "HOURLY", aliases = {"hourly", "1_hour", "60_min", "per_hour"}),
    @EnumAlias(value = "HALF_HOUR", aliases = {"half_hour", "30_min", "30min", "halfhour", "30_minutes"}),
    @EnumAlias(value = "QUARTER_HOUR", aliases = {"quarter_hour", "15_min", "15min", "quarterhour", "15_minutes"}),
    @EnumAlias(value = "TWO_HOUR", aliases = {"two_hour", "2_hour", "120_min", "2hr", "120_minutes"}),
    @EnumAlias(value = "THREE_HOUR", aliases = {"three_hour", "3_hour", "180_min", "3hr", "180_minutes"})
})
@NullableEnumValue(nullEquivalent = "HOURLY")
public enum ReservationType implements JsonEnum {
    CLOSED("Closed - No reservations", "closed", 0, 0),
    DAILY("Daily reservation", "daily", 1, 1440), // 24 hours in minutes
    HALF_DAY("Half-day reservation", "half_day", 2, 720), // 12 hours in minutes
    HOURLY("Hourly reservations", "hourly", 24, 60),
    HALF_HOUR("30-minute reservations", "half_hour", 48, 30),
    QUARTER_HOUR("15-minute reservations", "quarter_hour", 96, 15),
    TWO_HOUR("2-hour reservations", "two_hour", 12, 120),
    THREE_HOUR("3-hour reservations", "three_hour", 8, 180);
    
    private final String description;
    private final String jsonValue;
    private final int maxSlotsPerDay;
    private final int slotDurationMinutes;
    
    ReservationType(String description, String jsonValue, int maxSlotsPerDay, int slotDurationMinutes) {
        this.description = description;
        this.jsonValue = jsonValue;
        this.maxSlotsPerDay = maxSlotsPerDay;
        this.slotDurationMinutes = slotDurationMinutes;
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
        // All except CLOSED, DAILY, and HALF_DAY require specific time slots
        return this != CLOSED && this != DAILY && this != HALF_DAY;
    }
    
    public boolean isFlexibleTiming() {
        // DAILY and HALF_DAY have flexible timing within their periods
        return this == DAILY || this == HALF_DAY;
    }
    
    public boolean isFixedDuration() {
        // These have fixed time slot durations
        return requiresTimeSlots();
    }
    
    public int getMaxSlotsPerDay() {
        return maxSlotsPerDay;
    }
    
    public int getSlotDurationMinutes() {
        return slotDurationMinutes;
    }
    
    public Duration getSlotDuration() {
        return Duration.ofMinutes(slotDurationMinutes);
    }
    
    // Factory methods
    public static ReservationType fromAvailability(boolean isAvailable) {
        return isAvailable ? HOURLY : CLOSED;
    }
    
    public static ReservationType fromDurationMinutes(int minutes) {
        switch (minutes) {
            case 15: return QUARTER_HOUR;
            case 30: return HALF_HOUR;
            case 60: return HOURLY;
            case 120: return TWO_HOUR;
            case 180: return THREE_HOUR;
            case 720: return HALF_DAY;
            case 1440: return DAILY;
            default: return HOURLY; // Default to hourly
        }
    }
    
    public static ReservationType fromDuration(Duration duration) {
        return fromDurationMinutes((int) duration.toMinutes());
    }
    
    public static ReservationType fromString(String type) {
        if (type == null) return HOURLY;
        
        String normalized = type.trim().toLowerCase();
        switch (normalized) {
            case "closed": case "unavailable": case "blocked":
                return CLOSED;
            case "daily": case "full_day": case "whole_day":
                return DAILY;
            case "half_day": case "halfday": case "partial":
                return HALF_DAY;
            case "hourly": case "1_hour": case "60_min":
                return HOURLY;
            case "half_hour": case "30_min": case "30min":
                return HALF_HOUR;
            case "quarter_hour": case "15_min": case "15min":
                return QUARTER_HOUR;
            case "two_hour": case "2_hour": case "120_min":
                return TWO_HOUR;
            case "three_hour": case "3_hour": case "180_min":
                return THREE_HOUR;
            default:
                return HOURLY;
        }
    }
    
    // Time calculation methods
    public LocalTime calculateEndTime(LocalTime startTime) {
        return startTime.plusMinutes(slotDurationMinutes);
    }
    
    public boolean isValidStartTime(LocalTime startTime) {
        if (!requiresTimeSlots()) return true;
        
        // For fixed slots, start time should align with slot boundaries
        int minutesSinceMidnight = startTime.getHour() * 60 + startTime.getMinute();
        return minutesSinceMidnight % slotDurationMinutes == 0;
    }
    
    public List<LocalTime> generateAllStartTimes() {
        if (!requiresTimeSlots()) {
            return List.of(LocalTime.of(0, 0));
        }
        
        List<LocalTime> startTimes = new ArrayList<>();
        for (int i = 0; i < 1440; i += slotDurationMinutes) {
            startTimes.add(LocalTime.of(i / 60, i % 60));
        }
        return startTimes;
    }
    
    public int calculateSlotIndex(LocalTime time) {
        if (!requiresTimeSlots()) return 0;
        
        int minutesSinceMidnight = time.getHour() * 60 + time.getMinute();
        return minutesSinceMidnight / slotDurationMinutes;
    }
    
    // Convenience methods for common durations
    public static ReservationType hourly() { return HOURLY; }
    public static ReservationType halfHour() { return HALF_HOUR; }
    public static ReservationType quarterHour() { return QUARTER_HOUR; }
    public static ReservationType twoHour() { return TWO_HOUR; }
    public static ReservationType threeHour() { return THREE_HOUR; }
    
    // Validation
    public boolean isValidForAppointmentDuration(Duration appointmentDuration) {
        if (this == CLOSED) return false;
        if (this == DAILY || this == HALF_DAY) return true;
        
        // For fixed slots, appointment duration must fit within slot duration
        return !appointmentDuration.minusMinutes(slotDurationMinutes).isNegative();
    }
    
    @Override
    public String toString() {
        return String.format("%s (%d minutes, %d slots/day)", 
            description, slotDurationMinutes, maxSlotsPerDay);
    }
}