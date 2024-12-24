package com.Instanpe.AuditLogEntityManager.DTO;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.time.LocalDateTime;
@Data
@AllArgsConstructor
public class AuditLogDTO {

        private Long id;
        private String entityName;
        private Long entityId;
        private String actionType;
        private String previousState;
        private String currentState;
        private String modifiedBy;
        private LocalDateTime modifiedDate;
}
