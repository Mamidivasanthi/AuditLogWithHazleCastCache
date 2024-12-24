//package com.Instanpe.AuditLogEntityManager;
//
//import com.Instanpe.AuditLogEntityManager.DTO.CalendarEntity;
//import com.Instanpe.AuditLogEntityManager.DTO.AuditLogEntity;
//import com.Instanpe.AuditLogEntityManager.Repository.AuditLogRepository;
//import com.Instanpe.AuditLogEntityManager.Repository.CalendarRepository;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//
//import static org.junit.jupiter.api.Assertions.*;
//
//import java.util.List;
//
//@SpringBootTest
//public class AuditListenerTest {
//
//    @Autowired
//    private CalendarRepository calendarRepository;  // Your Calendar Entity repository
//
//    @Autowired
//    private AuditLogRepository auditLogRepository;  // Your Audit Log repository
//
//    @BeforeEach
//    public void setup() {
//        auditLogRepository.deleteAll();  // Clear previous audit logs before each test
//    }
//
//    @Test
//    public void testCreateAuditLog() {
//        // Create a new CalendarEntity
//        CalendarEntity calendar = new CalendarEntity();
//        calendar.setCalendarCode("CAL123");
//        calendar.setCalendarName("Test Calendar");
//        calendar.setStatus(GENERIC_STATUS_ENUM.ACTIVE);
//        calendar.setCreatedBy("TestUser");
//        calendar.setUpdatedBy("TestUser");
//
//        // Save the entity, which will trigger the @PrePersist and @PostPersist audit logs
//        calendarRepository.save(calendar);
//
//        // Check if audit log was created
//        List<AuditLogEntity> auditLogs = auditLogRepository.findAll();
//        assertFalse(auditLogs.isEmpty(), "Audit logs should not be empty");
//        AuditLogEntity log = auditLogs.get(0);
//        assertEquals("CREATE", log.getActionType(), "Action type should be CREATE");
//        assertNotNull(log.getPreviousState(), "Previous state should not be null");
//        assertNotNull(log.getCurrentState(), "Current state should not be null");
//        assertEquals("TestUser", log.getModifiedBy(), "ModifiedBy should match the creator");
//    }
//
//    @Test
//    public void testUpdateAuditLog() {
//        // Create and save an entity first
//        CalendarEntity calendar = new CalendarEntity();
//        calendar.setCalendarCode("CAL124");
//        calendar.setCalendarName("Test Update Calendar");
//        calendar.setStatus(GENERIC_STATUS_ENUM.ACTIVE);
//        calendar.setCreatedBy("TestUser");
//        calendar.setUpdatedBy("TestUser");
//
//        calendarRepository.save(calendar);
//
//        // Now, update the entity
//        calendar.setCalendarName("Updated Calendar Name");
//        calendar.setUpdatedBy("UpdatedUser");
//
//        // Save the updated entity, which will trigger @PreUpdate and @PostUpdate
//        calendarRepository.save(calendar);
//
//        // Verify that the audit log for update exists
//        List<AuditLogEntity> auditLogs = auditLogRepository.findAll();
//        assertTrue(auditLogs.size() > 1, "Audit logs should have more than one entry");
//        AuditLogEntity updateLog = auditLogs.get(1);
//        assertEquals("UPDATE", updateLog.getActionType(), "Action type should be UPDATE");
//        assertNotNull(updateLog.getPreviousState(), "Previous state should be present");
//        assertNotNull(updateLog.getCurrentState(), "Current state should be present");
//        assertEquals("UpdatedUser", updateLog.getModifiedBy(), "ModifiedBy should match the updater");
//    }
//
//    @Test
//    public void testDeleteAuditLog() {
//        // Create and save an entity first
//        CalendarEntity calendar = new CalendarEntity();
//        calendar.setCalendarCode("CAL125");
//        calendar.setCalendarName("Test Delete Calendar");
//        calendar.setStatus(GENERIC_STATUS_ENUM.ACTIVE);
//        calendar.setCreatedBy("TestUser");
//        calendar.setUpdatedBy("TestUser");
//
//        calendarRepository.save(calendar);
//
//        // Now, delete the entity
//        calendarRepository.delete(calendar);
//
//        // Verify that the delete action triggered the audit log
//        List<AuditLogEntity> auditLogs = auditLogRepository.findAll();
//        assertTrue(auditLogs.size() > 2, "Audit logs should have more than two entries");
//        AuditLogEntity deleteLog = auditLogs.get(2);
//        assertEquals("DELETE", deleteLog.getActionType(), "Action type should be DELETE");
//        assertNotNull(deleteLog.getPreviousState(), "Previous state should be present");
//        assertNull(deleteLog.getCurrentState(), "Current state should be null for DELETE");
//    }
//}
