package com.Instanpe.AuditLogEntityManager.servicerepo.DAO;

import com.Instanpe.AuditLogEntityManager.entitymanager.AuditLogEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface AuditLogRepository extends JpaRepository<AuditLogEntity, Long> {
    List<AuditLogEntity> findByEntityId(Long entityId);


}
