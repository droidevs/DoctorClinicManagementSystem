/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Loggings.business;

import Loggings.business.context.BusinessLogContext;
import java.math.BigDecimal;
import java.util.UUID;

public interface BillingLogger {

    void billCreated(
        BusinessLogContext context,
        UUID billId,
        BigDecimal amount
    );

    void billPaid(
        BusinessLogContext context,
        UUID billId,
        String paymentMethod
    );
}
