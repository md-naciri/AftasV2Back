package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.model.entity.Member;
import com.app.fishcompetition.model.entity.Ranking;
import com.app.fishcompetition.repositories.RankingRepository;
import com.app.fishcompetition.services.CompetitionService;
import com.app.fishcompetition.services.MemberService;
import com.app.fishcompetition.services.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RankingServiceImpl  implements RankingService {

    private final RankingRepository rankingRepository;
    private final MemberService memberService;
    private final CompetitionService competitionService;
    @Override
    public List<Ranking> getRankings() {
        return null;
    }

    @Override
    public Optional<Ranking> getRankingById(UUID id) {
        return rankingRepository.findById(id);
    }

    @Override
    public Ranking addRanking(Ranking ranking) {

    }

    @Override
    public Ranking updateRanking(UUID id, Ranking ranking) {
        return null;
    }

    @Override
    public void deleteRanking(UUID id) {

    }
    public boolean checkIfMemberExiste(UUID memberId){
        Optional<Member> member = memberService.getMemberById(memberId); ;
        return member.isPresent();
    }
    public boolean checkIfCompetitionExiste(UUID competitionId){
        Optional<Competition> Competition = competitionService.getCompetitionById(competitionId);
        return Competition.isPresent();
    }
}
