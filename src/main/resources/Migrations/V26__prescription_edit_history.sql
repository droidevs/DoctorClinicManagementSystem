

-- ============================
-- Table: prescription_edit_history
-- ============================

CREATE TABLE prescription_edit_history (
    id UUID PRIMARY KEY,

    -- Foreign key to prescription
    prescription_id UUID NOT NULL,
    CONSTRAINT fk_presc_edit_prescription FOREIGN KEY (prescription_id)
        REFERENCES prescriptions(id)
        ON DELETE CASCADE,

    -- Previous state snapshot
    prev_medication_id UUID NOT NULL,
    prev_medication_name VARCHAR(255) NOT NULL,
    prev_dosage_value NUMERIC(10,4),
    prev_dosage_unit VARCHAR(20),
    prev_administration_route VARCHAR(50),
    prev_dosage_form VARCHAR(50),
    prev_frequency_type VARCHAR(20),
    prev_times_per_period INT,
    prev_period_unit VARCHAR(20),
    prev_timing_modifier VARCHAR(30),
    prev_instructions VARCHAR(1000),
    prev_duration_days INT,
    prev_refills_allowed INT,

    -- New state snapshot
    new_medication_id UUID NOT NULL,
    new_medication_name VARCHAR(255) NOT NULL,
    new_dosage_value NUMERIC(10,4),
    new_dosage_unit VARCHAR(20),
    new_administration_route VARCHAR(50),
    new_dosage_form VARCHAR(50),
    new_frequency_type VARCHAR(20),
    new_times_per_period INT,
    new_period_unit VARCHAR(20),
    new_timing_modifier VARCHAR(30),
    new_instructions VARCHAR(1000),
    new_duration_days INT,
    new_refills_allowed INT,

    -- Metadata
    reason_for_change VARCHAR(500),
    requires_patient_notification BOOLEAN NOT NULL DEFAULT FALSE,

    created_by UUID NOT NULL,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by UUID,
    updated_at TIMESTAMP WITH TIME ZONE,

    CONSTRAINT fk_presc_edit_created_by FOREIGN KEY (created_by) REFERENCES users(id),
    CONSTRAINT fk_presc_edit_updated_by FOREIGN KEY (updated_by) REFERENCES users(id)
);

-- Indexes
CREATE INDEX idx_presc_edit_prescription ON prescription_edit_history(prescription_id);
CREATE INDEX idx_presc_edit_created_at ON prescription_edit_history(created_at);
CREATE INDEX idx_presc_edit_notify ON prescription_edit_history(requires_patient_notification);


-- ============================
-- Table: prescription_snapshot_specific_times
-- Stores specific times for previous and new frequency
-- ============================

CREATE TABLE prescription_snapshot_specific_times (
    id UUID PRIMARY KEY,
    prescription_snapshot_id UUID NOT NULL,
    specific_time TIME NOT NULL,

    CONSTRAINT fk_presc_snapshot FOREIGN KEY (prescription_snapshot_id)
        REFERENCES prescription_edit_history(id)
        ON DELETE CASCADE
);

-- Optional index for querying times
CREATE INDEX idx_presc_snapshot_times ON prescription_snapshot_specific_times(prescription_snapshot_id);
