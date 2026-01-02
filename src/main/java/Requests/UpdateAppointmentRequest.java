/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Enums.AppointmentStatus;
import java.time.LocalDateTime;

/**
 *
 * @author admin
 */
public record UpdateAppointmentRequest(
        LocalDateTime appointmentDatetime,
        AppointmentStatus status,
        String reason
        ) {

}
