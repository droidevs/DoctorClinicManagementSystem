/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import Enums.Day;
import Enums.ReservationType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.persistence.UniqueConstraint;
import java.util.UUID;

@Entity
@Table(
    name = "day_schedule",
    uniqueConstraints = @UniqueConstraint(columnNames = {"weekly_schedule_id", "day"})
)
public class DayScheduleEntity extends BaseEntity {

    @ManyToOne(optional = false)
    @JoinColumn(name = "weekly_schedule_id")
    private WeeklyScheduleEntity weeklySchedule;

    @Enumerated(EnumType.STRING)
    @Column(name = "day",nullable = false)
    private Day day;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private ReservationType reservationType;
}
