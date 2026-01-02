/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.patient;

import Exceptions.NotFoundException;
import java.util.UUID;

public class PatientNotFoundException extends NotFoundException {
    public PatientNotFoundException(UUID id) {
        super("Patient not found: " + id);
    }
}
