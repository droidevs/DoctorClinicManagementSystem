/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.patient;

import Exceptions.ConflictException;

/**
 *
 * @author admin
 */

public class PatientAlreadyExistsException extends ConflictException {
    public PatientAlreadyExistsException() {
        super("Patient profile already exists");
    }
}
