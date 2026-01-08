/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.slot;

import Exceptions.NotFoundException;

/**
 *
 * @author admin
 */
public class TimeSlotNotFoundException extends NotFoundException {
    
    public TimeSlotNotFoundException() {
        super("Time Slot Not Found");
    }
    
}
