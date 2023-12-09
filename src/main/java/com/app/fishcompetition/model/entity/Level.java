package com.app.fishcompetition.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Level {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private int level;

    private String description;

    private int points;

    @OneToMany(mappedBy = "level")
    private List<Fish> fishes;
}
