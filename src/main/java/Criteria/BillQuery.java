package Criteria;

import Requests.Pagination;
import java.time.LocalDate;
import java.util.UUID;

public class BillQuery {
    private final Pagination pagination;
    private final String status;
    private final UUID patientId;
    private final UUID doctorId;
    private final Boolean unpaidOnly;
    private final LocalDate fromDate;
    private final LocalDate toDate;
    private final Double minAmount;
    private final Double maxAmount;

    public BillQuery(Pagination pagination, String status, UUID patientId, UUID doctorId, Boolean unpaidOnly, LocalDate fromDate, LocalDate toDate, Double minAmount, Double maxAmount) {
        this.pagination = pagination;
        this.status = status;
        this.patientId = patientId;
        this.doctorId = doctorId;
        this.unpaidOnly = unpaidOnly;
        this.fromDate = fromDate;
        this.toDate = toDate;
        this.minAmount = minAmount;
        this.maxAmount = maxAmount;
    }

    public Pagination getPagination() { return pagination; }
    public String getStatus() { return status; }
    public UUID getPatientId() { return patientId; }
    public UUID getDoctorId() { return doctorId; }
    public Boolean getUnpaidOnly() { return unpaidOnly; }
    public LocalDate getFromDate() { return fromDate; }
    public LocalDate getToDate() { return toDate; }
    public Double getMinAmount() { return minAmount; }
    public Double getMaxAmount() { return maxAmount; }
}
