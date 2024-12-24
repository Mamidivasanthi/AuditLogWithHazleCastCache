package com.Instanpe.AuditLogEntityManager.entitymanager;
import jakarta.persistence.*;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

@Data
@Entity
//Integrate the Listener class to handle callbacks whenever it is called [JPA lifecycle events]
@EntityListeners(AuditListener.class)
@Table(schema="financial_institution_new_s",name="calendar_t")
public class CalendarEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name="calendar_id")
    private Long id;
    @Column(name="calendar_code")
    private String calendarCode;
    @Column(name="calendar_name")
    private String calendarName;
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
//    one calendar have multiple holidays and all holidays are belongs to a specific calendar
    @OneToMany(mappedBy = "calendar",cascade = CascadeType.ALL,orphanRemoval = true)
    private List<CalendarHolidayMpngEntity> calendarHolidays;


}
