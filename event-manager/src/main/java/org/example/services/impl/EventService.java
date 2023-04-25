package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.EventEntity;
import org.example.entity.ExerciseEntity;
import org.example.entity.Token;
import org.example.entity.dto.Event;
import org.example.entity.dto.EventResponse;
import org.example.entity.dto.Exercise;
import org.example.exception.NotFoundException;
import org.example.mapper.EventMapper;
import org.example.mapper.ExerciseMapper;
import org.example.repository.EventRepository;
import org.example.repository.ExerciseRepository;
import org.example.repository.TokenRepo;
import org.example.services.TokenService;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class EventService {
    private final EventRepository eventRepository;
    private final TokenRepo tokenRepo;

    public List<EventResponse> getAllEvent(String header) {
        header = header.substring(7);
        Optional<Token> token = tokenRepo.findByToken(header);
        Iterable<EventEntity> iterable = eventRepository.findAllKEK(token.orElseThrow().getId());
        ArrayList<EventResponse> events = new ArrayList<>();
        for (EventEntity eventEntity : iterable) {
            events.add(EventMapper.eventEntityToEvent(eventEntity));
        }
        return events;
    }

    public EventResponse getEventById(String header,Long id) throws NotFoundException {
        EventEntity eventEntity = eventRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("event not found: id = " + id));
        return EventMapper.eventEntityToEvent(eventEntity);
    }

    public void addEvent(String header,Event event) throws ParseException {
        header = header.substring(7);
        Optional<Token> token = tokenRepo.findByToken(header);
        EventEntity eventEntity = EventMapper.eventToEventEntity(event, token.get());
        eventRepository.save(eventEntity);
    }

    public void addEvent(String header,int id, Event event) throws ParseException {
        header = header.substring(7);
        Optional<Token> token = tokenRepo.findByToken(header);
        event.setId(id);
        EventEntity eventEntity = EventMapper.eventToEventEntity(event,token.get());
        eventRepository.deleteById((long) id);
        eventRepository.save(eventEntity);
    }

    public void deleteEvent(String header, int id){
        header = header.substring(7);
        eventRepository.deleteById((long) id);
    }
}