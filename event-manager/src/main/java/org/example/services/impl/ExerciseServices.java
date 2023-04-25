package org.example.services.impl;

import lombok.RequiredArgsConstructor;
import org.example.entity.ExerciseEntity;
import org.example.entity.dto.Exercise;
import org.example.exception.NotFoundException;
import org.example.mapper.ExerciseMapper;
import org.example.repository.ExerciseRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class ExerciseServices {
    private final ExerciseRepository exerciseRepository;

    public List<Exercise> getAllExercise() {
        Iterable<ExerciseEntity> iterable = exerciseRepository.findAll();
        ArrayList<Exercise> exercises = new ArrayList<>();
        for (ExerciseEntity exerciseEntity : iterable) {
            exercises.add(ExerciseMapper.exerciseEntityToExercise(exerciseEntity));
        }
        return exercises;
    }

    public Exercise getExerciseById(Long id) throws NotFoundException {
        ExerciseEntity exerciseEntity = exerciseRepository
                .findById(id)
                .orElseThrow(() -> new NotFoundException("exercise not found: id = " + id));
        return ExerciseMapper.exerciseEntityToExercise(exerciseEntity);
    }

    public void addExercise(Exercise exercise) {;
        ExerciseEntity exerciseEntity = ExerciseMapper.exerciseToExerciseEntity(exercise);
        exerciseRepository.save(exerciseEntity);
    }
}
