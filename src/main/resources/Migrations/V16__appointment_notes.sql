/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  admin
 * Created: Jan 8, 2026
 */


CREATE TABLE appointment_notes (
    id UUID PRIMARY KEY,

    appointment_id UUID NOT NULL,
    doctor_id UUID NOT NULL,

    note TEXT NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,

    created_by UUID,
    updated_by UUID,

    -- Foreign keys
    CONSTRAINT fk_note_appointment
        FOREIGN KEY (appointment_id)
        REFERENCES appointments(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_note_doctor
        FOREIGN KEY (doctor_id)
        REFERENCES doctors(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_note_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT fk_note_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id)
);

-- Indexes
CREATE INDEX idx_note_appointment
    ON appointment_notes (appointment_id);

CREATE INDEX idx_note_doctor
    ON appointment_notes (doctor_id);

CREATE INDEX idx_note_created_at
    ON appointment_notes (created_at);
