package com.Instanpe.AuditLogEntityManager.servicerepo.service;

import com.Instanpe.AuditLogEntityManager.DTO.AuditLogDTO;


import java.util.List;

public interface AuditLogService {


    List<AuditLogDTO> getAuditLogsByEntityId(Long entityId);

    List<AuditLogDTO> getAllAuditLogs();
}
