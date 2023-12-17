package com.app.fishcompetition.model.entity;


import com.fasterxml.jackson.annotation.*;
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
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id",scope = Fish.class)
public class Fish {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    @Column(unique = true)
    private String name;

    private double averageWeight;

    @OneToMany( mappedBy = "fish")
    private List<Hunting> huntings;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.REMOVE})
    private Level level;
}