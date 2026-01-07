/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Enums.FrequencyType;
import Enums.PeriodUnit;
import Enums.TimingModifier;


public record FrequencyModel(
    FrequencyType type,      // e.g., RECURRING vs AS_NEEDED
    int timesPerPeriod,      // e.g., 3
    PeriodUnit period,       // e.g., PER_DAY
    TimingModifier timing    // e.g., BEFORE_MEALS
) {}
