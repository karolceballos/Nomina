package com.humanblend.saas.event.service;

import com.humanblend.saas.event.entities.Event;

import java.util.List;

public interface EventService {
    List<Event> getAllEvents();
    Event getEventById(Integer id);
    Event createEvent(Event event);
    Event updateEvent(Integer id, Event event);
    void deleteEvent(Integer id);
}
