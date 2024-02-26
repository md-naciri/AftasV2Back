package com.app.fishcompetition.repositories;

import com.app.fishcompetition.model.entity.Competition;
import com.app.fishcompetition.model.entity.Hunting;
import com.app.fishcompetition.model.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface MemberRepository extends JpaRepository<Member, UUID> {

    @Query("SELECT m FROM Member m WHERE m.firstName LIKE %?1% OR m.identityNumber LIKE %?1% OR m.lastName LIKE %?1%")
    List<Member> searchMember(String searchText);

    Optional<Member> findByEmail(String username);
    Optional<Member> findByVerificationCode(String code);
}
