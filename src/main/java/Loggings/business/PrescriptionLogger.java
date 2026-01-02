/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Loggings.business;

import Loggings.business.context.BusinessLogContext;
import java.util.UUID;

public interface PrescriptionLogger {

    void prescriptionCreated(
        UUID prescriptionId,
        UUID appointmentId
    );
}