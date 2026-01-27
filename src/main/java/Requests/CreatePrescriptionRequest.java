/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Validators.annotations.ValidMedicineName;
import Validators.annotations.ValidUUID;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;


public record CreatePrescriptionRequest(
        
    @ValidUUID 
    UUID appointmentId,
        
    @ValidMedicineName 
    String medicineName,
    
    @Valid @NotNull 
    DosageRequest dosage,
    
    @Valid @NotNull 
    FrequencyRequest frequency,
    
    String instructions
) {}