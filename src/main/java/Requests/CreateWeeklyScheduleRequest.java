/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Validators.annotations.ValidUUID;
import java.util.UUID;


public record CreateWeeklyScheduleRequest(
        @ValidUUID
        UUID doctorId
) {}