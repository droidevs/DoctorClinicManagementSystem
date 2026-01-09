

CREATE TABLE medication_forms (
    medication_id UUID NOT NULL,
    available_form VARCHAR(50) NOT NULL,

    CONSTRAINT pk_medication_forms
        PRIMARY KEY (medication_id, available_form),

    CONSTRAINT fk_medication_forms_medication
        FOREIGN KEY (medication_id)
        REFERENCES medications(id)
        ON DELETE CASCADE
);
