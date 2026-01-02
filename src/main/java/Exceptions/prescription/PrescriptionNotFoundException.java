/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.prescription;

import Exceptions.NotFoundException;
import java.util.UUID;

public class PrescriptionNotFoundException extends NotFoundException {
    public PrescriptionNotFoundException(UUID id) {
        super("Prescription not found: " + id);
    }
}