/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import Entities.PaymentEntity;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface PaymentRepository {

    PaymentEntity save(PaymentEntity payment);

    Optional<PaymentEntity> findById(UUID id);

    Optional<PaymentEntity> findByBillId(UUID billId);

    List<PaymentEntity> findAll();

    List<PaymentEntity> findByReceivedBy(UUID secretaryId);

    boolean existsByBillId(UUID billId);
}
