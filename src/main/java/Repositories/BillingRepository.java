/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;


import Entities.BillEntity;
import Enums.BillStatus;
import Criteria.BillQuery;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface BillingRepository {

    BillEntity save(BillEntity bill);

    BillEntity update(BillEntity bill);

    Optional<BillEntity> findById(UUID id);

    Optional<BillEntity> findByAppointmentId(UUID appointmentId);

    List<BillEntity> findAll();

    List<BillEntity> findAll(int page, int size);

    List<BillEntity> findByStatus(BillStatus status);

    boolean existsByAppointmentId(UUID appointmentId);

    void delete(BillEntity bill);

    void softDelete(UUID id);

    void restore(UUID id);

    List<BillEntity> searchByDateRange(String from, String to);

    List<BillEntity> filter(BillQuery query);
}
