/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Jsons.UniversalEnumHandler;
import Validators.annotations.ValidMonetaryAmount;
import Validators.annotations.ValidUUID;
import jakarta.json.bind.annotation.JsonbTypeSerializer;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import java.math.BigDecimal;
import java.util.UUID;

public record CreateBillRequest(
    
    @ValidUUID
    UUID appointmentId,
    
    @ValidMonetaryAmount
    BigDecimal amount
    
) {}
