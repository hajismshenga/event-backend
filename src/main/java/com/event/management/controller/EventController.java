package com.event.management.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.event.management.model.Event;
import com.event.management.service.EventService;

@RestController
@RequestMapping("/api/events")
public class EventController {

    @Autowired
    private EventService eventService;


    @GetMapping("/all")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = eventService.getAllEvents();
        return new ResponseEntity<>(events, HttpStatus.OK);
    }

    @PostMapping("/add")
    public ResponseEntity<?> addEvent(@RequestBody EventRequest eventRequest) {
        try {
            // Create the event
            Event event = new Event();
            event.setName(eventRequest.getName());
            event.setLocation(eventRequest.getLocation());
            event.setDetails(eventRequest.getDetails());
            event.setDescription(eventRequest.getDescription());
            event.setDate(eventRequest.getDate());
            event.setTime(eventRequest.getTime());
            Event savedEvent = eventService.addEvent(event);

            // Generate invitation link
            String baseUrl = "http://localhost:8080/api/events/respond";
            String inviteLink = baseUrl + "?eventId=" + savedEvent.getId() + "&token=" + savedEvent.getId(); // Using event ID as token for simplicity

            // Prepare the invitation email content
            String emailContent = "Hello,\n\nYou are invited to the event \"" + savedEvent.getName() + "\".\n\nDetails:\nDate: " + savedEvent.getDate() +
                "\nTime: " + savedEvent.getTime() + "\nLocation: " + savedEvent.getLocation() + "\nDescription: " + savedEvent.getDescription() +
                "\n\nPlease confirm your attendance by clicking the following link:\n" + inviteLink + "\n\nThank you!";
            
            // Return the invitation link for the user to copy and share
            return new ResponseEntity<>(emailContent, HttpStatus.CREATED);
        } catch (Exception e) {
            // Log the stack trace
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to add event: " + e.getMessage());
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable("id") Long eventId) {
        Event event = eventService.getEventById(eventId);
        return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteEvent(@PathVariable("id") Long eventId) {
        eventService.deleteEvent(eventId);
        return new ResponseEntity<>("Event deleted successfully", HttpStatus.OK);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable("id") Long eventId, @RequestBody Event event) {
        event.setId(eventId);
        Event updatedEvent = eventService.updateEvent(event);
        return new ResponseEntity<>(updatedEvent, HttpStatus.CREATED);
    }

    @GetMapping("/respond")
    public ResponseEntity<String> respondToEvent(@RequestParam Long eventId, @RequestParam String token) {
        // In a real application, you'd verify the token and record the response
        return new ResponseEntity<>("Response link received for event ID: " + eventId, HttpStatus.OK);
    }
}
