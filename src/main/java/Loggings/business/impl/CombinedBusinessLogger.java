/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Loggings.business.impl;


import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.UUID;
import Loggings.business.action.BusinessAction;
import Loggings.business.core.BusinessLogger;
import Loggings.business.resource.ResourceType;

@ApplicationScoped
public class CombinedBusinessLogger implements BusinessLogger {

    @Inject
    private Slf4jBusinessLogger slf4jLogger;

    @Inject
    private JpaBusinessLogger jpaLogger;

    @Override
    public void log(BusinessAction action, ResourceType resourceType, UUID resourceId, String description) {
        slf4jLogger.log(action, resourceType, resourceId, description);
        jpaLogger.log(action, resourceType, resourceId, description);
    }
    
}
