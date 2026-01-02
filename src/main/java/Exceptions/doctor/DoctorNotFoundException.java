/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.doctor;

import Exceptions.NotFoundException;
import java.util.UUID;

public class DoctorNotFoundException extends NotFoundException {
    public DoctorNotFoundException(UUID id) {
        super("Doctor not found: " + id);
    }
}
