package com.Instanpe.AuditLogEntityManager.servicerepo.DAO;

import com.Instanpe.AuditLogEntityManager.entitymanager.CalendarEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CalendarRepository extends JpaRepository<CalendarEntity,Long> {
}
