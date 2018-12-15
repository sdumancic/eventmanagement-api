package com.example.eventmanagementapi.repositories;

import com.example.eventmanagementapi.entities.Organizer;
import org.springframework.data.repository.PagingAndSortingRepository;

public interface OrganizerRepository extends PagingAndSortingRepository<Organizer,Long> {
}
