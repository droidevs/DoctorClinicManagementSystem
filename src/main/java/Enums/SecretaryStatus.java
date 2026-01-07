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
    unknownFallback = "ACTIVE",
    serializeAsObject = false,
    aliases = {}
)
@EnumAliases({
    @EnumAlias(value = "ACTIVE", aliases = {"active", "working", "available", "on_duty", "employed"}),
    @EnumAlias(value = "SUSPENDED", aliases = {"suspended", "inactive", "unavailable", "off_duty", "terminated", "fired"})
})
@NullableEnumValue(nullEquivalent = "ACTIVE")
public enum SecretaryStatus implements JsonEnum {
    ACTIVE("Active secretary", "active"),
    SUSPENDED("Suspended secretary", "suspended");
    
    private final String description;
    private final String jsonValue;
    
    SecretaryStatus(String description, String jsonValue) {
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
    public boolean isActive() {
        return this == ACTIVE;
    }
    
    public boolean isSuspended() {
        return this == SUSPENDED;
    }
    
    public boolean canScheduleAppointments() {
        return this == ACTIVE;
    }
    
    public boolean canAccessSystem() {
        return this == ACTIVE;
    }
    
    // Factory methods
    public static SecretaryStatus fromBoolean(boolean isActive) {
        return isActive ? ACTIVE : SUSPENDED;
    }
    
    public static SecretaryStatus fromString(String status) {
        if (status == null) return ACTIVE;
        
        String normalized = status.trim().toLowerCase();
        switch (normalized) {
            case "suspended": case "inactive": case "terminated": case "fired":
                return SUSPENDED;
            default:
                return ACTIVE;
        }
    }
    
    // HR/Management methods
    public boolean requiresReactivation() {
        return this == SUSPENDED;
    }
    
    public boolean isEligibleForPay() {
        return this == ACTIVE;
    }
    
    public SecretaryStatus toggle() {
        return this == ACTIVE ? SUSPENDED : ACTIVE;
    }
}