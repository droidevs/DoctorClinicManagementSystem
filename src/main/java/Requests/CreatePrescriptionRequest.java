/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

/**
 *
 * @author admin
 */
public record CreatePrescriptionRequest(
        String appointmentId,
        String medicineName,
        String dosage,
        String frequency,
        String instructions
        ) {

}
