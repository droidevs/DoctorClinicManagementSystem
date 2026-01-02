/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.appointment;

import Exceptions.ConflictException;


public class InvalidAppointmentStateException extends ConflictException {
    public InvalidAppointmentStateException(String state) {
        super("Invalid appointment state: " + state);
    }
}
