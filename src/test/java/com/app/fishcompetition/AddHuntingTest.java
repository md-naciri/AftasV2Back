package com.app.fishcompetition;

import com.app.fishcompetition.common.exceptions.custom.CompetitionTimeException;
import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.model.entity.Fish;
import com.app.fishcompetition.model.entity.Hunting;
import com.app.fishcompetition.model.entity.Member;
import com.app.fishcompetition.repositories.HuntingRepository;
import com.app.fishcompetition.services.HuntingService;
import com.app.fishcompetition.services.MemberService;
import com.app.fishcompetition.services.impls.CompetitionServiceImpl;
import com.app.fishcompetition.services.impls.FishServiceImpl;
import com.app.fishcompetition.services.impls.HuntingServiceImpl;
import com.app.fishcompetition.services.impls.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;

import java.time.LocalDate;
import java.util.Date;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.UUID;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@SpringBootTest
public class AddHuntingTest {

    @Mock
    private MemberServiceImpl memberService;

    @Mock
    private HuntingRepository huntingRepository;

    @Mock
    private CompetitionServiceImpl competitionService;

    @Mock
    private FishServiceImpl fishService;

    @InjectMocks
    private HuntingServiceImpl huntingService;
    @Test
    void testAddHunting_memberNotExist() {
            UUID memberId = UUID.randomUUID();
            Hunting hunting = Hunting.builder().member(Member.builder().id(memberId).build()).build();

            when(memberService.getMemberById(memberId)).thenReturn(Optional.empty());

            assertThrows(NoSuchElementException.class, () -> huntingService.addHunting(hunting, 1.0));
    }
    @Test
    void testAddHuntingCompetitionNotExist() {
        UUID competitionId = UUID.randomUUID();
        Member member = Member.builder().id(UUID.fromString("94e6a25c-b2f8-4945-ad89-dfa5b8f5c140")).build();
        Hunting hunting = Hunting.builder()
                .competition(Competition.builder().id(competitionId).build())
                .member(member)
                .build();

        when(competitionService.getCompetitionById(competitionId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> huntingService.addHunting(hunting, 1.0));
    }

    @Test
    void testAddHuntingFishNotExist(){
        UUID fishId = UUID.randomUUID();
        Member member = Member.builder().id(UUID.fromString("94e6a25c-b2f8-4945-ad89-dfa5b8f5c140")).build();
        Competition competition = Competition.builder().id(UUID.fromString("1c800c8e-063f-4e7c-a4c7-1bfdae97f8ca")).build();
        Fish fish = Fish.builder().id(UUID.randomUUID()).build();
        Hunting hunting = Hunting.builder().competition(competition).member(member).fish(fish).build();

        when(fishService.getFishById(fishId)).thenReturn(Optional.empty());

        assertThrows(NoSuchElementException.class, () -> huntingService.addHunting(hunting, 1.0));
    }
    @Test
    void testIAddHuntingCompetitionDayException() {

        UUID memberId = UUID.fromString("94e6a25c-b2f8-4945-ad89-dfa5b8f5c140");
        UUID fishId = UUID.fromString("13d14a6f-0397-4df3-8465-b409f8ff65b0");
        UUID competitionId = UUID.fromString("1c800c8e-063f-4e7c-a4c7-1bfdae97f8ca");

        LocalDate competitionDate = LocalDate.of(2023, 1, 1);
        Date date = java.sql.Date.valueOf(competitionDate);
        Competition competition = Competition.builder().id(competitionId).date(date).build();

        Member member = Member.builder().id(memberId).build();
        Fish fish = Fish.builder().id(fishId).build();
        Hunting hunting = Hunting.builder().competition(competition).member(member).fish(fish).build();

        when(memberService.getMemberById(memberId)).thenReturn(Optional.of(member));
        when(fishService.getFishById(fishId)).thenReturn(Optional.of(fish));
        when(competitionService.getCompetitionById(competitionId)).thenReturn(Optional.of(competition));

        assertThrows(CompetitionTimeException.class, () -> huntingService.addHunting(hunting, 1.0));
    }

}
