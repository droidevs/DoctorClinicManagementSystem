/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import java.time.LocalTime;


public record CreateExceptionSlotRequest(
        LocalTime startTime,
        LocalTime endTime,
        int maxReservations,
        String slotCode
) {}
