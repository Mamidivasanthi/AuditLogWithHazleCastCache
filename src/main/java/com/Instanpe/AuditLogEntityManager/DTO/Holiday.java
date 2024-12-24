package com.Instanpe.AuditLogEntityManager.DTO;

import com.Instanpe.AuditLogEntityManager.entitymanager.CalendarHolidayMpngEntity;
import lombok.AllArgsConstructor;
import lombok.Data;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@AllArgsConstructor
public class Holiday {
    private Long id; // Matches holiday_id in the entity
    private LocalDate holidayDate; // Matches holiday_date
    private String description; // Matches description
    private String status; // Use String to make it more generic (e.g., "ACTIVE")
    private LocalDateTime effectiveFrom; // Matches effective_from
    private String createdBy; // Matches created_by
    private String updatedBy; // Matches updated_by
    private LocalDateTime createdDate; // Matches created_date
    private LocalDateTime updatedDate; // Matches updated_date
    private List<CalendarHolidayMpngEntity> calendarHolidays; // Similar to CalendarHolidayMpngEntity, mapped to a DTO
}
