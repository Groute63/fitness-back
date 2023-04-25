package org.example.entity.dto;

import lombok.*;

import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
public class EventResponse {
    private long id;

    private String title;

    private String start;

    private String end;

    private List<Exercise> exercises;
}
