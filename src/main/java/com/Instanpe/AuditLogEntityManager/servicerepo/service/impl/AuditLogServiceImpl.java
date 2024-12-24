package com.Instanpe.AuditLogEntityManager.servicerepo.service.impl;

import com.Instanpe.AuditLogEntityManager.DTO.AuditLogDTO;
import com.Instanpe.AuditLogEntityManager.servicerepo.service.AuditLogService;
import com.Instanpe.AuditLogEntityManager.servicerepo.DAO.AuditLogRepository;
import com.Instanpe.AuditLogEntityManager.entitymanager.AuditLogEntity;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class AuditLogServiceImpl implements AuditLogService {
    private final AuditLogRepository auditLogRepository;
    @Override
    public List<AuditLogDTO> getAuditLogsByEntityId(Long entityId) {
        List<AuditLogEntity> auditLogs=auditLogRepository.findByEntityId(entityId);
        return auditLogs.stream().map(this::convertToDTO).collect(Collectors.toList());

    }
//    Get all

    @Override
    public List<AuditLogDTO> getAllAuditLogs() {
        List<AuditLogEntity> auditLogs=auditLogRepository.findAll();
        return auditLogs.stream().map(this::convertToDTO).collect(Collectors.toList());

    }

    private AuditLogDTO convertToDTO(AuditLogEntity auditLogEntity) {
        return new AuditLogDTO(
                auditLogEntity.getId(),
                auditLogEntity.getEntityName(),
                auditLogEntity.getEntityId(),
                auditLogEntity.getActionType(),
                auditLogEntity.getPreviousState(),
                auditLogEntity.getCurrentState(),
                auditLogEntity.getModifiedBy(),
                auditLogEntity.getModifiedDate()

        );
    }
}
