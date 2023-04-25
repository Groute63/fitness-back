package org.example.controller;

import lombok.RequiredArgsConstructor;
import org.example.entity.dto.ErrorResponse;
import org.example.entity.dto.Exercise;
import org.example.exception.NotFoundException;
import org.example.services.impl.ExerciseServices;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController()
@RequestMapping("/exercise")
@RequiredArgsConstructor
public class ExerciseController {
    private final ExerciseServices exerciseServices;

    @GetMapping("/{id}")
    public Exercise getExerciseById(@PathVariable Long id) throws NotFoundException {
        return exerciseServices.getExerciseById(id);
    }

    @GetMapping
    public List<Exercise> getAllExercise() {
        return exerciseServices.getAllExercise();
    }

    @PostMapping
    public void addExercise(@RequestBody Exercise request) {
        exerciseServices.addExercise(request);
    }

    @PutMapping
    public void updateExercise(@RequestBody Exercise request) {
        exerciseServices.addExercise(request);
    }

    @ExceptionHandler({NotFoundException.class})
    public ResponseEntity<ErrorResponse> handleUserRegistrationException(NotFoundException ex) {
        return ResponseEntity
                .badRequest()
                .body(new org.example.entity.dto.ErrorResponse(ex.getMessage()));
    }
}
