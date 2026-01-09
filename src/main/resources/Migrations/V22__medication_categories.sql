

CREATE TABLE medicine_categories (
    id UUID PRIMARY KEY,

    code VARCHAR(50) NOT NULL,
    name VARCHAR(100) NOT NULL,
    description VARCHAR(500),
    is_active BOOLEAN NOT NULL DEFAULT TRUE,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,

    created_by UUID,
    updated_by UUID,

    CONSTRAINT uk_medicine_category_code UNIQUE (code),

    CONSTRAINT fk_medicine_category_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT fk_medicine_category_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id)
);

CREATE INDEX idx_medicine_category_code
    ON medicine_categories (code);

CREATE INDEX idx_medicine_category_active
    ON medicine_categories (is_active);
