/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Enums.ReservationType;
import java.util.UUID;

public record CreateDayScheduleRequest(
        UUID weeklyScheduleId,
        int dayOfWeek, // 1 = Monday ... 7 = Sunday
        ReservationType reservationType
) {}
