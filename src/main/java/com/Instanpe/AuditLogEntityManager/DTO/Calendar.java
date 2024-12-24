package com.Instanpe.AuditLogEntityManager.DTO;

import com.Instanpe.AuditLogEntityManager.entitymanager.CalendarHolidayMpngEntity;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
public class Calendar {
    private Long Id;
    private String calendarCode;
    private String calendarName;
    private String status;
    private String createdBy;
    private String updatedBy;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;
    private List<CalendarHolidayMpngEntity> calendarHolidays;



}
