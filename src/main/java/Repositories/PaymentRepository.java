/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import Entities.PaymentEntity;
import java.util.Optional;
import java.util.UUID;


public interface PaymentRepository {

    Optional<PaymentEntity> findByBillId(UUID billId);
    
}
