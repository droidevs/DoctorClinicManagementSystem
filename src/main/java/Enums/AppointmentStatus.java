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
    unknownFallback = "UNKNOWN",
    serializeAsObject = false,
    aliases = {}
)
@EnumAliases({
    @EnumAlias(value = "SCHEDULED", aliases = {"booked", "planned", "upcoming", "future"}),
    @EnumAlias(value = "CANCELLED", aliases = {"canceled", "aborted", "terminated", "voided"}),
    @EnumAlias(value = "COMPLETED", aliases = {"done", "finished", "fulfilled", "concluded"}),
    @EnumAlias(value = "UNKNOWN", aliases = {"undefined", "unspecified", "pending"})
})
@NullableEnumValue(nullEquivalent = "UNKNOWN")
public enum AppointmentStatus implements JsonEnum {
    SCHEDULED("Scheduled appointment", "scheduled"),
    CANCELLED("Cancelled appointment", "cancelled"),
    COMPLETED("Completed appointment", "completed"),
    UNKNOWN("Unknown appointment status", "unknown");
    
    private final String description;
    private final String jsonValue;
    
    AppointmentStatus(String description, String jsonValue) {
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
    
}