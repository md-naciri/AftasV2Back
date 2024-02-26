package com.app.fishcompetition.model.dto;


import com.app.fishcompetition.model.dto.response.MemberDTO;
import jakarta.validation.constraints.NotNull;
import lombok.Data;

@Data
public class RankingDto {


    private Integer rank;
    private Integer score;

    @NotNull(message = "Member is mandatory")
    private MemberDTO member;

    @NotNull(message = "Competition is mandatory")
    private CompetitionDto competition;
}
