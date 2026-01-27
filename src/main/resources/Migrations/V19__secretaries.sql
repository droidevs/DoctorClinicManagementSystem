CREATE TABLE secretaries (
    id UUID PRIMARY KEY,

    user_id UUID NOT NULL,
    status VARCHAR(30) NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,

    can_receive_payments BOOLEAN NOT NULL DEFAULT FALSE,
    can_verify_identity BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,

    created_by UUID,
    updated_by UUID,

    CONSTRAINT uk_secretary_user UNIQUE (user_id),

    CONSTRAINT fk_secretary_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_secretary_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT fk_secretary_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id)
);

-- Indexes
CREATE INDEX idx_secretary_user
    ON secretaries (user_id);

CREATE INDEX idx_secretary_status
    ON secretaries (status);

CREATE INDEX idx_secretaries_deleted
    ON secretaries (deleted);
