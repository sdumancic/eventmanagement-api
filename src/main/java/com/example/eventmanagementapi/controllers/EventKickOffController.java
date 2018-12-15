package com.example.eventmanagementapi.controllers;

import com.example.eventmanagementapi.entities.Event;
import com.example.eventmanagementapi.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.rest.webmvc.RepositoryRestController;
import org.springframework.data.rest.webmvc.ResourceNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Optional;

@RepositoryRestController
@RequestMapping("/events")
public class EventKickOffController {

    @Autowired
    EventRepository eventRepository;

    @PostMapping("/start/{id}")
    public ResponseEntity start(@PathVariable Long id){
        //http://localhost:8080/eventmanagement-api/events/start/1
        Optional<Event> event = eventRepository.findById(id);
        Event e;
        if (event.isPresent()){
            e = event.get();
            e.setStarted(true);
            eventRepository.save(e);
        } else{
            throw new ResourceNotFoundException();
        }

        return ResponseEntity.ok(e.getName() + " has started");
    }

}
