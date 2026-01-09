/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  admin
 * Created: Jan 8, 2026
 */

CREATE TABLE time_slot (
    id UUID PRIMARY KEY,

    day_schedule_id UUID NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    max_reservations INT NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,

    created_by UUID,
    updated_by UUID,

    CONSTRAINT uk_time_slot_day_start UNIQUE (day_schedule_id, start_time),

    CONSTRAINT fk_time_slot_day_schedule
        FOREIGN KEY (day_schedule_id)
        REFERENCES day_schedule(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_time_slot_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT fk_time_slot_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id)
);

CREATE INDEX idx_time_slot_day
    ON time_slot (day_schedule_id);

CREATE INDEX idx_time_slot_start_time
    ON time_slot (start_time);

