package com.humanblend.saas.event.controller;

import com.humanblend.saas.event.entities.Event;
import com.humanblend.saas.event.service.impl.EventServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EventControllerTest {

    @Mock
    private EventServiceImpl eventService;

    @InjectMocks
    private EventController eventController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEvents_Ok() {
        // Mocking the service to return a list of events
        List<Event> events = new ArrayList<>();
        events.add(new Event(1, 1, 1, null));
        events.add(new Event(2, 1, 2, null));

        when(eventService.getAllEvents()).thenReturn(events);

        // Calling the controller method
        ResponseEntity<List<Event>> responseEntity = eventController.getAllEvents();

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(events, responseEntity.getBody());
    }

    @Test
    void testGetEventById_Ok() {
        int eventId = 1;
        Event event = new Event(eventId, 1, 1, null);

        when(eventService.getEventById(eventId)).thenReturn(event);

        ResponseEntity<Event> responseEntity = eventController.getEventById(eventId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(event, responseEntity.getBody());
    }

    @Test
    void testCreateEvent_Ok() {
        Event event = new Event(null, 1, 1, null);

        when(eventService.createEvent(event)).thenReturn(event);

        ResponseEntity<Event> responseEntity = eventController.createEvent(event);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(event, responseEntity.getBody());
    }

    @Test
    void testUpdateEvent_Ok() {
        int eventId = 1;
        Event updatedEvent = new Event(eventId, 1, 1, null);

        when(eventService.updateEvent(eventId, updatedEvent)).thenReturn(updatedEvent);

        ResponseEntity<Event> responseEntity = eventController.updateEvent(eventId, updatedEvent);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedEvent, responseEntity.getBody());
    }

    @Test
    void testDeleteEvent_Ok() {
        int eventId = 1;

        when(eventService.getEventById(eventId)).thenReturn(new Event(eventId, 1, 1, null));

        ResponseEntity<Void> responseEntity = eventController.deleteEvent(eventId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testGetAllEvents_NotFound() {
        // Mocking the service to return an empty list
        List<Event> events = new ArrayList<>();

        when(eventService.getAllEvents()).thenReturn(events);

        // Calling the controller method
        ResponseEntity<List<Event>> responseEntity = eventController.getAllEvents();

        // Verifying the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGetEventById_NotFound() {
        int eventId = 1;

        when(eventService.getEventById(eventId)).thenReturn(null);

        ResponseEntity<Event> responseEntity = eventController.getEventById(eventId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateEvent_NotFound() {
        int eventId = 1;
        Event updatedEvent = new Event(eventId, 1, 1, null);

        when(eventService.updateEvent(eventId, updatedEvent)).thenReturn(null);

        ResponseEntity<Event> responseEntity = eventController.updateEvent(eventId, updatedEvent);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteEvent_NotFound() {
        int eventId = 1;

        when(eventService.getEventById(eventId)).thenReturn(null);

        ResponseEntity<Void> responseEntity = eventController.deleteEvent(eventId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
