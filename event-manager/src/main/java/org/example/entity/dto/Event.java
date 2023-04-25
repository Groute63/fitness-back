package org.example.entity.dto;

import lombok.*;
import org.example.entity.ExerciseEntity;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class Event {
    private long id;

    private String title;

    private String start;

    private String end;

    private String date;

    private List<Exercise> exercises;
}
