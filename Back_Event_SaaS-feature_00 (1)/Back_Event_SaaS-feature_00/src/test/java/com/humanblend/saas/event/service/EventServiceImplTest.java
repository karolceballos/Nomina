package com.humanblend.saas.event.service;

import com.humanblend.saas.event.entities.Event;
import com.humanblend.saas.event.repository.EventRepository;
import com.humanblend.saas.event.service.impl.EventServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventServiceImplTest {

    @Mock
    private EventRepository eventRepository;

    @InjectMocks
    private EventServiceImpl eventService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEvents() {
        // Mocking the repository to return a list of events
        List<Event> events = new ArrayList<>();
        events.add(new Event(1, 1, 1, null));
        events.add(new Event(2, 1, 2, null));

        when(eventRepository.findAll()).thenReturn(events);

        // Calling the service method
        List<Event> result = eventService.getAllEvents();

        // Verifying the result
        assertNotNull(result);
        assertEquals(events.size(), result.size());
        assertEquals(events.get(0), result.get(0));
        assertEquals(events.get(1), result.get(1));
    }

    @Test
    void testGetEventById_NullId() {
        int id = 1;

        when(eventRepository.findById(id)).thenReturn(Optional.empty());

        // Calling the service method
        assertNull(eventService.getEventById(id));
    }

    @Test
    void testCreateEvent() {
        Event event = new Event(null, 1, 1, null);

        // Mocking the repository to return the saved event
        when(eventRepository.save(event)).thenReturn(event);

        // Calling the service method
        Event result = eventService.createEvent(event);

        // Verifying the result
        assertNotNull(result);
        assertEquals(event.getEmployeeId(), result.getEmployeeId());
        assertEquals(event.getTypeId(), result.getTypeId());
        assertEquals(event.getEntryDate(), result.getEntryDate());
    }

    @Test
    void testUpdateEvent_NullId() {
        int id = 1;
        Event event = new Event(id, 1, 1, null);

        when(eventRepository.findById(id)).thenReturn(Optional.empty());

        // Calling the service method
        assertNull(eventService.updateEvent(id, event));
    }

    @Test
    void testUpdateEvent() {
        int id = 1;
        Event currentEvent = new Event(id, 1, 1, null);
        Event updatedEvent = new Event(id, 2, 2, null);

        when(eventRepository.findById(id)).thenReturn(Optional.of(currentEvent));
        when(eventRepository.save(currentEvent)).thenReturn(updatedEvent);

        // Calling the service method
        Event result = eventService.updateEvent(id, updatedEvent);

        // Verifying the result
        assertNotNull(result);
        assertEquals(updatedEvent.getEmployeeId(), result.getEmployeeId());
        assertEquals(updatedEvent.getTypeId(), result.getTypeId());
        assertEquals(updatedEvent.getEntryDate(), result.getEntryDate());
    }

    @Test
    void testDeleteEvent() {
        int id = 1;

        // Mocking the repository to return an optional event when findById is called
        when(eventRepository.findById(id)).thenReturn(Optional.of(new Event()));

        // Calling the service method
        eventService.deleteEvent(id);

        // Verifying that findById method of repository is called exactly once with the specified id
        verify(eventRepository, times(1)).findById(id);

    }
}