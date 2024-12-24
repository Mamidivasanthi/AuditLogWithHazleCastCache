package com.Instanpe.AuditLogEntityManager.controller;

import com.Instanpe.AuditLogEntityManager.DTO.AuditLogDTO;
import com.Instanpe.AuditLogEntityManager.servicerepo.service.AuditLogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api/auditlogs")
@RequiredArgsConstructor
public class AuditLogController {
    private final AuditLogService auditLogService;
//    Get all Audit Logs
    @GetMapping
    public ResponseEntity<List<AuditLogDTO>> getAllAuditLogs(){
        List<AuditLogDTO> auditLogs=auditLogService.getAllAuditLogs();
        return ResponseEntity.ok(auditLogs);
    }

//    Get Audit Logs By entity Id
    @GetMapping("/entity/{entityId}")
    public ResponseEntity<List<AuditLogDTO>> getAuditLogsByEntityId(@PathVariable Long entityId){
        List<AuditLogDTO> auditLogs = auditLogService.getAuditLogsByEntityId(entityId);
        return ResponseEntity.ok(auditLogs);
    }
}
