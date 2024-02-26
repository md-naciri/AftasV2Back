package com.app.fishcompetition.model.entity;

import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.io.Serializable;
import java.util.UUID;

@Data
@Embeddable
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class RankingKey implements Serializable {
    private UUID member_id;
    private UUID competition_id;
}