/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Validators.annotations.ValidExceptionSlotRequest;
import Validators.annotations.ValidMaxReservations;
import Validators.annotations.ValidTime;
import java.time.LocalTime;


@ValidExceptionSlotRequest
public record CreateExceptionSlotRequest(
    
    @ValidTime
    LocalTime startTime,
    
    @ValidTime
    LocalTime endTime,
    
    @ValidMaxReservations
    int maxReservations
    
) {}
