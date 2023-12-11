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

    @Column(unique = true)
    private String code;

    @Column(unique = true)
    private int level;

    @Column(length = 500)
    private String description;

    @Column(unique = true)
    private int points;

    @OneToMany(mappedBy = "level")
    private List<Fish> fishes;
}
