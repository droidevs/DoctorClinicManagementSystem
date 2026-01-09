package Listeners;



import Entities.BaseEntity;
import Entities.UserEntity;
import Exceptions.user.UserNotFoundException;
import Repositories.UserRepository;
import Securities.RequestUserContext;
import jakarta.enterprise.context.ApplicationScoped;
import jakarta.enterprise.context.RequestScoped;
import jakarta.inject.Inject;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreUpdate;
import java.time.Instant;
import java.util.UUID;

@ApplicationScoped // Better for listeners; state is handled by the context
public class AuditListener {

    @Inject
    private EntityManager em; // Use EM directly for getReference

    @Inject
    private RequestUserContext userContext;

    @PrePersist
    public void prePersist(BaseEntity entity) {
        setAuditInfo(entity, true);
    }

    @PreUpdate
    public void preUpdate(BaseEntity entity) {
        setAuditInfo(entity, false);
    }

    private void setAuditInfo(BaseEntity entity, boolean isPersist) {
        UUID userId = userContext.getUserId();

        if (userId != null) {
            // PERFORMANCE FIX: This does NOT hit the database.
            // It creates a proxy object using only the ID.
            UserEntity userProxy = em.getReference(UserEntity.class, userId);

            if (isPersist) {
                entity.setCreatedBy(userProxy);
                entity.setCreatedAt(Instant.now());
            } else {
                entity.setUpdatedBy(userProxy);
                entity.setUpdatedAt(Instant.now());
            }
        }
    }
}