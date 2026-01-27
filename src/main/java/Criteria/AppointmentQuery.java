package Criteria;

import Requests.Pagination;
import java.time.LocalDate;
import java.util.UUID;

public class AppointmentQuery {
    private final Pagination pagination;
    private final UUID doctorId;
    private final UUID patientId;
    private final LocalDate date;
    private final String status;

    public AppointmentQuery(Pagination pagination, UUID doctorId, UUID patientId, LocalDate date, String status) {
        this.pagination = pagination;
        this.doctorId = doctorId;
        this.patientId = patientId;
        this.date = date;
        this.status = status;
    }

    public Pagination getPagination() { return pagination; }
    public UUID getDoctorId() { return doctorId; }
    public UUID getPatientId() { return patientId; }
    public LocalDate getDate() { return date; }
    public String getStatus() { return status; }
}
