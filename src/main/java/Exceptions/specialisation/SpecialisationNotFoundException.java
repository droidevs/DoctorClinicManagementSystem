/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Exceptions.specialisation;

import Exceptions.NotFoundException;
import java.util.UUID;

public class SpecialisationNotFoundException extends NotFoundException {
    public SpecialisationNotFoundException(UUID id) {
        super("Specialisation not found: " + id);
    }
}
