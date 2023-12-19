package com.app.fishcompetition;

import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.model.entity.Fish;
import com.app.fishcompetition.model.entity.Hunting;
import com.app.fishcompetition.model.entity.Member;
import com.app.fishcompetition.repositories.HuntingRepository;
import com.app.fishcompetition.services.HuntingService;
import com.app.fishcompetition.services.MemberService;
import com.app.fishcompetition.services.impls.MemberServiceImpl;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.dao.DataIntegrityViolationException;

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

    @InjectMocks
    private HuntingService huntingService;

    @Test
    void testAddHunting_memberNotExist() {
            UUID memberId = UUID.randomUUID();
            Hunting hunting = Hunting.builder().member(Member.builder().id(memberId).build()).build();

            when(memberService.getMemberById(memberId)).thenReturn(Optional.empty());

            assertThrows(NoSuchElementException.class, () -> huntingService.addHunting(hunting, 1.0));


    }

}
