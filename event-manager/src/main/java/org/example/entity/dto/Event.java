package org.example.entity.dto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import lombok.*;
import org.example.entity.Token;

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
