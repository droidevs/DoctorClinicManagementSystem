/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  admin
 * Created: Jan 8, 2026
 */


CREATE TABLE doctor_specialisations (
    doctor_id UUID NOT NULL,
    specialisation_id UUID NOT NULL,

    CONSTRAINT pk_doctor_specialisations
        PRIMARY KEY (doctor_id, specialisation_id),

    CONSTRAINT fk_ds_doctor
        FOREIGN KEY (doctor_id)
        REFERENCES doctors(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_ds_specialisation
        FOREIGN KEY (specialisation_id)
        REFERENCES specialisations(id)
        ON DELETE RESTRICT
);

CREATE INDEX idx_ds_doctor
    ON doctor_specialisations (doctor_id);

CREATE INDEX idx_ds_specialisation
    ON doctor_specialisations (specialisation_id);

