package com.Instanpe.AuditLogEntityManager.entitymanager;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity

@Table(schema="financial_institution_new_s",name="audit_log_t")
public class AuditLogEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="audit_id")
    private Long id;
    @Column(name="entity_name")
    private String entityName;
    @Column(name="entity_id")
    private Long entityId;
    @Column(name="action_type")
    private String actionType;
//    The TEXT type allows storing large strings without defining a specific size limit.
    @Column(name="previous_state",columnDefinition = "TEXT")
    private String previousState;
    @Column(name = "current_state", columnDefinition = "TEXT")
    private String currentState;
    @Column(name="modified_by")
    private String modifiedBy;
    @Column(name="modified_date")
    private LocalDateTime modifiedDate;
}
