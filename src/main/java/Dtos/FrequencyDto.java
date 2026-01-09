/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dtos;

import Enums.FrequencyType;
import Enums.PeriodUnit;
import Enums.TimingModifier;
import java.time.LocalTime;
import java.util.List;


public record FrequencyDto(
        FrequencyType type,
        Integer timesPerPeriod,
        PeriodUnit periodUnit,
        TimingModifier timingModifier,
        List<LocalTime> specificTimes
) {}
