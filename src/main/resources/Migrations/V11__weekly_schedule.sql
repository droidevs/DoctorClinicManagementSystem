/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  admin
 * Created: Jan 8, 2026
 */

CREATE TABLE weekly_schedule (
    id UUID PRIMARY KEY,

    doctor_id UUID NOT NULL,
    active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,

    created_by UUID,
    updated_by UUID,

    CONSTRAINT uk_weekly_schedule_doctor_active UNIQUE (doctor_id),

    CONSTRAINT fk_weekly_schedule_doctor
        FOREIGN KEY (doctor_id)
        REFERENCES doctors(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_weekly_schedule_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT fk_weekly_schedule_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id)
);

CREATE INDEX idx_weekly_schedule_doctor
    ON weekly_schedule (doctor_id);

CREATE INDEX idx_weekly_schedule_active
    ON weekly_schedule (active);
