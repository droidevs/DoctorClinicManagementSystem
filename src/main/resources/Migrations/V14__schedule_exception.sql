/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  admin
 * Created: Jan 8, 2026
 */

CREATE TABLE schedule_exception (
    id UUID PRIMARY KEY,

    doctor_id UUID NOT NULL,
    recurrence VARCHAR(20) NOT NULL,
    exception_date DATE,
    exception_day INT,
    exception_month INT,
    exception_type VARCHAR(20) NOT NULL,
    reason VARCHAR(500),

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,

    created_by UUID,
    updated_by UUID,

    CONSTRAINT fk_exception_doctor
        FOREIGN KEY (doctor_id)
        REFERENCES doctors(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_exception_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT fk_exception_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id)
);

CREATE INDEX idx_exception_doctor
    ON schedule_exception (doctor_id);

CREATE INDEX idx_exception_type
    ON schedule_exception (exception_type);

CREATE INDEX idx_exception_recurrence
    ON schedule_exception (recurrence);
