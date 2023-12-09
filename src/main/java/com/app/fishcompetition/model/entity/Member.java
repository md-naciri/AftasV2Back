package com.app.fishcompetition.model.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(generator = "uuid2")
    private Long id;

    private String name;

    private String familyName;

    private Date accessDate;

    private String nationality;

    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;

    @Column(unique = true)
    private String identityNumber;

    @OneToMany(mappedBy = "member")
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "member")
    private List<Hunting> huntings;
}
