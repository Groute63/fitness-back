package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.dto.ErrorResponse;
import org.example.entity.dto.Event;
import org.example.entity.dto.EventResponse;
import org.example.entity.dto.Exercise;
import org.example.exception.NotFoundException;
import org.example.services.impl.EventService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController()
@RequestMapping("/events")
@RequiredArgsConstructor
public class EventController {
    private final EventService eventService;

    @GetMapping("/{id}")
    public EventResponse getEventById(@PathVariable Long id) throws NotFoundException {
        return eventService.getEventById(id);
    }

    @GetMapping
    public List<EventResponse> getAllEvent() {
        return eventService.getAllEvent();
    }

    @PostMapping
    public void addEvent(@RequestBody Event request) throws ParseException {
        eventService.addEvent(request);
    }

    @PutMapping("/{id}")
    public void updateEvent(@PathVariable int id, @RequestBody Event request) throws ParseException {
        eventService.addEvent(id,request);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleUserRegistrationException(NotFoundException ex) {
        return ResponseEntity
                .badRequest()
                .body(new org.example.entity.dto.ErrorResponse(ex.getMessage()));
    }
}
