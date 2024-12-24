package com.Instanpe.AuditLogEntityManager.entitymanager;

import com.Instanpe.AuditLogEntityManager.servicerepo.DAO.AuditLogRepository;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.persistence.PrePersist;
import jakarta.persistence.PreRemove;
import jakarta.persistence.PreUpdate;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Component;

import java.lang.reflect.Method;
import java.time.LocalDateTime;

@Component
public class AuditListener {

    private final AuditLogRepository auditLogRepository;
    private final ObjectMapper objectMapper;

    public AuditListener(@Lazy AuditLogRepository auditLogRepository, ObjectMapper objectMapper) {
        this.auditLogRepository = auditLogRepository;
        this.objectMapper = objectMapper;
    }

    @PrePersist
    public void logInsert(Object entity) {
        createAuditLog(entity, "INSERT", null);
    }

    @PreUpdate
    public void preUpdate(Object entity) {
        String previousState = serializeEntity(entity);
        createAuditLog(entity, "UPDATE", previousState);
    }

    @PreRemove
    public void preRemove(Object entity) {
        String previousState = serializeEntity(entity);
        createAuditLog(entity, "DELETE", previousState);
    }

    private void createAuditLog(Object entity, String actionType, String previousState) {
        try {
            AuditLogEntity auditLog = new AuditLogEntity();
            String entityName = entity.getClass().getSimpleName(); // Get the entity class name
            Long entityId = getEntityId(entity); // Dynamically retrieve the ID
            String modifiedBy = getUpdatedBy(entity); // Dynamically retrieve the 'updatedBy'

            auditLog.setEntityName(entityName);
            auditLog.setEntityId(entityId);
            auditLog.setActionType(actionType);
            auditLog.setPreviousState(previousState);
            auditLog.setCurrentState(actionType.equals("DELETE") ? null : serializeEntity(entity));
            auditLog.setModifiedBy(modifiedBy != null ? modifiedBy : "system");
            auditLog.setModifiedDate(LocalDateTime.now());
            auditLogRepository.save(auditLog);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private String serializeEntity(Object entity) {
        try {
            return objectMapper.writeValueAsString(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null; // Return null if serialization fails
        }
    }

    private Long getEntityId(Object entity) {
        try {
            Method getIdMethod = entity.getClass().getMethod("getId");
            return (Long) getIdMethod.invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    private String getUpdatedBy(Object entity) {
        try {
            Method getUpdatedByMethod = entity.getClass().getMethod("getUpdatedBy");
            return (String) getUpdatedByMethod.invoke(entity);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}
