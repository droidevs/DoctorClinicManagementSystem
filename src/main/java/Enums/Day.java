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
    unknownFallback = "MONDAY", // Default to Monday for unknown
    serializeAsObject = false,
    aliases = {}
)
@EnumAliases({
    @EnumAlias(value = "MONDAY", aliases = {"mon", "1", "monday", "first_day", "week_start"}),
    @EnumAlias(value = "TUESDAY", aliases = {"tue", "tues", "2", "tuesday"}),
    @EnumAlias(value = "WEDNESDAY", aliases = {"wed", "weds", "3", "wednesday", "hump_day"}),
    @EnumAlias(value = "THURSDAY", aliases = {"thu", "thur", "thurs", "4", "thursday"}),
    @EnumAlias(value = "FRIDAY", aliases = {"fri", "5", "friday", "tgif", "weekend_eve"}),
    @EnumAlias(value = "SATURDAY", aliases = {"sat", "6", "saturday", "weekend"}),
    @EnumAlias(value = "SUNDAY", aliases = {"sun", "7", "0", "sunday", "weekend", "week_end"})
})
@NullableEnumValue(nullEquivalent = "MONDAY")
public enum Day implements JsonEnum {
    MONDAY("Monday", "monday", "MON", 1, false),
    TUESDAY("Tuesday", "tuesday", "TUE", 2, false),
    WEDNESDAY("Wednesday", "wednesday", "WED", 3, false),
    THURSDAY("Thursday", "thursday", "THU", 4, false),
    FRIDAY("Friday", "friday", "FRI", 5, false),
    SATURDAY("Saturday", "saturday", "SAT", 6, true),
    SUNDAY("Sunday", "sunday", "SUN", 7, true);
    
    private final String fullName;
    private final String jsonValue;
    private final String shortCode;
    private final int dayOfWeek; // ISO: Monday=1, Sunday=7
    private final boolean isWeekend;
    
    Day(String fullName, String jsonValue, String shortCode, int dayOfWeek, boolean isWeekend) {
        this.fullName = fullName;
        this.jsonValue = jsonValue;
        this.shortCode = shortCode;
        this.dayOfWeek = dayOfWeek;
        this.isWeekend = isWeekend;
    }
    
    // JsonEnum implementation
    @Override
    public String getJsonValue() {
        return jsonValue;
    }
    
    @Override
    public String getDescription() {
        return fullName + " (Day " + dayOfWeek + ")";
    }
    
    @Override
    public boolean isDeprecated() {
        return false;
    }
    
    // Original methods preserved
    public String getFullName() {
        return fullName;
    }
    
    public String getShortCode() {
        return shortCode;
    }
    
    public int getDayOfWeek() {
        return dayOfWeek;
    }
    
    // Enhanced methods
    public boolean isWeekend() {
        return isWeekend;
    }
    
    public boolean isWeekday() {
        return !isWeekend;
    }
    
    public Day next() {
        int nextOrdinal = (this.ordinal() + 1) % values().length;
        return values()[nextOrdinal];
    }
    
    public Day previous() {
        int prevOrdinal = (this.ordinal() - 1 + values().length) % values().length;
        return values()[prevOrdinal];
    }
    
    // Factory methods
    public static Day fromDayNumber(int dayNumber) {
        // Handle different numbering systems
        int normalized = dayNumber;
        if (dayNumber == 0) normalized = 7; // Sunday as 0
        if (dayNumber < 1 || dayNumber > 7) normalized = ((dayNumber - 1) % 7) + 1;
        
        for (Day day : values()) {
            if (day.dayOfWeek == normalized) {
                return day;
            }
        }
        return MONDAY; // Default fallback
    }
    
    public static Day fromShortCode(String code) {
        if (code == null) return MONDAY;
        
        String normalized = code.trim().toUpperCase();
        for (Day day : values()) {
            if (day.shortCode.equals(normalized)) {
                return day;
            }
        }
        return MONDAY;
    }
    
    public static Day fromFullName(String name) {
        if (name == null) return MONDAY;
        
        String normalized = name.trim().toLowerCase();
        for (Day day : values()) {
            if (day.fullName.toLowerCase().equals(normalized) || 
                day.jsonValue.equals(normalized)) {
                return day;
            }
        }
        return MONDAY;
    }
    
    // Business logic
    public boolean isBusinessDay() {
        return this != SATURDAY && this != SUNDAY;
    }
    
    public int daysUntil(Day target) {
        int diff = target.dayOfWeek - this.dayOfWeek;
        return diff >= 0 ? diff : diff + 7;
    }
}