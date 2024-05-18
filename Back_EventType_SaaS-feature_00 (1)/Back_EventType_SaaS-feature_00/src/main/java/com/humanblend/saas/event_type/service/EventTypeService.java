package com.humanblend.saas.event_type.service;

import com.humanblend.saas.event_type.entities.EventType;

import java.util.List;

public interface EventTypeService {
    List<EventType> getAllEventsTypes();
    EventType getEventTypeById(Integer id);
    EventType createEventType(EventType eventType);
    EventType updateEventType(Integer id, EventType eventType);
    void deleteEventType(Integer id);

}
