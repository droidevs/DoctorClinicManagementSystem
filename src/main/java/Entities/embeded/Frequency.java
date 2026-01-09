/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.embeded;

import Enums.FrequencyType;
import Enums.PeriodUnit;
import Enums.TimingModifier;
import jakarta.persistence.CollectionTable;
import jakarta.persistence.Column;
import jakarta.persistence.ElementCollection;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.JoinColumn;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;
import lombok.Getter;
import lombok.Setter;


@Embeddable
@Getter
@Setter
public class Frequency {
    
    @Enumerated(EnumType.STRING)
    @Column(name = "frequency_type", length = 20)
    private FrequencyType type;  // RECURRING, STAT, PRN
    
    @Column(name = "times_per_period")
    private Integer timesPerPeriod;  // e.g., 3
    
    @Enumerated(EnumType.STRING)
    @Column(name = "period_unit", length = 20)
    private PeriodUnit periodUnit;  // PER_DAY, PER_WEEK
    
    @Enumerated(EnumType.STRING)
    @Column(name = "timing_modifier", length = 30)
    private TimingModifier timingModifier;
    
    @Column(name = "specific_times")
    @ElementCollection
    @CollectionTable(name = "prescription_specific_times", 
                     joinColumns = @JoinColumn(name = "prescription_id"))
    private List<LocalTime> specificTimes = new ArrayList<>();  // For exact times
    
    // Business logic
    public String toDisplayString() {
        if (type == FrequencyType.PRN) {
            return "As needed";
        } else if (type == FrequencyType.STAT) {
            return "Immediately, once only";
        } else {
            return timesPerPeriod + " times " + periodUnit.getMedicalAbbreviation().toLowerCase() 
                   + " " + timingModifier.getMedicalCode().toLowerCase();
        }
    }
    
    public boolean isScheduled() {
        return type == FrequencyType.RECURRING;
    }
}
