package com.event.management.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.event.management.model.Event;
import com.event.management.repository.EventRepository;

@Service
public class EventService {

    @Autowired
    private EventRepository eventRepository;

    public List<Event> getAllEvents() {
        return eventRepository.findAll();
    }

    public Event addEvent(Event event) {
        return eventRepository.save(event);
    }

    public Event getEventById(Long eventId) {
        return eventRepository.findById(eventId).orElse(null);
    }

    public void deleteEvent(Long eventId) {
        eventRepository.deleteById(eventId);
    }

    public Event updateEvent(Event event) {
        return eventRepository.save(event);
    }

    public boolean verifyToken(Long eventId, String token) {
        // Simple token verification (in real applications, use a more secure approach)
        return eventId.toString().equals(token);
    }

    public void recordResponse(Long eventId, String response) {
        // Record the response (e.g., update the event's response list)
        Event event = eventRepository.findById(eventId).orElse(null);
        if (event != null) {
            // Add response recording logic here
            eventRepository.save(event);
        }
    }
}
