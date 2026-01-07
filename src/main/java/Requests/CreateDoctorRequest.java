/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;


import Validators.annotations.ValidGender;
import Validators.annotations.ValidName;
import Validators.annotations.ValidUUID;
import jakarta.validation.constraints.*;
import java.util.UUID;

public record CreateDoctorRequest(
    
    @ValidUUID
    UUID userId,
    
    @ValidName
    String firstName,
    
    @ValidName
    String lastName,
    
    @ValidGender
    String gender
    
) {}
