package com.Instanpe.AuditLogEntityManager.controller;

import com.Instanpe.AuditLogEntityManager.entitymanager.HolidayEntity;
import com.Instanpe.AuditLogEntityManager.DTO.Holiday;
import com.Instanpe.AuditLogEntityManager.servicerepo.service.HolidayService;
import jakarta.persistence.EntityNotFoundException;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;
import java.util.List;
import java.util.stream.Collectors;

@Data
@RequiredArgsConstructor
@RestController
@RequestMapping("/api/holidays")
@Slf4j
@CacheConfig(cacheNames = "Holiday")
public class HolidayController {
    private final HolidayService holidayService;

    //    create a new Holiday
    @PostMapping
    public ResponseEntity<Holiday> createHoliday(@RequestBody Holiday holiday) {
        log.info("Received request to create a holiday:{}", holiday);
        try {
            HolidayEntity entity = holidayService.convertToEntity(holiday);

            HolidayEntity savedEntity = holidayService.saveHoliday(entity);

            Holiday response = holidayService.convertToHoliday(savedEntity);
            return new ResponseEntity<>(response, HttpStatus.CREATED);


        } catch (Exception e) {
           log.error("Error Occured while creating holiday:{}",e.getMessage(),e);
           return ResponseEntity.internalServerError().build();
        }


    }

    // Get all holidays

    @GetMapping
    @Cacheable(key="'getallholidays'")
    public ResponseEntity<List<Holiday>> getAllHolidays() {
        log.info("Received request to fetch all holidays");
        try {
            List<HolidayEntity> entities = holidayService.getAllHolidays();
            log.debug("Fetched {} holiday entities from the database", entities.size());

            List<Holiday> holidays = entities.stream()
                    .map(holidayService::convertToHoliday)
                    .collect(Collectors.toList());
            return ResponseEntity.ok(holidays);
        } catch (Exception e) {
            log.error("Error occurred while fetching holidays: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

//    Get a holiday by ID
    @GetMapping("/{id}")
    @Cacheable(key="#id")
    public ResponseEntity<Holiday> getHolidayById(@PathVariable Long id) {

        log.info("Received request to fetch holiday with ID: {}", id+" from Db");
        try {
            Optional<HolidayEntity> entity = holidayService.getHolidayById(id);
            if (entity.isPresent()) {

                return ResponseEntity.ok(holidayService.convertToHoliday(entity.get()));
            } else {

                return ResponseEntity.notFound().build();
            }
        } catch (Exception e) {
            log.error("Error occurred while fetching holiday with ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }
//
//    // Delete a holiday by ID
    @DeleteMapping("/{id}")
    @CacheEvict(value = "holidays",key="#id")  // Evict the cache for the specific holiday
    public ResponseEntity<String> deleteHolidayById(@PathVariable Long id) {
        try {        log.info("Received request to delete holiday with ID: {}", id);

            holidayService.deleteHolidayById(id);

            return new ResponseEntity<>("Deleted Successfully",HttpStatus.OK);
        } catch (Exception e) {
            log.error("Error occurred while deleting holiday with ID {}: {}", id, e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{id}")
    @CachePut(value = "Holiday", key = "#id")  // Update the cache for the specific holiday
    @CacheEvict(value = "Holiday", key = "'getallholidays'") // Evict the cache for all holidays

    public ResponseEntity<Holiday> updateHoliday(@PathVariable Long id, @RequestBody Holiday holiday) {
        log.info("Received request to update holiday with ID: {}", id);
        try {
            // Call the service to update the holiday
            HolidayEntity updatedEntity = holidayService.updateHoliday(id, holiday);

            // Convert the updated entity to the Holiday DTO (assuming you have a conversion method)
            Holiday updatedHoliday = holidayService.convertToHoliday(updatedEntity);

            // Log the success message with the ID of the updated holiday
            log.info("Successfully updated holiday with ID: {}", updatedEntity.getId());

            // Return the updated holiday data as a response
            return ResponseEntity.ok(updatedHoliday);
        } catch (EntityNotFoundException e) {
            // Handle the case when the holiday with the given ID is not found
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            // Log any other errors
            log.error("Error occurred while updating holiday: {}", e.getMessage(), e);
            return ResponseEntity.internalServerError().build();
        }
    }


}



