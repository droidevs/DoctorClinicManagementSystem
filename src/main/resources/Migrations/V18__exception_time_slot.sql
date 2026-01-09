/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  admin
 * Created: Jan 8, 2026
 */

CREATE TABLE exception_time_slot (
    id UUID PRIMARY KEY,

    exception_id UUID NOT NULL,
    start_time TIME NOT NULL,
    end_time TIME NOT NULL,
    max_reservations INT NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,

    created_by UUID,
    updated_by UUID,

    -- Foreign keys
    CONSTRAINT fk_exception_slot_exception
        FOREIGN KEY (exception_id)
        REFERENCES schedule_exception(id)
        ON DELETE CASCADE,

    CONSTRAINT fk_exception_slot_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT fk_exception_slot_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id)
);

-- Indexes
CREATE INDEX idx_exception_slot_exception
    ON exception_time_slot (exception_id);

CREATE INDEX idx_exception_slot_start_time
    ON exception_time_slot (start_time);
