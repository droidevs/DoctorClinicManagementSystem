/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.appointment;

import Exceptions.ConflictException;
import java.time.LocalDateTime;

public class AppointmentTimeConflictException extends ConflictException {
    public AppointmentTimeConflictException(LocalDateTime time) {
        super("Doctor already has an appointment at " + time);
    }
}
