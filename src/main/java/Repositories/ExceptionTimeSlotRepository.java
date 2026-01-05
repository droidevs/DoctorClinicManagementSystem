/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Repositories;

import Entities.ExceptionTimeSlotEntity;
import java.util.List;
import java.util.UUID;

public interface ExceptionTimeSlotRepository {

    List<ExceptionTimeSlotEntity> findByException(UUID exceptionId);
    
}
