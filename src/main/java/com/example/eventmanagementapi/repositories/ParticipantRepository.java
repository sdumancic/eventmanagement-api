package com.example.eventmanagementapi.repositories;

import com.example.eventmanagementapi.entities.Participant;
import org.springframework.data.repository.PagingAndSortingRepository;


public interface ParticipantRepository extends PagingAndSortingRepository<Participant,Long> {
}
