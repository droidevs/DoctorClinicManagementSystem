/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.appointment;

import Exceptions.NotFoundException;
import java.util.UUID;

public class AppointmentNotFoundException extends NotFoundException {
    public AppointmentNotFoundException(UUID id) {
        super("Appointment not found: " + id);
    }
}