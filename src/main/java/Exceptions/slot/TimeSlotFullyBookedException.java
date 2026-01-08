/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.slot;

import Exceptions.ConflictException;

/**
 *
 * @author admin
 */
public class TimeSlotFullyBookedException extends ConflictException {
    
    public TimeSlotFullyBookedException() {
        super("Time Slot Fully Booked");
    }
    
}
