/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Loggings.business.impl;

import Dtos.RoleDto;
import Entities.AuditLog;
import Loggings.business.action.BusinessAction;
import Loggings.business.context.BusinessLogContext;
import Loggings.business.core.BusinessLogger;
import Loggings.business.resource.ResourceType;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.transaction.Transactional;
import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.stream.Collectors;

@ApplicationScoped
public class JpaBusinessLogger implements BusinessLogger {

    @Inject
    private BusinessLogContext context;

    @PersistenceContext(unitName = "myPU") // your persistence-unit name in persistence.xml
    private EntityManager em;

    @Override
    @Transactional
    public void log(BusinessAction action, ResourceType resourceType, UUID resourceId, String description) {

        AuditLog audit = new AuditLog(
                context.userId(), (Set<String>) context.role().stream().map(RoleDto::name),
                action,
                resourceType,
                resourceId,
                description,
                context.metadata().entrySet().stream()
                        .collect(Collectors.toMap(Map.Entry::getKey, e -> e.getValue().toString())),
                Instant.now()
        );

        em.persist(audit); // persist audit to DB
    }
}
