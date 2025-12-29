/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;

import java.util.UUID;

/**
 *
 * @author admin
 */

public record PrescriptionDto(
    UUID id,
    AppointmentDto appointment,
    String medicineName,
    String dosage,
    String frequency,
    String instructions
) {}

