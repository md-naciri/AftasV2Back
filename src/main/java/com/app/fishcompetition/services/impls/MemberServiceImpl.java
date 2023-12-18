package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.common.exceptions.custom.DateNotAvailableException;
import com.app.fishcompetition.enums.IdentityDocumentType;
import com.app.fishcompetition.model.entity.Member;
import com.app.fishcompetition.repositories.MemberRepository;
import com.app.fishcompetition.services.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public Page<Member> getAllMembersWithPagination(int pageNumber, int pageSize){
        Pageable pageable = PageRequest.of(pageNumber,pageSize);
        return memberRepository.findAll(pageable);
    }

    @Override
    public Optional<Member> getMemberById(UUID memberId) {
        return memberRepository.findById(memberId);
    }

    @Override
    public Member addMember(Member member) {

        if(member.getAccessDate() != null) {
            if (!checkValidateAccessDate(member.getAccessDate())) {
                throw new DateNotAvailableException("access date should equal currentDate");
            }
        }else{
            member.setAccessDate(new Date());
        }
        return memberRepository.save(member);
    }

    @Override
    public Member updateMember(UUID memberId, Member member) {
        return null;
    }

    @Override
    public void deleteMember(UUID memberId) {

    }
    public boolean checkValidateAccessDate(Date accessDate){
        return accessDate.equals(new Date());
    }

}
