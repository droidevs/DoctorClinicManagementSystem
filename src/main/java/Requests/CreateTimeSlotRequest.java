/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Enums.SlotCode;
import Validators.annotations.ValidMaxReservations;
import Validators.annotations.ValidOrderIndex;
import Validators.annotations.ValidSlotCode;
import Validators.annotations.ValidTime;
import Validators.annotations.ValidTimeSlotRequest;
import Validators.annotations.ValidUUID;
import jakarta.validation.constraints.NotNull;
import java.time.LocalTime;
import java.util.UUID;


@ValidTimeSlotRequest
public record CreateTimeSlotRequest(
    
    @ValidUUID
    UUID dayScheduleId,
    
    @ValidSlotCode
    SlotCode slotCode,
    
    @ValidTime
    LocalTime startTime,
    
    @ValidTime
    LocalTime endTime,
    
    @ValidMaxReservations
    int maxReservations,
    
    @ValidOrderIndex
    int orderIndex
    
) {}

