/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import Validators.annotations.ValidEmail;
import Validators.annotations.ValidPassword;

/**
 *
 * @author admin
 */
public record LoginRequest(
        
        @ValidEmail
        String email,
        
        @ValidPassword
        String password
        ) {}
