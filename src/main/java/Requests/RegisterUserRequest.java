/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests;

import Validators.annotations.ValidEmail;
import Validators.annotations.ValidPassword;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;


/**
 *
 * @author admin
 */
public record RegisterUserRequest (

        @ValidEmail
        String email,

        @ValidPassword
        String password,

        @NotBlank(message = "Role is required")
        String role
    ){}
