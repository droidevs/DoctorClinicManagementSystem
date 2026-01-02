/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.billing;

import Exceptions.ConflictException;

public class BillAlreadyPaidException extends ConflictException {
    public BillAlreadyPaidException() {
        super("Bill is already paid");
    }
}
