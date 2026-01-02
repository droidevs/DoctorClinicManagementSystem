/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Record.java to edit this template
 */
package Requests;

import java.util.UUID;


public record BillFilterRequest(
    String status,
    UUID patientId,
    UUID doctorId,
    Boolean unpaidOnly,
    String fromDate,
    String toDate,
    Double minAmount,
    Double maxAmount
) {
    
    // Builder method for convenience
    public static BillFilterRequest of(String status, UUID patientId, Boolean unpaidOnly) {
        return new BillFilterRequest(status, patientId, null, unpaidOnly, null, null, null, null);
    }
}
