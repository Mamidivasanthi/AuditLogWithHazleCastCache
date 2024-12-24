package com.Instanpe.AuditLogEntityManager.servicerepo.service.impl;

import com.Instanpe.AuditLogEntityManager.DTO.Holiday;
import com.Instanpe.AuditLogEntityManager.servicerepo.DAO.HolidayRepository;
import com.Instanpe.AuditLogEntityManager.entitymanager.GENERIC_STATUS_ENUM;
import com.Instanpe.AuditLogEntityManager.entitymanager.HolidayEntity;

import com.Instanpe.AuditLogEntityManager.servicerepo.service.HolidayService;
import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class HolidayServiceImpl implements HolidayService {
    private final HolidayRepository holidayRepository;
    @Override
    public HolidayEntity saveHoliday(HolidayEntity entity) {
       return holidayRepository.save(entity);
    }
//    Convert HolidayEntity to Holiday DTO
    public Holiday convertToHoliday(HolidayEntity entity){
        return new Holiday(
                entity.getId(),
                entity.getHolidayDate(),
                entity.getDescription(),
                entity.getStatus().name(),
                entity.getEffectiveFrom(),
                entity.getCreatedBy(),
                entity.getUpdatedBy(),
                entity.getCreatedDate(),
                entity.getUpdatedDate(),
                null
        );
    }

    @Override
    public List<HolidayEntity> getAllHolidays() {
        return holidayRepository.findAll();
    }

    @Override
    public Optional<HolidayEntity> getHolidayById(Long id) {
        return holidayRepository.findById(id);
    }

    @Override
    public void deleteHolidayById(Long id) {
        holidayRepository.deleteById(id);
    }

    @Override
    public HolidayEntity updateHoliday(Long id, Holiday holiday) {
        Optional<HolidayEntity> optionalEntity = holidayRepository.findById(id);

        if (optionalEntity.isEmpty()) {
            throw new EntityNotFoundException("Holiday with ID " + id + " not found.");
        }

        HolidayEntity existingEntity = optionalEntity.get();

        // Update fields if provided, otherwise retain existing values
        if (holiday.getHolidayDate() != null) {
            existingEntity.setHolidayDate(holiday.getHolidayDate());
        }
        if (holiday.getDescription() != null) {
            existingEntity.setDescription(holiday.getDescription());
        }
        if (holiday.getStatus() != null) {
            existingEntity.setStatus(GENERIC_STATUS_ENUM.valueOf(holiday.getStatus()));
        }
        if (holiday.getEffectiveFrom() != null) {
            existingEntity.setEffectiveFrom(holiday.getEffectiveFrom());
        }
        if (holiday.getUpdatedBy() != null) {
            existingEntity.setUpdatedBy(holiday.getUpdatedBy());
        }
        existingEntity.setUpdatedDate(LocalDateTime.now());

        // Save and return the updated entity
        return holidayRepository.save(existingEntity);
    }


    //    convert Holiday DTO to Holiday Entity
    public HolidayEntity convertToEntity(Holiday holiday){
        HolidayEntity entity=new HolidayEntity();
        entity.setId(holiday.getId());
        entity.setHolidayDate(holiday.getHolidayDate());
        entity.setDescription(holiday.getDescription());
        entity.setStatus(GENERIC_STATUS_ENUM.valueOf(holiday.getStatus())); // Convert String to ENUM
        entity.setEffectiveFrom(holiday.getEffectiveFrom());
        entity.setCreatedBy(holiday.getCreatedBy());
        entity.setUpdatedBy(holiday.getUpdatedBy());
        entity.setCreatedDate(holiday.getCreatedDate() != null ? holiday.getCreatedDate() : LocalDateTime.now());
        entity.setUpdatedDate(holiday.getUpdatedDate() != null ? holiday.getUpdatedDate() : LocalDateTime.now());
        return entity;
    }
}
