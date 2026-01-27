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

        @NotBlank(message = "Email is required")
        @Email(message = "Invalid email format")
        @ValidEmail
        String email,

        @NotBlank(message = "Password is required")
        @Size(min = 8, max = 100, message = "Password must be between 8 and 100 characters")
        @ValidPassword
        String password
    ){}
