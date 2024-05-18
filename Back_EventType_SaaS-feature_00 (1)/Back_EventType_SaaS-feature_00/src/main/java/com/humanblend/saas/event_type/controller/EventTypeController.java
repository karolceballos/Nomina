package com.humanblend.saas.event_type.controller;

import com.humanblend.saas.event_type.entities.EventType;
import com.humanblend.saas.event_type.service.impl.EventTypeServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("${request-mapping.controller.eventTypes}")
@RequiredArgsConstructor
public class EventTypeController {

    private final EventTypeServiceImpl service;

    @GetMapping()
    public ResponseEntity<List<EventType>> getAllEventsTypes() {
        List<EventType> eventTypes = service.getAllEventsTypes();

        if (eventTypes != null && !eventTypes.isEmpty())
            return new ResponseEntity<>(eventTypes, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventType> getEventTypeById(@PathVariable Integer id) {
        EventType eventType = service.getEventTypeById(id);

        if (eventType == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(eventType, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<EventType> createEventType(@RequestBody EventType training) {
        return new ResponseEntity<>(service.createEventType(training), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<EventType> updateEventType(@PathVariable Integer id, @RequestBody EventType eventType) {
        EventType updatedEventType = service.updateEventType(id, eventType);

        if (updatedEventType == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(updatedEventType, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEventType/{id}")
    public ResponseEntity<Void> deleteTraining(@PathVariable Integer id) {
        EventType eventType = service.getEventTypeById(id);
        if (eventType == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            service.deleteEventType(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }
}
