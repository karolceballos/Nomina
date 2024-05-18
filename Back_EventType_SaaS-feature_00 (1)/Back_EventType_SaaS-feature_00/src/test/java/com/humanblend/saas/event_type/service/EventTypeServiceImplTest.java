package com.humanblend.saas.event_type.service;

import com.humanblend.saas.event_type.entities.EventType;
import com.humanblend.saas.event_type.repository.EventTypeRepository;
import com.humanblend.saas.event_type.service.impl.EventTypeServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class EventTypeServiceImplTest {

    @Mock
    private EventTypeRepository eventTypeRepository;

    @InjectMocks
    private EventTypeServiceImpl eventTypeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAllEventsTypes() {
        // Mocking the repository to return a list of event types
        when(eventTypeRepository.findAll()).thenReturn(null);

        // Calling the service method
        assertNull(eventTypeService.getAllEventsTypes());
    }

    @Test
    void testGetEventTypeById_NullId() {
        int id = 1;
        when(eventTypeRepository.findById(id)).thenReturn(Optional.empty());

        // Calling the service method
        assertNull(eventTypeService.getEventTypeById(id));
    }

    @Test
    void testCreateEventType() {
        EventType eventType = new EventType(1, "Test Event Type", "Cancelado");

        // Mocking the repository to return the saved event type
        when(eventTypeRepository.save(eventType)).thenReturn(eventType);

        // Calling the service method
        EventType result = eventTypeService.createEventType(eventType);

        // Verifying the result
        assertNotNull(result);
        assertEquals(eventType.getDescription(), result.getDescription());
    }

    @Test
    void testUpdateEventType_NullId() {
        int id = 1;
        EventType eventType = new EventType(id, "Updated Event Type", "Completado");

        when(eventTypeRepository.findById(id)).thenReturn(Optional.empty());

        // Calling the service method
        assertNull(eventTypeService.updateEventType(id, eventType));
    }

    @Test
    void testUpdateEventType() {
        int id = 1;
        EventType currentEventType = new EventType(id, "Existing Event Type", "Inactivo");
        EventType updatedEventType = new EventType(id, "Updated Event Type", "en progreso");

        when(eventTypeRepository.findById(id)).thenReturn(Optional.of(currentEventType));
        when(eventTypeRepository.save(currentEventType)).thenReturn(updatedEventType);

        // Calling the service method
        EventType result = eventTypeService.updateEventType(id, updatedEventType);

        // Verifying the result
        assertNotNull(result);
        assertEquals(updatedEventType.getDescription(), result.getDescription());
    }

    @Test
    void testDeleteEventType_NullId() {
        int id = 1;
        when(eventTypeRepository.findById(id)).thenReturn(Optional.empty());

        // Calling the service method
        eventTypeService.deleteEventType(id);

        // Verifying that delete method of repository is not called
        verify(eventTypeRepository, never()).delete(any());
    }

    @Test
    void testDeleteEventType() {
        int id = 1;
        EventType eventType = new EventType(id, "Event Type to Delete", "en progreso");

        when(eventTypeRepository.findById(id)).thenReturn(Optional.of(eventType));

        // Calling the service method
        eventTypeService.deleteEventType(id);

        // Verifying that delete method of repository is called
        verify(eventTypeRepository, times(1)).delete(eventType);
    }
}
