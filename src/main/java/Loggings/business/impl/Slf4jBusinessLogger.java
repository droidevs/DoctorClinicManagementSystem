/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Loggings.business.impl;


import Loggings.business.action.BusinessAction;
import Loggings.business.context.BusinessLogContext;
import Loggings.business.core.BusinessLogger;
import Loggings.business.resource.ResourceType;
import jakarta.inject.Inject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

public class Slf4jBusinessLogger implements BusinessLogger {

    private static final Logger log =
            LoggerFactory.getLogger("BUSINESS_AUDIT");

    @Inject
    private BusinessLogContext context;
    
    @Override
    public void log(
            BusinessAction action,
            ResourceType resourceType,
            UUID resourceId,
            String description
    ) {

        log.info(
            "userId={} role={} action={} resource={} resourceId={} description={} metadata={}",
            context.userId(),
            context.role(),
            action,
            resourceType,
            resourceId,
            description,
            context.metadata()
        );
    }
}