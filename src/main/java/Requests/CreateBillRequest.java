/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import java.math.BigDecimal;

/**
 *
 * @author admin
 */
public record CreateBillRequest(
        String appointmentId,
        BigDecimal amount
        ) {

}
