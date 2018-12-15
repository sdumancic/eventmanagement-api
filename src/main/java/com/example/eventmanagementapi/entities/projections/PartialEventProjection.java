package com.example.eventmanagementapi.entities.projections;

import com.example.eventmanagementapi.entities.Event;
import org.springframework.data.rest.core.config.Projection;

import java.time.Instant;
import java.time.ZonedDateTime;

@Projection(types = {Event.class}, name="partial")
public interface PartialEventProjection {

    //http://localhost:8080/eventmanagement-api/events&projection=partial
    String getName();
    ZonedDateTime getStartTime();
    ZonedDateTime getEndTime();

    Instant getCreated();
}
