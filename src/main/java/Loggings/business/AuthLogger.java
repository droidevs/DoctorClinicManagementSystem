/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Loggings.business;

import Loggings.business.context.BusinessLogContext;

public interface AuthLogger {

    void loginSuccess(BusinessLogContext context);

    void loginFailure(String email);

    void logout(BusinessLogContext context);
}
