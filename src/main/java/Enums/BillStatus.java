/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Enum.java to edit this template
 */
package Enums;

import Jsons.BooleanEnum;
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
    @EnumAlias(value = "PAID", aliases = {"settled", "cleared", "discharged", "paid_in_full"}),
    @EnumAlias(value = "UNPAID", aliases = {"outstanding", "due", "pending_payment", "unsettled"}),
    @EnumAlias(value = "UNKNOWN", aliases = {"undefined", "unspecified", "pending_review"})
})
@NullableEnumValue(nullEquivalent = "UNKNOWN")
public enum BillStatus implements JsonEnum, BooleanEnum {
    PAID("Bill has been paid", "paid", true),
    UNPAID("Bill is pending payment", "unpaid", false),
    UNKNOWN("Unknown payment status", "unknown", false);
    
    private final String description;
    private final String jsonValue;
    private final boolean paid;
    
    BillStatus(String description, String jsonValue, boolean paid) {
        this.description = description;
        this.jsonValue = jsonValue;
        this.paid = paid;
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
    
    // BooleanEnum implementation
    @Override
    public boolean getBooleanValue() {
        return paid;
    }
    
    @Override
    public String getBooleanRepresentation() {
        return paid ? "paid" : "unpaid";
    }
    
    // Helper methods
    public boolean isPaid() {
        return paid;
    }
    
    public boolean requiresPayment() {
        return !paid;
    }
    
    public BillStatus opposite() {
        return this == PAID ? UNPAID : PAID;
    }
    
    // Factory methods for boolean conversion
    public static BillStatus fromBoolean(boolean isPaid) {
        return isPaid ? PAID : UNPAID;
    }
    
    public static BillStatus fromPaymentStatus(String status) {
        if (status == null) return UNKNOWN;
        
        String normalized = status.trim().toLowerCase();
        switch (normalized) {
            case "true": case "yes": case "1": case "paid": case "settled":
                return PAID;
            case "false": case "no": case "0": case "unpaid": case "due":
                return UNPAID;
            default:
                return UNKNOWN;
        }
    }
}
