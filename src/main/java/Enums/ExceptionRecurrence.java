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
    unknownFallback = "ONE_TIME",
    serializeAsObject = false,
    aliases = {}
)
@EnumAliases({
    @EnumAlias(value = "ONE_TIME", aliases = {"single", "once", "non_recurring", "one_off"}),
    @EnumAlias(value = "MONTHLY", aliases = {"month", "every_month", "per_month", "monthly_recurring"}),
    @EnumAlias(value = "YEARLY", aliases = {"year", "annual", "annually", "yearly_recurring", "per_year"})
})
@NullableEnumValue(nullEquivalent = "ONE_TIME")
public enum ExceptionRecurrence implements JsonEnum {
    ONE_TIME("One-time exception", "one_time", 1, "P1D"),
    MONTHLY("Monthly recurrence", "monthly", 30, "P1M"),
    YEARLY("Yearly recurrence", "yearly", 365, "P1Y");
    
    private final String description;
    private final String jsonValue;
    private final int approximateDays;
    private final String isoDuration;
    
    ExceptionRecurrence(String description, String jsonValue, int approximateDays, String isoDuration) {
        this.description = description;
        this.jsonValue = jsonValue;
        this.approximateDays = approximateDays;
        this.isoDuration = isoDuration;
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
    public boolean isRecurring() {
        return this != ONE_TIME;
    }
    
    public boolean isOneTime() {
        return this == ONE_TIME;
    }
    
    public int getApproximateDays() {
        return approximateDays;
    }
    
    public String getIsoDuration() {
        return isoDuration;
    }
    
    // Factory methods
    public static ExceptionRecurrence fromFrequency(String frequency) {
        if (frequency == null) return ONE_TIME;
        
        String normalized = frequency.trim().toLowerCase();
        switch (normalized) {
            case "month": case "monthly": case "every_month": case "per_month":
                return MONTHLY;
            case "year": case "yearly": case "annual": case "annually": case "per_year":
                return YEARLY;
            default:
                return ONE_TIME;
        }
    }
    
    public static ExceptionRecurrence fromDaysInterval(int days) {
        if (days >= 28 && days <= 31) {
            return MONTHLY;
        } else if (days >= 360 && days <= 370) {
            return YEARLY;
        } else {
            return ONE_TIME;
        }
    }
}