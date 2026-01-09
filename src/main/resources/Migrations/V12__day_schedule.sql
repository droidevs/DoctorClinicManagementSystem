/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  admin
 * Created: Jan 8, 2026
 */

CREATE TABLE day_schedule (
    id UUID PRIMARY KEY,

    weekly_schedule_id UUID NOT NULL,
    day VARCHAR(10) NOT NULL,
    reservation_type VARCHAR(20) NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,

    created_by UUID,
    updated_by UUID,

    CONSTRAINT uk_day_schedule_weekday UNIQUE (weekly_schedule_id, day),

    CONSTRAINT fk_day_schedule_weekly_schedule
        FOREIGN KEY (weekly_schedule_id)
        REFERENCES weekly_schedule(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_day_schedule_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT fk_day_schedule_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id)
);

CREATE INDEX idx_day_schedule_weekly_schedule
    ON day_schedule (weekly_schedule_id);

CREATE INDEX idx_day_schedule_day
    ON day_schedule (day);
