package com.humanblend.saas.event_type.service.impl;

import com.humanblend.saas.event_type.entities.EventType;
import com.humanblend.saas.event_type.repository.EventTypeRepository;
import com.humanblend.saas.event_type.service.EventTypeService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventTypeServiceImpl implements EventTypeService {
    private final EventTypeRepository repository;

    @Override
    public List<EventType> getAllEventsTypes() {
        return repository.findAll();
    }

    @Override
    public EventType getEventTypeById(Integer id) {
        EventType eventType = repository.findById(id).orElse(null);
        return (eventType == null) ? null : eventType;
    }

    @Override
    public EventType createEventType(EventType eventType) {
        return repository.save(eventType);
    }

    @Override
    public EventType updateEventType(Integer id, EventType eventType) {
        EventType currentEventType = repository.findById(id).orElse(null);

        if (currentEventType == null)
            return null;

        currentEventType.setDescription(eventType.getDescription());
        currentEventType.setStatus(eventType.getStatus());

        return repository.save(currentEventType);
    }

    @Override
    public void deleteEventType(Integer id) {
        Optional<EventType> eventType = repository.findById(id);
        eventType.ifPresent(repository::delete);
    }
}
