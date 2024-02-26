package com.app.fishcompetition.model.dto.response;

import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class LoginResponse {

    private String firstName;
    private String lastName;
    private String accessToken;
    private String refreshToken;
}
