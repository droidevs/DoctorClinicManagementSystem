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
    unknownFallback = "",
    serializeAsObject = false,
    aliases = {}
)
@EnumAliases({
    @EnumAlias(value = "ORAL", aliases = {"by_mouth", "per_os", "po", "mouth"}),
    @EnumAlias(value = "INTRAVENOUS", aliases = {"iv", "intravenous_injection", "iv_push", "vein"}),
    @EnumAlias(value = "TOPICAL", aliases = {"skin", "dermal", "transdermal", "cutaneous", "external"}),
    @EnumAlias(value = "INHALATION", aliases = {"inhale", "breathe_in", "pulmonary", "nebulized", "respiratory"})
})
@NullableEnumValue(nullEquivalent = "UNKNOWN")
public enum AdministrationRoute implements JsonEnum {
    ORAL("By mouth", "oral"),
    INTRAVENOUS("IV", "intravenous"), 
    TOPICAL("Apply to skin", "topical"),
    INHALATION("Inhale", "inhalation"),
    UNKNOWN("Unknown administration route", "unknown");
    
    private final String label;
    private final String jsonValue;
    
    AdministrationRoute(String label, String jsonValue) {
        this.label = label;
        this.jsonValue = jsonValue;
    }
    
    // JsonEnum implementation
    @Override
    public String getJsonValue() {
        return jsonValue;
    }
    
    @Override
    public String getDescription() {
        return label;
    }
    
    @Override
    public boolean isDeprecated() {
        return false;
    }
    
    // Original method preserved
    public String getLabel() {
        return label;
    }
}