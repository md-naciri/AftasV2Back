package com.app.fishcompetition.services;

import com.app.fishcompetition.model.entity.Member;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberService {
    List<Member> getAllMembers();

    Optional<Member> getMemberById(UUID memberId);

    Member addMember(Member member);

    Member updateMember(UUID memberId, Member member);

    void deleteMember(UUID memberId);
}
