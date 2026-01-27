package Criteria;

import java.util.UUID;

import Requests.Pagination;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class PrescriptionQuery {
    private UUID appointmentId;
    private UUID patientId;
    private UUID medicationId;
    private Boolean activeOnly;
    private Pagination pagination;
}
