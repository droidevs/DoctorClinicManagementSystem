/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Loggings.business;

import Loggings.business.context.BusinessLogContext;
import java.util.Set;
import java.util.UUID;

public interface DoctorLogger {

    void doctorCreated(UUID doctorId);

    void specialisationsAssigned(
        UUID doctorId,
        Set<UUID> specialisationIds
    );
}
