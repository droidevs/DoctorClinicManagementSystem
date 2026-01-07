/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Validators.annotations.ValidAddress;
import Validators.annotations.ValidDateOfBirth;
import Validators.annotations.ValidGender;
import Validators.annotations.ValidName;
import Validators.annotations.ValidPhoneNumber;
import Validators.annotations.ValidUUID;
import java.time.LocalDate;
import jakarta.validation.constraints.*;
import java.time.LocalDate;
import java.time.Period;
import java.util.UUID;

public record CreatePatientRequest(
    
    @ValidUUID
    UUID userId,
    
    @ValidName
    String firstName,
    
    @ValidName
    String lastName,
    
    @ValidGender
    String gender,
    
    @ValidDateOfBirth
    LocalDate dateOfBirth,
    
    @ValidPhoneNumber
    String phone,
    
    @ValidAddress
    String address
    
) {}