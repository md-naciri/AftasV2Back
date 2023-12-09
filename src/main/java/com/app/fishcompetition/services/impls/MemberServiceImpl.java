package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.model.entity.Member;
import com.app.fishcompetition.services.MemberService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class MemberServiceImpl implements MemberService {
    @Override
    public List<Member> getAllMembers() {
        return null;
    }

    @Override
    public Optional<Member> getMemberById(UUID memberId) {
        return Optional.empty();
    }

    @Override
    public Member addMember(Member member) {
        return null;
    }

    @Override
    public Member updateMember(UUID memberId, Member member) {
        return null;
    }

    @Override
    public void deleteMember(UUID memberId) {

    }
}
