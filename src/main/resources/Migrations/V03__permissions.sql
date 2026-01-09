/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  admin
 * Created: Jan 8, 2026
 */


CREATE TABLE permissions (
    id UUID PRIMARY KEY,

    code VARCHAR(100) NOT NULL,

    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_at TIMESTAMP WITH TIME ZONE,

    created_by UUID,
    updated_by UUID,

    CONSTRAINT uk_permission_code UNIQUE (code),

    CONSTRAINT fk_permission_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT fk_permission_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id)
);

-- Index
CREATE INDEX idx_permission_code
    ON permissions (code);

