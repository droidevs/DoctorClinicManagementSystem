/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;


import Enums.SlotCode;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.time.LocalTime;
import java.util.UUID;

@Entity
@Table(
    name = "time_slot",
    uniqueConstraints = @UniqueConstraint(columnNames = {"day_schedule_id", "slot_code"})
)
public class TimeSlotEntity extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "day_schedule_id")
    private DayScheduleEntity daySchedule;

    @Enumerated(EnumType.STRING)
    @Column(name = "slot_code", nullable = false)
    private SlotCode slotCode;

    @Column(nullable = false)
    private LocalTime startTime;

    @Column(nullable = false)
    private LocalTime endTime;

    @Column(nullable = false)
    private int maxReservations;

    @Column(nullable = false)
    private int orderIndex;
}
