package com.humanblend.saas.event_type.controller;

import com.humanblend.saas.event_type.entities.EventType;
import com.humanblend.saas.event_type.service.impl.EventTypeServiceImpl;
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

class EventTypeControllerTest {

    @Mock
    private EventTypeServiceImpl eventTypeService;

    @InjectMocks
    private EventTypeController eventTypeController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEventsTypes_Ok() {
        // Mocking the service to return a list of event types
        List<EventType> eventTypes = new ArrayList<>();
        eventTypes.add(new EventType(1, "Event Type 1", "Progresando"));
        eventTypes.add(new EventType(2, "Event Type 2", "Cancelado"));

        when(eventTypeService.getAllEventsTypes()).thenReturn(eventTypes);

        // Calling the controller method
        ResponseEntity<List<EventType>> responseEntity = eventTypeController.getAllEventsTypes();

        // Verifying the response
        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(eventTypes, responseEntity.getBody());
    }

    @Test
    void testGetEventTypeById_Ok() {
        int eventTypeId = 1;
        EventType eventType = new EventType(eventTypeId, "Event Type 1", "Cancelado");

        when(eventTypeService.getEventTypeById(eventTypeId)).thenReturn(eventType);

        ResponseEntity<EventType> responseEntity = eventTypeController.getEventTypeById(eventTypeId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(eventType, responseEntity.getBody());
    }

    @Test
    void testCreateEventType_Ok() {
        EventType eventType = new EventType(null, "New Event Type", "Completado");

        when(eventTypeService.createEventType(eventType)).thenReturn(eventType);

        ResponseEntity<EventType> responseEntity = eventTypeController.createEventType(eventType);

        assertEquals(HttpStatus.CREATED, responseEntity.getStatusCode());
        assertEquals(eventType, responseEntity.getBody());
    }

    @Test
    void testUpdateEventType_Ok() {
        int eventTypeId = 1;
        EventType updatedEventType = new EventType(eventTypeId, "Updated Event Type", "Progresando");

        when(eventTypeService.updateEventType(eventTypeId, updatedEventType)).thenReturn(updatedEventType);

        ResponseEntity<EventType> responseEntity = eventTypeController.updateEventType(eventTypeId, updatedEventType);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
        assertEquals(updatedEventType, responseEntity.getBody());
    }

    @Test
    void testGetAllEventsTypes_NotFound() {
        // Mocking the service to return an empty list
        List<EventType> eventTypes = new ArrayList<>();

        when(eventTypeService.getAllEventsTypes()).thenReturn(eventTypes);

        // Calling the controller method
        ResponseEntity<List<EventType>> responseEntity = eventTypeController.getAllEventsTypes();

        // Verifying the response
        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testGetEventTypeById_NotFound() {
        int eventTypeId = 1;

        when(eventTypeService.getEventTypeById(eventTypeId)).thenReturn(null);

        ResponseEntity<EventType> responseEntity = eventTypeController.getEventTypeById(eventTypeId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testUpdateEventType_NotFound() {
        int eventTypeId = 1;
        EventType updatedEventType = new EventType(eventTypeId, "Updated Event Type", "Completado");

        when(eventTypeService.updateEventType(eventTypeId, updatedEventType)).thenReturn(null);

        ResponseEntity<EventType> responseEntity = eventTypeController.updateEventType(eventTypeId, updatedEventType);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteTraining_Ok() {
        int eventTypeId = 1;

        when(eventTypeService.getEventTypeById(eventTypeId)).thenReturn(new EventType(eventTypeId, "Event Type to Delete", "Completado"));

        ResponseEntity<Void> responseEntity = eventTypeController.deleteTraining(eventTypeId);

        assertEquals(HttpStatus.OK, responseEntity.getStatusCode());
    }

    @Test
    void testDeleteTraining_NotFound() {
        int eventTypeId = 1;

        when(eventTypeService.getEventTypeById(eventTypeId)).thenReturn(null);

        ResponseEntity<Void> responseEntity = eventTypeController.deleteTraining(eventTypeId);

        assertEquals(HttpStatus.NOT_FOUND, responseEntity.getStatusCode());
    }
}
