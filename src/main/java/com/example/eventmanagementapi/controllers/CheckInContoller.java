package com.example.eventmanagementapi.controllers;

import com.example.eventmanagementapi.controllers.exceptions.AlreadyCheckedInException;
import com.example.eventmanagementapi.entities.Participant;
import com.example.eventmanagementapi.repositories.ParticipantRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.PersistentEntityResource;
import org.springframework.data.rest.webmvc.PersistentEntityResourceAssembler;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RepositoryRestController
@RequestMapping("/events")
public class CheckInContoller {

    @Autowired
    ParticipantRepository participantRepository;

    @PostMapping("/checkin/{id}")
    public ResponseEntity<PersistentEntityResource> checkIn(@PathVariable Long id, PersistentEntityResourceAssembler assembler){
        //http://localhost:8080/eventmanagement-api/events/checkin/1
        Optional<Participant> participant = participantRepository.findById(id);
        Participant p;
        if (participant.isPresent()){
            p = participant.get();
            if (p.getCheckedIn()){
                throw new AlreadyCheckedInException();
            }
            else {
                p.setCheckedIn(true);
                participantRepository.save(p);
            }
        } else{
            throw new ResourceNotFoundException();
        }

        return ResponseEntity.ok(assembler.toResource(p));
    }
}
