package com.humanblend.saas.event.controller;

import com.humanblend.saas.event.entities.Event;
import com.humanblend.saas.event.service.impl.EventServiceImpl;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@CrossOrigin("*")
@RequestMapping("${request-mapping.controller.events}")
@RequiredArgsConstructor
public class EventController {

    private final EventServiceImpl service;

    @GetMapping()
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = service.getAllEvents();

        if (events != null && !events.isEmpty())
            return new ResponseEntity<>(events, HttpStatus.OK);
        else
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Event> getEventById(@PathVariable Integer id) {
        Event event = service.getEventById(id);

        if (event == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(event, HttpStatus.OK);
    }

    @PostMapping("/insert")
    public ResponseEntity<Event> createEvent(@RequestBody Event training) {
        return new ResponseEntity<>(service.createEvent(training), HttpStatus.CREATED);
    }

    @PutMapping("/update/{id}")
    public ResponseEntity<Event> updateEvent(@PathVariable Integer id, @RequestBody Event training) {
        Event updatedEvent = service.updateEvent(id, training);

        if (updatedEvent == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else
            return new ResponseEntity<>(updatedEvent, HttpStatus.OK);
    }

    @DeleteMapping("/deleteEvent/{id}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Integer id) {
        Event event = service.getEventById(id);
        if (event == null)
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        else {
            service.deleteEvent(id);
            return new ResponseEntity<>(HttpStatus.OK);
        }
    }

}
