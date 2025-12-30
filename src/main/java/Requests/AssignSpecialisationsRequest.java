/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import java.util.Set;

/**
 *
 * @author admin
 */
public record AssignSpecialisationsRequest(
        Set<String> specialisationIds
        ) {}
