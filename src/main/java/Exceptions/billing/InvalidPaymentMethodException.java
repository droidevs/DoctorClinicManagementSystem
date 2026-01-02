/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.billing;

import Exceptions.BusinessException;

public class InvalidPaymentMethodException extends BusinessException {
    public InvalidPaymentMethodException(String method) {
        super("Invalid payment method: " + method);
    }
}
