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
    unknownFallback = "PER_DAY",
    serializeAsObject = false,
    aliases = {}
)
@EnumAliases({
    @EnumAlias(value = "PER_HOUR", aliases = {"hourly", "per_hour", "every_hour", "hour", "h"}),
    @EnumAlias(value = "PER_DAY", aliases = {"daily", "per_day", "every_day", "day", "d", "qd"}),
    @EnumAlias(value = "PER_WEEK", aliases = {"weekly", "per_week", "every_week", "week", "w", "qw"}),
    @EnumAlias(value = "PER_MONTH", aliases = {"monthly", "per_month", "every_month", "month", "m", "qm"})
})
@NullableEnumValue(nullEquivalent = "PER_DAY")
public enum PeriodUnit implements JsonEnum {
    PER_HOUR("Per hour", "per_hour", 1.0/24.0),
    PER_DAY("Per day", "per_day", 1.0),
    PER_WEEK("Per week", "per_week", 7.0),
    PER_MONTH("Per month", "per_month", 30.44); // Average days per month
    
    private final String description;
    private final String jsonValue;
    private final double daysEquivalent;
    
    PeriodUnit(String description, String jsonValue, double daysEquivalent) {
        this.description = description;
        this.jsonValue = jsonValue;
        this.daysEquivalent = daysEquivalent;
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
    public double getDaysEquivalent() {
        return daysEquivalent;
    }
    
    public boolean isShorterThan(PeriodUnit other) {
        return this.daysEquivalent < other.daysEquivalent;
    }
    
    public boolean isLongerThan(PeriodUnit other) {
        return this.daysEquivalent > other.daysEquivalent;
    }
    
    // Conversion methods
    public double convertTo(PeriodUnit targetUnit, double value) {
        if (this == targetUnit) return value;
        return (value * this.daysEquivalent) / targetUnit.daysEquivalent;
    }
    
    // Factory methods
    public static PeriodUnit fromString(String unit) {
        if (unit == null) return PER_DAY;
        
        String normalized = unit.trim().toLowerCase();
        switch (normalized) {
            case "hour": case "hourly": case "per_hour": case "h":
                return PER_HOUR;
            case "week": case "weekly": case "per_week": case "w":
                return PER_WEEK;
            case "month": case "monthly": case "per_month": case "m":
                return PER_MONTH;
            default:
                return PER_DAY;
        }
    }
    
    public static PeriodUnit fromDays(double days) {
        if (days <= 1.0) return PER_DAY;
        if (days <= 7.0) return PER_WEEK;
        return PER_MONTH;
    }
    
    // Medical abbreviations
    public String getMedicalAbbreviation() {
        switch (this) {
            case PER_HOUR: return "q1h";
            case PER_DAY: return "qd";
            case PER_WEEK: return "qw";
            case PER_MONTH: return "qm";
            default: return "";
        }
    }
}