package com.Instanpe.AuditLogEntityManager.entitymanager;

import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
@EntityListeners(AuditListener.class)
@Table(schema="financial_institution_new_s",name="calendar_holidays_t")
public class HolidayEntity {
    @Column(name="holiday_id")
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(name="holiday_date")
    private LocalDate holidayDate;
    @Column(name="description")
    private String description;
    @Column(name="status")
    @Enumerated(EnumType.STRING)
//  Accepts the enum of type string with status as Active/InActive/Pending
    private GENERIC_STATUS_ENUM status;
    @Column(name="effective_from")
    private LocalDateTime effectiveFrom;
    @Column(name="created_by")
    private String createdBy;
    @Column(name="updated_by")
    private String updatedBy;
    @Column(name="created_date")
    private LocalDateTime createdDate;
    @Column(name="updated_date")
    private LocalDateTime updatedDate;
//    One holiday (e.g., "Christmas") can have multiple mappings in the CalendarHolidayMpngEntity table, linking it to different calendars.
    @OneToMany(mappedBy = "holiday",cascade =CascadeType.ALL,orphanRemoval = true,fetch=FetchType.EAGER)
    private List<CalendarHolidayMpngEntity> calendarHolidays;
}
