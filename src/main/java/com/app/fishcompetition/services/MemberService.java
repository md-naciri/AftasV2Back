package com.app.fishcompetition.services;

import com.app.fishcompetition.model.dto.request.SignupRequest;
import com.app.fishcompetition.model.dto.response.MemberDTO;
import com.app.fishcompetition.model.entity.Member;
import org.springframework.data.domain.Page;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface MemberService {
    List<Member> getAllMembers();

    Page<Member> getAllMembersWithPagination(int pageNumber, int pageSize);;
    Optional<Member> getMemberById(UUID memberId);

    MemberDTO addMember(SignupRequest member);

    Member updateMember(UUID memberId, Member member);

    List<Member> searchMember(String searchText);;
    void deleteMember(UUID memberId);
    void enableAccount(String email);


}
