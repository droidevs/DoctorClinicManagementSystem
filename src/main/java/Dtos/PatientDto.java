/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Dtos;


import java.time.LocalDate;
import java.util.UUID;

public record PatientDto(
    UUID id,
    UserDto user,
    String firstName,
    String lastName,
    String gender,
    LocalDate dateOfBirth,
    String phone,
    String address
) {}
