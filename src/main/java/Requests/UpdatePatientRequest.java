/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Validators.annotations.ValidName;
import Validators.annotations.ValidPhoneNumber;
import Validators.annotations.ValidAddress;
import jakarta.validation.constraints.NotBlank;

/**
 *
 * @author admin
 */
public record UpdatePatientRequest(
        @ValidName
        @NotBlank(message = "First name is required")
        String firstName,

        @ValidName
        @NotBlank(message = "Last name is required")
        String lastName,

        @ValidPhoneNumber
        String phone,

        @ValidAddress
        String address
) {}
