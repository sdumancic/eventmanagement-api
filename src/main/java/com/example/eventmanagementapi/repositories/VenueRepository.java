package com.example.eventmanagementapi.repositories;

import com.example.eventmanagementapi.entities.Venue;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface VenueRepository extends PagingAndSortingRepository<Venue, Long> {
}
