package org.example.mapper;

import org.example.entity.EventEntity;
import org.example.entity.ExerciseEntity;
import org.example.entity.Token;
import org.example.entity.dto.Event;
import org.example.entity.dto.EventResponse;
import org.example.entity.dto.Exercise;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class EventMapper {

    public static EventEntity eventToEventEntity(Event event) throws ParseException {
        String start = event.getDate().substring(0, 11) + event.getStart() + ":00.000Z";
        String end = event.getDate().substring(0, 11) + event.getEnd() + ":00.000Z";
        EventEntity result = new EventEntity();
        List<ExerciseEntity> exercises = event.getExercises() != null ? event.getExercises().stream()
                .map(ExerciseMapper::exerciseToExerciseEntity)
                .toList() : new ArrayList<>();
        for (ExerciseEntity exercise : exercises) {
            exercise.setEvent(result);
        }
        result.setId(event.getId());
        result.setTitle(event.getTitle());
        result.setStartTime(start);
        result.setEndTime(end);
        result.setExercise(exercises);
        return result;
    }

    public static EventEntity eventToEventEntity(Event event, Token token) throws ParseException {
        String start = event.getDate().substring(0, 11) + event.getStart() + ":00.000Z";
        String end = event.getDate().substring(0, 11) + event.getEnd() + ":00.000Z";
        EventEntity result = new EventEntity();
        List<ExerciseEntity> exercises = event.getExercises() != null ? event.getExercises().stream()
                .map(ExerciseMapper::exerciseToExerciseEntity)
                .toList() : new ArrayList<>();
        for (ExerciseEntity exercise : exercises) {
            exercise.setEvent(result);
        }
        result.setId(event.getId());
        result.setTitle(event.getTitle());
        result.setStartTime(start);
        result.setEndTime(end);
        result.setExercise(exercises);
        result.setToken(token);
        return result;
    }


    public static EventResponse eventEntityToEvent(EventEntity event) {
        return new EventResponse(event.getId(),
                event.getTitle(),
                event.getStartTime(),
                event.getEndTime(),
                event.getExercise().stream().map(ExerciseMapper::exerciseEntityToExercise).toList()
        );
    }
}
