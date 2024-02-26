package com.app.fishcompetition.model.dto.response;

import com.app.fishcompetition.enums.IdentityDocumentType;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class MemberDTO {

    private UUID id;
    private String firstName;
    private String lastName;
    private String email;
    private boolean accountNonExpired;
    private boolean accountNonLocked;
    private boolean credentialsNonExpired;
    private boolean enabled;
    private String nationality;
    private IdentityDocumentType identityDocumentType;
    private String identityNumber;
    private LocalDateTime  createdAt;
    private LocalDateTime updatedAt;

    private List<AppRoleDTO> authorities;
}
