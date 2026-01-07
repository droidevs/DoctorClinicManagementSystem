/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import java.util.Set;
import java.util.UUID;

public record AssignPermissionsRequest(

        @NotNull(message = "Permission IDs cannot be null")
        @NotEmpty(message = "At least one permission ID must be provided")
        Set<@NotNull(message = "Permission ID cannot be null") UUID> permissionIds

) {}


