package com.humanblend.saas.event.service.impl;

import com.humanblend.saas.event.entities.Event;
import com.humanblend.saas.event.repository.EventRepository;
import com.humanblend.saas.event.service.EventService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventServiceImpl implements EventService {

    private final EventRepository repository;
    @Override
    public List<Event> getAllEvents() {
        return repository.findAll();
    }

    @Override
    public Event getEventById(Integer id) {
        Event event = repository.findById(id).orElse(null);

        return (event == null) ? null : event;
    }

    @Override
    public Event createEvent(Event event) {
        return repository.save(event);
    }

    @Override
    public Event updateEvent(Integer id, Event event) {
        Event currentEvent = repository.findById(id).orElse(null);

        if (currentEvent == null)
            return null;

        currentEvent.setEntryDate(event.getEntryDate());
        currentEvent.setEmployeeId(event.getEmployeeId());
        currentEvent.setTypeId(event.getTypeId());

        return repository.save(currentEvent);
    }

    @Override
    public void deleteEvent(Integer id) {
        Optional<Event> event = repository.findById(id);

        event.ifPresent(repository::delete);
    }
}
