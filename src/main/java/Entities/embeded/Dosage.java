/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities.embeded;

import Enums.AdministrationRoute;
import Enums.WeightUnit;
import jakarta.persistence.Column;
import jakarta.persistence.Embeddable;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import java.math.BigDecimal;
import lombok.Getter;
import lombok.Setter;



@Embeddable
@Getter
@Setter
public class Dosage {
    
    @Column(name = "dosage_value", precision = 10, scale = 4)
    private BigDecimal value;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "dosage_unit", length = 20)
    private WeightUnit unit;
    
    @Enumerated(EnumType.STRING)
    @Column(name = "administration_route", length = 50)
    private AdministrationRoute route;
    
    @Column(name = "dosage_form", length = 50)
    private String form;  // tablet, capsule, liquid, injection
    
    // Helper methods
    public String toDisplayString() {
        return value.stripTrailingZeros().toPlainString() + " " + unit.getJsonValue();
    }
    
}
