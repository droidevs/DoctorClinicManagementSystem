/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Enums.AdministrationRoute;
import Enums.WeightUnit;
import Validators.annotations.ValidDosageValue;
import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;

public record DosageRequest(
    @ValidDosageValue
    BigDecimal value,
    
    @NotNull
    WeightUnit unit,
    
    @NotNull
    AdministrationRoute route,
    
    String form
) {}
