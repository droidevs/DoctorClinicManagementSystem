/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;


import Dtos.BillDto;
import Requests.BillFilterRequest;
import Requests.CreateBillRequest;
import Requests.PaymentRequest;
import java.util.List;
import java.util.UUID;

public interface BillingService {

    BillDto create(CreateBillRequest request);

    BillDto findById(UUID id);

    List<BillDto> findAll();
    
    List<BillDto> findAll(int page, int size);

    List<BillDto> filter(BillFilterRequest request);

    BillDto pay(UUID id, PaymentRequest request);

    void softDelete(UUID id);

    void restore(UUID id);

    List<BillDto> searchByDateRange(String from, String to);
}
