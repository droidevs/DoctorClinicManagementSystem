/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.billing;

import Exceptions.NotFoundException;
import java.util.UUID;

public class BillNotFoundException extends NotFoundException {
    public BillNotFoundException(UUID id) {
        super("Bill not found: " + id);
    }
}
