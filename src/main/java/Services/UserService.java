/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Dtos.RoleDto;
import Dtos.UserDto;
import java.util.List;
import java.util.Set;
import java.util.UUID;


public interface UserService {

    UserDto findById(UUID id);

    UserDto findByEmail(String email);

    List<UserDto> findAll();

    void disable(UUID id);
    
    UUID resolveUserId(String principalName);
    
    Set<RoleDto> getUserRoles(UUID userId);
}
