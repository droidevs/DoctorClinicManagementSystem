/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Other/SQLTemplate.sql to edit this template
 */
/**
 * Author:  admin
 * Created: Jan 8, 2026
 */


ALTER TABLE users
ADD CONSTRAINT fk_user_role
FOREIGN KEY (role_id)
REFERENCES roles(id);

ALTER TABLE users
ADD COLUMN created_by UUID,
ADD COLUMN updated_by UUID;

ALTER TABLE users
ADD CONSTRAINT fk_user_created_by
FOREIGN KEY (created_by)
REFERENCES users(id);

ALTER TABLE users
ADD CONSTRAINT fk_user_updated_by
FOREIGN KEY (updated_by)
REFERENCES users(id);
