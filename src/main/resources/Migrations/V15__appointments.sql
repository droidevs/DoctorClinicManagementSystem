/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  admin
 * Created: Jan 8, 2026
 */

CREATE TABLE appointments (
    id UUID PRIMARY KEY,

    patient_id UUID NOT NULL,
    doctor_id UUID NOT NULL,

    appointment_date DATE NOT NULL,

    time_slot_id UUID,
    exception_slot_id UUID,

    status VARCHAR(30) NOT NULL,
    reason VARCHAR(500),
    cancel_reason VARCHAR(500),

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,

    created_by UUID,
    updated_by UUID,

    -- Foreign keys
    CONSTRAINT fk_appointment_patient
        FOREIGN KEY (patient_id)
        REFERENCES patients(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_appointment_doctor
        FOREIGN KEY (doctor_id)
        REFERENCES doctors(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_appointment_time_slot
        FOREIGN KEY (time_slot_id)
        REFERENCES time_slot(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_appointment_exception_slot
        FOREIGN KEY (exception_slot_id)
        REFERENCES schedule_exception(id)
        ON DELETE SET NULL,

    CONSTRAINT fk_appointment_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT fk_appointment_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id)
);

-- Indexes
CREATE INDEX idx_appointment_doctor
    ON appointments (doctor_id);

CREATE INDEX idx_appointment_patient
    ON appointments (patient_id);

CREATE INDEX idx_appointment_datetime
    ON appointments (appointment_date);

CREATE INDEX idx_appointment_status
    ON appointments (status);
