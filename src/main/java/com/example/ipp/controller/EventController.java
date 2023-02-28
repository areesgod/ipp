package com.example.ipp.controller;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


import com.example.ipp.exception.ResourceNotFoundException;
import com.example.ipp.model.Event;
import com.example.ipp.repository.EventRepository;
@CrossOrigin(origins = "http://localhost:4200")
@RestController
@RequestMapping("/api/v1/")
public class EventController {
    @Autowired
    private EventRepository eventRepository;

    @GetMapping("/events")
    public List<Event>getAllEvents(){
        return eventRepository.findAll();
    }
    @PostMapping("/events")
    public Event createEvent(@RequestBody Event event){
        return eventRepository.save(event);
    }
    @GetMapping("/events/{id}")
    public ResponseEntity<Event> getEmployeeById(@PathVariable Long id) {
        Event employee =eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evente not exist with id :" + id));
        return ResponseEntity.ok(employee);
    }
    @PutMapping("/events/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long id,@RequestBody Event eventDetails){
        Event event = eventRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Event does not exist with id:" + id));
        event.setDescription(eventDetails.getDescription());
        event.setType(eventDetails.getType());
        Event updatedEvent = eventRepository.save(event);
        return ResponseEntity.ok(updatedEvent);
    }
    @DeleteMapping("/events/{id}")
    public ResponseEntity<Map<String,Boolean>> deleteEvent(@PathVariable Long id){
        Event event = eventRepository.findById(id)
                .orElseThrow(()-> new ResourceNotFoundException("Event doesn't exist id:" + id));
        eventRepository.delete(event);
        Map<String,Boolean> response = new HashMap<>();
        response.put("deleted",Boolean.TRUE);
        return ResponseEntity.ok(response);
    }
}
