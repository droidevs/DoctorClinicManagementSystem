/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;

import jakarta.persistence.*;
import java.time.Instant;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Entity
@Table(name = "audit_logs")
public class AuditLog {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    private UUID userId;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "audit_roles", joinColumns = @JoinColumn(name = "audit_id"))
    @Column(name = "role")
    private Set<String> roles;

    @Enumerated(EnumType.STRING)
    private Loggings.business.action.BusinessAction action;

    @Enumerated(EnumType.STRING)
    private Loggings.business.resource.ResourceType resourceType;

    private UUID resourceId;

    @Column(length = 1000)
    private String description;

    @ElementCollection(fetch = FetchType.EAGER)
    @CollectionTable(name = "audit_metadata", joinColumns = @JoinColumn(name = "audit_id"))
    @MapKeyColumn(name = "key")
    @Column(name = "value")
    private Map<String, String> metadata;

    private Instant timestamp;

    public AuditLog() {}

    public AuditLog(UUID userId, Set<String> roles, Loggings.business.action.BusinessAction action,
                    Loggings.business.resource.ResourceType resourceType, UUID resourceId,
                    String description, Map<String, String> metadata, Instant timestamp) {
        this.userId = userId;
        this.roles = roles;
        this.action = action;
        this.resourceType = resourceType;
        this.resourceId = resourceId;
        this.description = description;
        this.metadata = metadata;
        this.timestamp = timestamp;
    }

}
