package org.example.mapper;

import org.example.entity.ExerciseEntity;
import org.example.entity.dto.Event;
import org.example.entity.dto.Exercise;
import org.springframework.stereotype.Component;

@Component
public class ExerciseMapper {
    public static ExerciseEntity exerciseToExerciseEntity(Exercise exercise){
        return new ExerciseEntity(exercise.getId(),exercise.getTitle(), exercise.getSets(), exercise.getRepeats(), null);
    }

    public static ExerciseEntity exerciseToExerciseEntity(Exercise exercise, Event event){
        //todo доделать
        return new ExerciseEntity(exercise.getId(),exercise.getTitle(), exercise.getSets(), exercise.getRepeats(), null);
    }

    public static Exercise exerciseEntityToExercise(ExerciseEntity exerciseEntity){
        return new Exercise(exerciseEntity.getId(),exerciseEntity.getTitle(), exerciseEntity.getSets(), exerciseEntity.getRepeats());
    }
}
