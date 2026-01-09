/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  admin
 * Created: Jan 8, 2026
 */

-- ============================================================
-- ROLES TABLE
-- ============================================================

CREATE TABLE roles (
    id UUID PRIMARY KEY,

    name VARCHAR(50) NOT NULL,

    -- ===== AUDIT =====
    created_by UUID,
    created_at TIMESTAMP WITH TIME ZONE NOT NULL,
    updated_by UUID,
    updated_at TIMESTAMP WITH TIME ZONE,

    -- ===== CONSTRAINTS =====
    CONSTRAINT uk_role_name UNIQUE (name),

    CONSTRAINT fk_role_created_by
        FOREIGN KEY (created_by)
        REFERENCES users(id),

    CONSTRAINT fk_role_updated_by
        FOREIGN KEY (updated_by)
        REFERENCES users(id)
);

-- ===== INDEX =====
CREATE INDEX idx_role_name ON roles (name);

