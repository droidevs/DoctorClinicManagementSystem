/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.prescription;

import Exceptions.BusinessException;


public class PrescriptionNotAllowedException extends BusinessException {
    public PrescriptionNotAllowedException() {
        super("Prescription can only be created for completed appointments");
    }
}
