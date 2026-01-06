/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;

import java.util.Set;

public record UserRoleDto(
        Long roleId,
        String roleName,
        Set<String> permissions
) {}
