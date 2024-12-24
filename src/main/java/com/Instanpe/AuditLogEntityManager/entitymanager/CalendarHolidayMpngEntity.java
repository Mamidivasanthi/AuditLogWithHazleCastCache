package com.Instanpe.AuditLogEntityManager.entitymanager;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;

@Data
@Entity
@Table(schema="financial_institution_new_s",name="calendar_holidays_mpg_t")
public class CalendarHolidayMpngEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="calendar_holidays_id")
    private Long calendarHolidaysId;
    @ManyToOne
    @JoinColumn(name="calendar_id")
    private CalendarEntity calendar;
//    Each CalendarHolidayMpngEntity refers back to a single HolidayEntity using the holiday field.
    @ManyToOne
    @JoinColumn(name="holiday_id")
    private HolidayEntity holiday;
    @Column(name="status")
    @Enumerated(EnumType.STRING)
    private GENERIC_STATUS_ENUM status;
    @Column(name="created_by")
    private String createdBy;
    @Column(name="updated_by")
    private String updatedBy;
    @Column(name="created_date")
    private LocalDateTime createdDate;
    @Column(name="updated_date")
    private LocalDateTime updatedDate;



}
