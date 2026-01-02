/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.auth;

import Exceptions.BusinessException;



public class AccountDisabledException extends BusinessException {
    public AccountDisabledException() {
        super("Account is disabled");
    }
}

