/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Validators.annotations.ValidDescription;
import Validators.annotations.ValidSpecialisationName;
import Validators.annotations.ValidSpecialisationRequest;
import jakarta.validation.constraints.*;
import java.util.regex.Pattern;

@ValidSpecialisationRequest
public record CreateSpecialisationRequest(
    
    @ValidSpecialisationName
    String name,
    
    @ValidDescription
    String description
    
) {}
