/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import jakarta.validation.constraints.NotNull;
import java.util.UUID;

/**
 *
 * @author admin
 */
public record PaymentRequest(
        
        @NotNull(message = "Bill ID is required")
        UUID billId,
        
        String paymentMethod
        ) {

}
