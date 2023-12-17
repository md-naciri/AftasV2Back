package com.app.fishcompetition.model.dto;

import com.app.fishcompetition.common.validation.ValidIdentityDocumentType;
import com.app.fishcompetition.enums.IdentityDocumentType;
import com.app.fishcompetition.model.entity.Hunting;
import com.app.fishcompetition.model.entity.Ranking;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.PastOrPresent;
import jakarta.validation.constraints.Size;
import lombok.Data;

import java.util.Date;
import java.util.List;

@Data
public class MemberDto {

    @NotBlank(message = "first name  is required")
    @Size(min = 2, max = 50, message = "Name must be between 2 and 50 characters")
    private String firstName;

    @NotBlank(message = "last name name  is required")
    @Size(min = 2, max = 50, message = "Family name must be between 2 and 50 characters")
    private String lastName;

    private Date accessDate;

    @NotBlank(message = "nationality  is mandatory")
    private String nationality;

    @NotNull(message = "Identity document type cannot be null")
    private IdentityDocumentType identityDocumentType;

    @NotNull(message = "Identity number cannot be null")
    @Size(min = 2, max = 50, message = "Identity number must be between 2 and 50 characters")
    private String identityNumber;
}
