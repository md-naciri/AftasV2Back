package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.common.exceptions.custom.CompetitionNotAvailableException;
import com.app.fishcompetition.common.exceptions.custom.MemberCompetitionAlreadyExistException;
import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.model.entity.Member;
import com.app.fishcompetition.model.entity.Ranking;
import com.app.fishcompetition.repositories.RankingRepository;
import com.app.fishcompetition.services.CompetitionService;
import com.app.fishcompetition.services.MemberService;
import com.app.fishcompetition.services.RankingService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class RankingServiceImpl  implements RankingService {

    private final RankingRepository rankingRepository;
    private final MemberService memberService;
    private final CompetitionService competitionService;
    private final RequestResponseWithDetails requestResponseWithDetails;
    @Override
    public List<Ranking> getRankings() {
        return rankingRepository.findAll();
    }

    @Override
    public Optional<Ranking> getRankingById(UUID id) {
        return rankingRepository.findById(id);
    }

    public Optional<Ranking> getRankingByMemberIdAndCompetitionId(UUID memberId, UUID competitionId) {
        return rankingRepository.findByMemberIdAndCompetitionId(memberId, competitionId);
    }

    @Override
    public Ranking addRanking(Ranking ranking) {
           if(!checkIfCompetitionIsAvailableToJoin(ranking.getCompetition().getId())){
                throw  new CompetitionNotAvailableException("competition not available to join ");
           }else  if(!checkIfMemberExist(ranking.getMember().getId())){
                throw  new NoSuchElementException("member not exist");
            }else if (!checkIfCompetitionExist(ranking.getCompetition().getId())){
                throw  new NoSuchElementException("competition not exist");
            }else if (checkIfUserAlreadyRankedWithSameCompetition(ranking.getMember().getId(), ranking.getCompetition().getId())){
                throw  new MemberCompetitionAlreadyExistException("member already ranked with same competition");
            }else{
                ranking.setRank(1);
                ranking.setScore(0);
                Competition competition = competitionService.getCompetitionById(ranking.getCompetition().getId()).get();
                competition.setNumberOfParticipants(competition.getNumberOfParticipants() + 1);
                competitionService.updateCompetition(competition.getId(), competition);
                return rankingRepository.save(ranking);
            }
    }
    public List<Ranking> getRankingsByCompetitionId(UUID competitionId) {
        competitionService.getCompetitionById(competitionId).orElseThrow(() -> new NoSuchElementException("Competition not found"));
        return rankingRepository.findAllByCompetitionIdOrderByScoreDesc(competitionId);
    }

    @Override
    public void updateRanking(UUID rankingId, Ranking updatedRanking) {
        Ranking ranking = rankingRepository.findById(rankingId)
                .orElseThrow(() -> new NoSuchElementException("Ranking not found"));

        ranking.setScore(updatedRanking.getScore());

        List<Ranking> rankings = rankingRepository.findAllByCompetitionIdOrderByScoreDesc(ranking.getCompetition().getId());

        for (int i = 0; i < rankings.size(); i++) {
            Ranking currentRanking = rankings.get(i);
            currentRanking.setRank(i + 1);
            rankingRepository.save(currentRanking);
        }
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
    public boolean checkIfCompetitionIsAvailableToJoin(UUID competitionId){
        Optional<Competition> competition = competitionService.getCompetitionById(competitionId);
        if(competition.isPresent()) {
            Calendar cal = Calendar.getInstance();
            cal.add(Calendar.HOUR, 24);
            Date currentDatePlus24h = cal.getTime();
            Date competitionDate = competition.get().getDate();
            return currentDatePlus24h.before(competitionDate);
        }else{
            throw new NoSuchElementException("competition not exist");
        }
    }
}
