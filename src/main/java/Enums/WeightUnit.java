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
    unknownFallback = "MG",
    serializeAsObject = false,
    aliases = {}
)
@EnumAliases({
    @EnumAlias(value = "MG", aliases = {"mg", "milligram", "milligrams", "milli", "milligramme"}),
    @EnumAlias(value = "MCG", aliases = {"mcg", "microgram", "micrograms", "micro", "μg", "ug"}),
    @EnumAlias(value = "ML", aliases = {"ml", "milliliter", "milliliters", "millilitre", "cc", "cm3"}),
    @EnumAlias(value = "IU", aliases = {"iu", "international_unit", "international_units", "u", "unit"}),
    @EnumAlias(value = "TABLET", aliases = {"tablet", "tablets", "tab", "tabs", "pill", "pills"})
})
@NullableEnumValue(nullEquivalent = "MG")
public enum WeightUnit implements JsonEnum {
    MG("Milligram", "mg", true, 0.001),
    MCG("Microgram", "mcg", true, 0.000001),
    ML("Milliliter", "ml", false, 0.001),
    IU("International Units", "iu", false, 1.0), // Cannot convert
    TABLET("Tablet(s)", "tablet", false, 1.0);
    
    private final String label;
    private final String jsonValue;
    private final boolean isWeight;
    private final double gramsEquivalent;
    
    WeightUnit(String label, String jsonValue, boolean isWeight, double gramsEquivalent) {
        this.label = label;
        this.jsonValue = jsonValue;
        this.isWeight = isWeight;
        this.gramsEquivalent = gramsEquivalent;
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
    
    // Enhanced methods
    public boolean isWeight() {
        return isWeight;
    }
    
    public boolean isVolume() {
        return this == ML;
    }
    
    public boolean isUnit() {
        return this == IU;
    }
    
    public boolean isCountable() {
        return this == TABLET;
    }
    
    public double getGramsEquivalent() {
        return gramsEquivalent;
    }
    
    // Conversion methods
    public boolean canConvertTo(WeightUnit target) {
        if (this == target) return true;
        if (this.isWeight() && target.isWeight()) return true;
        return false;
    }
    
    public double convertTo(WeightUnit target, double value) {
        if (this == target) return value;
        if (!canConvertTo(target)) {
            throw new UnsupportedOperationException(
                String.format("Cannot convert from %s to %s", this, target)
            );
        }
        return (value * this.gramsEquivalent) / target.gramsEquivalent;
    }
    
    // Factory methods
    public static WeightUnit fromString(String unit) {
        if (unit == null) return MG;
        
        String normalized = unit.trim().toLowerCase();
        switch (normalized) {
            case "mcg": case "μg": case "ug": case "microgram":
                return MCG;
            case "ml": case "milliliter": case "cc": case "cm3":
                return ML;
            case "iu": case "international_unit": case "unit":
                return IU;
            case "tablet": case "tab": case "pill": case "tablets":
                return TABLET;
            default:
                return MG; // Default to mg
        }
    }
    
    // Medical/pharmaceutical methods
    public boolean isMetricWeight() {
        return this == MG || this == MCG;
    }
    
    public boolean requiresDecimalPrecision() {
        return this == MCG;
    }
    
    public String getAbbreviation() {
        return jsonValue;
    }
    
    // Safety checks
    public boolean isValidForDosage() {
        return this != TABLET || this != IU; // Tablets and IUs are valid but special
    }
}
