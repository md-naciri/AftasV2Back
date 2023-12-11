package com.app.fishcompetition.model.entity;

import com.app.fishcompetition.enums.IdentityDocumentType;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String firstName;

    private String lastName;


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
