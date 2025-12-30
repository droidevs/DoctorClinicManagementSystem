/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import java.time.LocalDate;

/**
 *
 * @author admin
 */
public record CreatePatientRequest(
        String userId,
        String firstName,
        String lastName,
        String gender,
        LocalDate dateOfBirth,
        String phone,
        String address
        ) {

}
