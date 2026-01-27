/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  admin
 * Created: Jan 8, 2026
 */


CREATE TABLE doctors (
    id UUID PRIMARY KEY,

    user_id UUID NOT NULL,

    first_name VARCHAR(50) NOT NULL,
    last_name VARCHAR(50) NOT NULL,
    gender VARCHAR(10),
    deleted BOOLEAN NOT NULL DEFAULT FALSE,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,

    created_by UUID,
    updated_by UUID,

    CONSTRAINT uk_doctor_user UNIQUE (user_id),

    CONSTRAINT fk_doctor_user
        FOREIGN KEY (user_id)
        REFERENCES users(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_doctor_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT fk_doctor_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id)
);

CREATE INDEX idx_doctor_user
    ON doctors (user_id);

CREATE INDEX idx_doctor_name
    ON doctors (last_name, first_name);

CREATE INDEX idx_doctors_deleted
    ON doctors (deleted);
