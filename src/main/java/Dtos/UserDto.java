/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;

import Enums.Role;
import java.util.UUID;

public record UserDto(
    UUID id,
    String email,
    Role role,
    boolean enabled
) {}

