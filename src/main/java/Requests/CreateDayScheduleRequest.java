/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Enums.ReservationType;
import java.util.UUID;

import Enums.ReservationType;
import Jsons.UniversalEnumHandler;
import Validators.annotations.ValidDayOfWeek;
import Validators.annotations.ValidUUID;
import jakarta.json.bind.annotation.JsonbTypeSerializer;
import jakarta.validation.constraints.*;
import java.util.UUID;


public record CreateDayScheduleRequest(
    
    @ValidUUID(message = "Weekly schedule ID must be a valid UUID")
    UUID weeklyScheduleId,
    
    @ValidDayOfWeek
    int dayOfWeek, // 1 = Monday ... 7 = Sunday
    
    @NotNull(message = "Reservation type is required")
    @JsonbTypeSerializer(UniversalEnumHandler.class)
    ReservationType reservationType
    
) {}
