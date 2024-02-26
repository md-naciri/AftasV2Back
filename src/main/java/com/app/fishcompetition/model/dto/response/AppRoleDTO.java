package com.app.fishcompetition.model.dto.response;

import lombok.*;

import java.util.List;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AppRoleDTO {

    private String name;
    private List<AppPermissionDTO> permissions;
}
