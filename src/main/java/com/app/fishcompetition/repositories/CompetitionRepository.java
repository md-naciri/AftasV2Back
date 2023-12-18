package com.app.fishcompetition.repositories;

import com.app.fishcompetition.model.entity.Competition;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Repository
public interface CompetitionRepository extends JpaRepository<Competition, UUID> {

    @Query("SELECT c FROM Competition c WHERE c.date = ?1")
    List<Competition> findByDate(Date date);

    @Query("SELECT c FROM Competition c WHERE c.date < ?1")
    List<Competition> findByDateBefore(Date date);

    @Query("SELECT c FROM Competition c WHERE c.date >= ?1")
    List<Competition> findByDateAfter(Date date);
}
