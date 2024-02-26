package com.app.fishcompetition.model.dto.request;

import com.app.fishcompetition.enums.IdentityDocumentType;
import com.app.fishcompetition.enums.Nationality;
import jakarta.validation.constraints.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
public class SignupRequest {

    @NotBlank(message = "First name is required")
    @Size(min=2,max=20,message="First name must be between 2 and 20 characters")
    private String firstName;

    @NotBlank(message ="Last name is required")
    @Size(min=2,max=20,message="Last name must be between 2 and 20 characters")
    private String lastName;

    @NotBlank(message ="email is required")
    @Email
    private String email;

    @NotNull(message = "Identity document is required")
    private Nationality nationality;

    @NotNull(message = "Identity document type cannot be null")
    private IdentityDocumentType identityDocumentType;

    @NotNull(message = "Identity number cannot be null")
    @Size(min = 2, max = 50, message = "Identity number must be between 2 and 50 characters")
    private String identityNumber;

    @NotBlank(message ="password is required")
    @Size(min=8,max=30,message = "Password must be between  6 and 30 characters")
    @Pattern(regexp ="^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$",message ="password must be min 8 and max 30 length containing at least 1 uppercase, 1 lowercase, 1 special character and 1 digit")
    private String password;
}
