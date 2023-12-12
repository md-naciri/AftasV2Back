package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.common.exceptions.custom.MemberCompetitionAlreadyExistException;
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
import java.util.NoSuchElementException;
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
            if(!checkIfMemberExist(ranking.getMember().getId())){
                throw  new NoSuchElementException("member not exist");
            }else if (!checkIfCompetitionExist(ranking.getCompetition().getId())){
                throw  new NoSuchElementException("competition not exist");
            }else if (checkIfUserAlreadyRankedWithSameCompetition(ranking.getMember().getId(), ranking.getCompetition().getId())){
                throw  new MemberCompetitionAlreadyExistException("member already ranked with same competition");
            }else{
                ranking.setRank(1);
                ranking.setScore(0);
                return rankingRepository.save(ranking);
            }
    }

    @Override
    public Ranking updateRanking(UUID id, Ranking ranking) {
        return null;
    }

    @Override
    public void deleteRanking(UUID id) {

    }
    public boolean checkIfMemberExist(UUID memberId){
        Optional<Member> member = memberService.getMemberById(memberId); ;
        return member.isPresent();
    }
    public boolean checkIfCompetitionExist(UUID competitionId){
        Optional<Competition> Competition = competitionService.getCompetitionById(competitionId);
        return Competition.isPresent();
    }
    public boolean checkIfUserAlreadyRankedWithSameCompetition(UUID memberId, UUID competitionId){
        return findByMemberIdAndCompetitionId(memberId, competitionId);
    }
    public boolean findByMemberIdAndCompetitionId(UUID memberId, UUID competitionId){
        Optional<Ranking> ranking = rankingRepository.findByMemberIdAndCompetitionId(memberId, competitionId);
        return ranking.isPresent();
    }

}
