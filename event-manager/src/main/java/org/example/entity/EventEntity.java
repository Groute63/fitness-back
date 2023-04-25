package org.example.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "event")
public class EventEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    long id;

    private String title;

    private String startTime;

    private String endTime;

    @OneToMany(mappedBy = "event", cascade = CascadeType.ALL)
    private List<ExerciseEntity> exercise = new ArrayList<>();

    @Override
    public String toString() {
        return "EventEntity{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                ", exercise=" + exercise +
                '}';
    }
}
