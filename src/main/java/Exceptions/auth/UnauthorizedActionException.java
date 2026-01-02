/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.auth;

import Exceptions.BusinessException;

/**
 *
 * @author admin
 */
public class UnauthorizedActionException extends BusinessException {
    public UnauthorizedActionException(String action) {
        super("Not allowed to " + action);
    }
}
