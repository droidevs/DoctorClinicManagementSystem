/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import java.time.LocalDateTime;
import java.util.UUID;



/**
 *
 * @author admin
 */
public record CreateAppointmentRequest(
        String patientId,
        String doctorId,
        LocalDateTime appointmentDatetime,
        UUID slotId,              // normal slot (weekly)
        UUID exceptionSlotId      // exception slot (CUSTOM_SLOTS)
        ) {

}
