

CREATE TABLE medications (
    id UUID PRIMARY KEY,

    drug_code VARCHAR(50) NOT NULL,
    generic_name VARCHAR(150) NOT NULL,
    brand_name VARCHAR(150),
    drug_class VARCHAR(100),
    category_id UUID NOT NULL,
    contraindications VARCHAR(2000),

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,

    created_by UUID,
    updated_by UUID,

    CONSTRAINT uk_medication_drug_code UNIQUE (drug_code),

    CONSTRAINT fk_medication_category
        FOREIGN KEY (category_id)
        REFERENCES medicine_categories(id),

    CONSTRAINT fk_medication_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT fk_medication_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id)
);

CREATE INDEX idx_medication_drug_code
    ON medications (drug_code);

CREATE INDEX idx_medication_generic_name
    ON medications (generic_name);

CREATE INDEX idx_medication_category
    ON medications (category_id);
