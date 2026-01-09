

CREATE TABLE bills (
    id UUID PRIMARY KEY,

    appointment_id UUID NOT NULL,
    amount NUMERIC(10,2) NOT NULL,
    status VARCHAR(30) NOT NULL,
    paid_at TIMESTAMP WITH TIME ZONE,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,

    created_by UUID,
    updated_by UUID,

    CONSTRAINT uk_bill_appointment UNIQUE (appointment_id),

    CONSTRAINT fk_bill_appointment
        FOREIGN KEY (appointment_id)
        REFERENCES appointments(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_bill_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT fk_bill_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id),

    CONSTRAINT chk_bill_paid_at
        CHECK (
            (status = 'PAID' AND paid_at IS NOT NULL)
            OR
            (status <> 'PAID' AND paid_at IS NULL)
        )
);

-- Indexes
CREATE INDEX idx_bill_appointment
    ON bills (appointment_id);

CREATE INDEX idx_bill_status
    ON bills (status);

CREATE INDEX idx_bill_paid_at
    ON bills (paid_at);
