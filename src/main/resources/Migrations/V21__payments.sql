CREATE TABLE payments (
    id UUID PRIMARY KEY,

    bill_id UUID NOT NULL,
    amount NUMERIC(10,2) NOT NULL,
    payment_method VARCHAR(30) NOT NULL,
    received_by UUID NOT NULL,
    stripe_payment_intent_id VARCHAR(150),
    received_at TIMESTAMP WITH TIME ZONE NOT NULL,
    deleted BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,

    created_by UUID,
    updated_by UUID,

    CONSTRAINT uk_payment_bill UNIQUE (bill_id),
    CONSTRAINT uk_payment_stripe_intent UNIQUE (stripe_payment_intent_id),

    CONSTRAINT fk_payment_bill
        FOREIGN KEY (bill_id)
        REFERENCES bills(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_payment_received_by
        FOREIGN KEY (received_by)
        REFERENCES secretaries(id),

    CONSTRAINT fk_payment_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT fk_payment_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id),

    CONSTRAINT chk_payment_amount
        CHECK (amount > 0),

    CONSTRAINT chk_payment_stripe
        CHECK (
            (payment_method = 'CARD' AND stripe_payment_intent_id IS NOT NULL)
            OR
            (payment_method <> 'CARD' AND stripe_payment_intent_id IS NULL)
        )
);

-- Indexes
CREATE INDEX idx_payment_bill
    ON payments (bill_id);

CREATE INDEX idx_payment_method
    ON payments (payment_method);

CREATE INDEX idx_payment_received_by
    ON payments (received_by);

CREATE INDEX idx_payment_received_at
    ON payments (received_at);

CREATE INDEX idx_payments_deleted
    ON payments (deleted);
