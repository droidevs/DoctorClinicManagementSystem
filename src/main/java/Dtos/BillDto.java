/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Dtos;

import Enums.BillStatus;
import Enums.PaymentMethod;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

/**
 *
 * @author admin
 */
public record BillDto(
    UUID id,
    AppointmentDto appointment,
    BigDecimal amount,
    BillStatus status,
    PaymentMethod paymentMethod,
    LocalDateTime paidAt
) {}
