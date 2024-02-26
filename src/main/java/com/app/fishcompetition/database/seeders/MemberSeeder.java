package com.app.fishcompetition.database.seeders;

import com.app.fishcompetition.enums.IdentityDocumentType;
import com.app.fishcompetition.enums.Nationality;
import com.app.fishcompetition.enums.Provider;
import com.app.fishcompetition.model.entity.Member;
import com.app.fishcompetition.repositories.MemberRepository;
import com.app.fishcompetition.services.security.AddRoleToUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;

@Component
@RequiredArgsConstructor
public class MemberSeeder {

    private final MemberRepository userRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddRoleToUserService addRoleToUserService;

    public void seed(){
        if(userRepository.count() == 0){
            List<Member> users = List.of(
                Member.builder()
                    .firstName("John")
                    .lastName("Doe")
                    .email("john123@gmail.com")
                    .accountNonLocked(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .identityDocumentType(IdentityDocumentType.DRIVING_LICENSE)
                    .identityNumber("1234567890")
                    .nationality(Nationality.ca)
                    .provider(Provider.local)
                    .password(passwordEncoder.encode("1234Password@!"))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build(),
                Member.builder()
                    .firstName("Jane")
                    .lastName("Doe")
                    .email("jane456@gmail.com")
                    .accountNonLocked(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .identityDocumentType(IdentityDocumentType.IDENTITY_CARD)
                    .identityNumber("B32459")
                    .nationality(Nationality.ma)
                    .provider(Provider.local)
                    .password(passwordEncoder.encode("1234Password@!"))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build(),
                 Member.builder()
                    .firstName("Mike")
                    .lastName("Smith")
                    .email("mike789@gmail.com")
                    .accountNonLocked(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                     .identityDocumentType(IdentityDocumentType.PASSPORT)
                     .identityNumber("A1234567")
                     .nationality(Nationality.br)
                    .provider(Provider.local)
                    .password(passwordEncoder.encode("1234Password@!"))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build(),
                 Member.builder()
                    .firstName("Sarah")
                    .lastName("Williams")
                    .email("sarah1011@gmail.com")
                    .accountNonLocked(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                     .identityDocumentType(IdentityDocumentType.DRIVING_LICENSE)
                     .identityNumber("23D382")
                     .nationality(Nationality.dz)
                    .provider(Provider.local)
                    .password(passwordEncoder.encode("1234Password@!"))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build(),
                 Member.builder()
                    .firstName("Robert")
                    .lastName("Johnson")
                    .email("rob1213@gmail.com")
                    .accountNonLocked(true)
                    .accountNonExpired(true)
                    .credentialsNonExpired(true)
                    .enabled(true)
                    .identityDocumentType(IdentityDocumentType.IDENTITY_CARD)
                    .identityNumber("C32459")
                    .nationality(Nationality.ma)
                    .provider(Provider.local)
                    .password(passwordEncoder.encode("1234Password@!"))
                    .createdAt(LocalDateTime.now())
                    .updatedAt(LocalDateTime.now())
                    .build()
            );
            userRepository.saveAll(users);
            assignRolesToUsers();
        }
    }
    private void assignRolesToUsers(){

        addRoleToUserService.addRoleToUser("john123@gmail.com", "ROLE_MANAGER");
        addRoleToUserService.addRoleToUser("john123@gmail.com", "ROLE_ADHERENT");
        addRoleToUserService.addRoleToUser("john123@gmail.com", "ROLE_JURY");

        addRoleToUserService.addRoleToUser("jane456@gmail.com", "ROLE_ADHERENT");
        addRoleToUserService.addRoleToUser("jane456@gmail.com", "ROLE_JURY");

        addRoleToUserService.addRoleToUser("mike789@gmail.com", "ROLE_ADHERENT");
        addRoleToUserService.addRoleToUser("mike789@gmail.com", "ROLE_JURY");

        addRoleToUserService.addRoleToUser("sarah1011@gmail.com", "ROLE_JURY");

        addRoleToUserService.addRoleToUser("rob1213@gmail.com", "ROLE_ADHERENT");

    }
}
