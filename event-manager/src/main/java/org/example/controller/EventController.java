package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.dto.ErrorResponse;
import org.example.entity.dto.Event;
import org.example.entity.dto.EventResponse;
import org.example.entity.dto.Exercise;
import org.example.exception.NotFoundException;
import org.example.services.impl.EventService;
import org.springframework.http.HttpHeaders;
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
    public EventResponse getEventById(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @PathVariable Long id) throws NotFoundException {
        return eventService.getEventById(authorization,id);
    }

    @GetMapping
    public List<EventResponse> getAllEvent(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization) {
        return eventService.getAllEvent(authorization);
    }

    @PostMapping
    public void addEvent(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization,@RequestBody Event request) throws ParseException {
        eventService.addEvent(authorization,request);
    }

    @PutMapping("/{id}")
    public void updateEvent(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @PathVariable int id, @RequestBody Event request) throws ParseException {
        eventService.addEvent(authorization,id,request);
    }

    @DeleteMapping("/{id}")
    public void deleteEvent(@RequestHeader(HttpHeaders.AUTHORIZATION) String authorization, @PathVariable int id) throws ParseException {
        eventService.deleteEvent(authorization,id);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleUserRegistrationException(NotFoundException ex) {
        return ResponseEntity
                .badRequest()
                .body(new org.example.entity.dto.ErrorResponse(ex.getMessage()));
    }
}
