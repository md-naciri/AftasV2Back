package com.app.fishcompetition.repositories;

import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.model.entity.Hunting;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;
import java.util.UUID;

@Repository
public interface HuntingRepository extends JpaRepository<Hunting, UUID> {

    @Query("SELECT h FROM Hunting h WHERE h.member.id = ?1 AND h.fish.id = ?2")
    Optional<Hunting> findByMemberIdAndFishId(UUID memberId, UUID fishId);
}
