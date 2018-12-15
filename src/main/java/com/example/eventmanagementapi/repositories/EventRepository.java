package com.example.eventmanagementapi.repositories;

import com.example.eventmanagementapi.entities.Event;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.security.access.prepost.PreAuthorize;

import java.time.ZoneId;


public interface EventRepository extends PagingAndSortingRepository<Event,Long> {

    Page<Event> findByName(@Param("name") String name, Pageable pageable);

    /*
        http://localhost:8080/eventmanagement-api/events/search/findByNameAndZoneId?name=Advanced Oracle SQL&zoneId=US/Eastern
     */
    Page<Event> findByNameAndZoneId(@Param("name") String name, @Param("zoneId") ZoneId zoneId, Pageable pageable);

}
