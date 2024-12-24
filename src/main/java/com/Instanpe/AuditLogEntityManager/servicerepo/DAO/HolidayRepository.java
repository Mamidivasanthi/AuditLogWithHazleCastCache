package com.Instanpe.AuditLogEntityManager.servicerepo.DAO;

import com.Instanpe.AuditLogEntityManager.entitymanager.HolidayEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HolidayRepository extends JpaRepository<HolidayEntity, Long> {
    // Add custom methods only if needed


}
