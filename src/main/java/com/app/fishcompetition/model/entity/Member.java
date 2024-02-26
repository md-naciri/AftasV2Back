package com.app.fishcompetition.model.entity;

import com.app.fishcompetition.enums.IdentityDocumentType;
import com.app.fishcompetition.enums.Nationality;
import com.app.fishcompetition.enums.Provider;
import com.fasterxml.jackson.annotation.*;
import jakarta.persistence.*;
import lombok.*;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Member implements UserDetails {
    @Id
    @GeneratedValue(generator = "uuid2")
    private UUID id;

    private String firstName;

    private String lastName;

    @Column(unique = true)
    private String email;

    private String verificationCode;

    private String password;

    private String imageUrl;

    private boolean accountNonExpired=true ;

    private boolean accountNonLocked =true;

    private boolean credentialsNonExpired=true ;

    private boolean enabled =true;

    @Column(name = "provider_id")
    private String providerId;

    @Enumerated(EnumType.STRING)
    private Provider provider;

    @Enumerated(EnumType.STRING)
    private Nationality nationality;

    @Enumerated(EnumType.STRING)
    private IdentityDocumentType identityDocumentType;

    @Column(unique = true)
    private String identityNumber;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    @ManyToMany(mappedBy = "users",fetch = FetchType.EAGER)
    private List<AppRole> authorities = new ArrayList<>();

    @Override
    public String getUsername() {
        return email;
    }
    @OneToMany(mappedBy = "member")
    private List<Ranking> rankings;

    @OneToMany(mappedBy = "member")
    private List<Hunting> huntings;
}
