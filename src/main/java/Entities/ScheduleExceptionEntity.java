/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Enums.Day;
import Enums.ExceptionRecurrence;
import Enums.ExceptionType;
import Enums.ReservationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(
    name = "schedule_exception",
    indexes = {
        @Index(name = "idx_exception_doctor", columnList = "doctor_id"),
        @Index(name = "idx_exception_type", columnList = "exception_type"),
        @Index(name = "idx_exception_recurrence", columnList = "recurrence")
    }
)
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ScheduleExceptionEntity extends BaseEntity {

    /* ===== RELATION ===== */
    @ManyToOne(fetch = FetchType.LAZY, optional = false)
    @JoinColumn(name = "doctor_id", nullable = false)
    private DoctorEntity doctor;

    /* ===== RECURRENCE ===== */
    @Enumerated(EnumType.STRING)
    @Column(name = "recurrence", nullable = false, length = 20)
    private ExceptionRecurrence recurrence;

    /* ===== DATE FIELDS ===== */
    @Column(name = "exception_date")
    private LocalDate exceptionDate; // ONE_TIME

    @Column(name = "exception_day")
    private Integer exceptionDay; // MONTHLY / YEARLY

    @Column(name = "exception_month")
    private Integer exceptionMonth; // YEARLY only

    /* ===== TYPE & REASON ===== */
    @Enumerated(EnumType.STRING)
    @Column(name = "exception_type", nullable = false, length = 20)
    private ExceptionType exceptionType;

    @Column(name = "reason", length = 500)
    private String reason;

    /* ===== VALIDATION ===== */
    @PrePersist
    @PreUpdate
    private void validateDates() {
        if (recurrence == ExceptionRecurrence.ONE_TIME && exceptionDate == null) {
            throw new IllegalStateException("ONE_TIME exceptions must have exceptionDate");
        }
        if (recurrence == ExceptionRecurrence.MONTHLY && exceptionDay == null) {
            throw new IllegalStateException("MONTHLY exceptions must have exceptionDay");
        }
        if (recurrence == ExceptionRecurrence.YEARLY &&
                (exceptionDay == null || exceptionMonth == null)) {
            throw new IllegalStateException("YEARLY exceptions must have exceptionDay and exceptionMonth");
        }
    }
}
