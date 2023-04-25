package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.EventEntity;
import org.example.entity.ExerciseEntity;
import org.example.entity.dto.Event;
import org.example.entity.dto.EventResponse;
import org.example.entity.dto.Exercise;
import org.example.exception.NotFoundException;
import org.example.mapper.EventMapper;
import org.example.mapper.ExerciseMapper;
import org.example.repository.EventRepository;
import org.example.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;

    public List<EventResponse> getAllEvent() {
        Iterable<EventEntity> iterable = eventRepository.findAll();
        ArrayList<EventResponse> events = new ArrayList<>();
        for (EventEntity eventEntity : iterable) {
            events.add(EventMapper.eventEntityToEvent(eventEntity));
        }
        return events;
    }

    public EventResponse getEventById(Long id) throws NotFoundException {
        EventEntity eventEntity = eventRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("event not found: id = " + id));
        return EventMapper.eventEntityToEvent(eventEntity);
    }

    public void addEvent(Event event) throws ParseException {
        EventEntity eventEntity = EventMapper.eventToEventEntity(event);
        eventRepository.save(eventEntity);
    }

    public void addEvent(int id, Event event) throws ParseException {
        event.setId(id);
        EventEntity eventEntity = EventMapper.eventToEventEntity(event);
        eventRepository.deleteById((long) id);
        eventRepository.save(eventEntity);
    }
}