package org.example.entity.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.example.entity.ExerciseEntity;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Event {
    long id;
    String title;
    Date start;
    Date end;
    ExerciseEntity[] exercise;
}
