/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package Loggings.business.core;


import Loggings.business.context.BusinessLogContext;
import Loggings.business.action.BusinessAction;
import Loggings.business.resource.ResourceType;
import java.util.UUID;

public interface BusinessLogger {

    void log(
        BusinessLogContext context,
        BusinessAction action,
        ResourceType resourceType,
        UUID resourceId,
        String description
    );
}