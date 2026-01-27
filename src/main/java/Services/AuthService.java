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
    
    String register(RegisterUserRequest request);

    String login(LoginRequest request);

    void logout();
    
}
