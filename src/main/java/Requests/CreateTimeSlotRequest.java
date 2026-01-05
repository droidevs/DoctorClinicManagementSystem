/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Enums.SlotCode;
import java.time.LocalTime;
import java.util.UUID;


public record CreateTimeSlotRequest(
        UUID dayScheduleId,
        SlotCode slotCode,
        LocalTime startTime,
        LocalTime endTime,
        int maxReservations,
        int orderIndex
) {}

