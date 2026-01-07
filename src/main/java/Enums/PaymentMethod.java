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
    unknownFallback = "CARD",
    serializeAsObject = false,
    aliases = {}
)
@EnumAliases({
    @EnumAlias(value = "CASH", aliases = {"cash", "currency", "physical_money", "notes_coins", "paper_money"}),
    @EnumAlias(value = "CARD", aliases = {"card", "credit_card", "debit_card", "plastic", "electronic_payment", "digital_payment"})
})
@NullableEnumValue(nullEquivalent = "CARD")
public enum PaymentMethod implements JsonEnum {
    CASH("Cash payment", "cash"),
    CARD("Card payment", "card");
    
    private final String description;
    private final String jsonValue;
    
    PaymentMethod(String description, String jsonValue) {
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
    public boolean isElectronic() {
        return this == CARD;
    }
    
    public boolean isPhysical() {
        return this == CASH;
    }
    
    public boolean requiresReceipt() {
        return this == CARD; // Card payments always need receipts
    }
    
    public boolean supportsRefund() {
        return this == CARD; // Easier to refund card payments
    }
    
    // Factory methods
    public static PaymentMethod fromString(String method) {
        if (method == null) return CARD;
        
        String normalized = method.trim().toLowerCase();
        switch (normalized) {
            case "cash": case "physical": case "currency":
                return CASH;
            default:
                return CARD; // Default to card for safety
        }
    }
    
    public static PaymentMethod fromIsElectronic(boolean isElectronic) {
        return isElectronic ? CARD : CASH;
    }
}
