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
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.Instant;
import java.time.LocalDate;
import java.util.UUID;

@Entity
@Table(name = "schedule_exception")
public class ScheduleExceptionEntity extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "doctor_id")
    private DoctorEntity doctor;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExceptionRecurrence recurrence;

    // ONE_TIME
    private LocalDate exceptionDate;

    // MONTHLY / YEARLY
    private Integer exceptionDay;

    // YEARLY only
    private Integer exceptionMonth;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ExceptionType exceptionType;

    private String reason;

    @Column(nullable = false)
    private Instant createdAt = Instant.now();
}

