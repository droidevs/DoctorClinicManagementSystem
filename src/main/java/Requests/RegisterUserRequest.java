/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Requests;


/**
 *
 * @author admin
 */
public record RegisterUserRequest (
        String email,
        String password,
        String role
    ){}
