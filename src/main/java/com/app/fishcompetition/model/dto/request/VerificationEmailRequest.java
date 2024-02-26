package com.app.fishcompetition.model.dto.request;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class VerificationEmailRequest {

    @NotBlank(message = "please enter a valid code")
    private String code;

}
