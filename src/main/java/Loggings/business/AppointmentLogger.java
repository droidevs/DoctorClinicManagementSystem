/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Loggings.business;

import Loggings.business.context.BusinessLogContext;
import java.time.LocalDateTime;
import java.util.UUID;

public interface AppointmentLogger {

    void appointmentCreated(
        UUID appointmentId,
        UUID doctorId,
        LocalDateTime time
    );

    void appointmentCancelled(
        UUID appointmentId,
        String cancelReason
    );

    void appointmentCompleted(
        UUID appointmentId
    );
}
