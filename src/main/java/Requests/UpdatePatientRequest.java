/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

/**
 *
 * @author admin
 */
public record UpdatePatientRequest(
        String firstName,
        String lastName,
        String phone,
        String address
        ) {

}
