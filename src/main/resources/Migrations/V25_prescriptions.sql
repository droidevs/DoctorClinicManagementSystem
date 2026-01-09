

-- V__prescriptions.sql
CREATE TABLE prescriptions (
    id UUID PRIMARY KEY,

    -- Relations
    appointment_id UUID NOT NULL,
    medication_id UUID NOT NULL,

    -- Embedded Dosage
    dosage_value NUMERIC(10,4),
    dosage_unit VARCHAR(20),
    administration_route VARCHAR(50),
    dosage_form VARCHAR(50),

    -- Embedded Frequency
    frequency_type VARCHAR(20),
    frequency_times_per_period INT,
    frequency_period_unit VARCHAR(20),
    frequency_timing_modifier VARCHAR(30),

    -- Columns
    instructions VARCHAR(1000),
    duration_days INT,
    refills_allowed INT,

    -- Audit (BaseEntity)
    created_by UUID NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by UUID,
    updated_at TIMESTAMP WITH TIME ZONE,

    CONSTRAINT fk_prescription_appointment FOREIGN KEY (appointment_id)
        REFERENCES appointments(id)
        ON DELETE CASCADE,
    CONSTRAINT fk_prescription_medication FOREIGN KEY (medication_id)
        REFERENCES medications(id)
        ON DELETE RESTRICT,
    CONSTRAINT fk_prescription_created_by FOREIGN KEY (created_by)
        REFERENCES users(id)
        ON DELETE RESTRICT,
    CONSTRAINT fk_prescription_updated_by FOREIGN KEY (updated_by)
        REFERENCES users(id)
        ON DELETE SET NULL
);

-- Indexes
CREATE INDEX idx_prescription_appointment ON prescriptions (appointment_id);
CREATE INDEX idx_prescription_medication ON prescriptions (medication_id);
CREATE INDEX idx_prescription_created_at ON prescriptions (created_at);

-- Table for prescription specific times (ElementCollection)
CREATE TABLE prescription_specific_times (
    prescription_id UUID NOT NULL,
    specific_time TIME NOT NULL,

    CONSTRAINT fk_specific_time_prescription FOREIGN KEY (prescription_id)
        REFERENCES prescriptions(id)
        ON DELETE CASCADE
);
CREATE INDEX idx_specific_time_prescription ON prescription_specific_times (prescription_id);
