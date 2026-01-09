/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dtos;

import Enums.AdministrationRoute;
import Enums.WeightUnit;
import Validators.annotations.ValidDosageValue;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record DosageDto(  
    BigDecimal value,
    
    @NotNull
    WeightUnit unit,
    
    @NotNull
    AdministrationRoute route,
    
    String form
) {}