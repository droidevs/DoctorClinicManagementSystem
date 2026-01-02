/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Services;

import Dtos.UserDto;
import Requests.LoginRequest;
import Requests.RegisterUserRequest;

/**
 *
 * @author admin
 */
public interface AuthService {
    
    
    void login(LoginRequest request);
    
    void register(RegisterUserRequest request);
    
    void logout();
    
}
