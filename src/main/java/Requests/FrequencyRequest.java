/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Enums.FrequencyType;
import Enums.PeriodUnit;
import Enums.TimingModifier;
import java.time.LocalTime;
import java.util.List;


public record FrequencyRequest(
    FrequencyType type,
    Integer timesPerPeriod,
    PeriodUnit periodUnit,
    TimingModifier timingModifier,
    List<LocalTime> specificTimes
) {}
