package com.Instanpe.AuditLogEntityManager.servicerepo.service;

import com.Instanpe.AuditLogEntityManager.DTO.Holiday;
import com.Instanpe.AuditLogEntityManager.entitymanager.HolidayEntity;
import java.util.Optional;
import java.util.List;

public interface HolidayService {

    HolidayEntity saveHoliday(HolidayEntity entity);

    HolidayEntity convertToEntity(Holiday holiday);

    Holiday convertToHoliday(HolidayEntity savedEntity);

    List<HolidayEntity> getAllHolidays();

    Optional<HolidayEntity> getHolidayById(Long id);


    void deleteHolidayById(Long id);

    HolidayEntity updateHoliday(Long id, Holiday holiday);
}
