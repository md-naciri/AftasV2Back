package com.app.fishcompetition.services.impls;

import com.app.fishcompetition.common.exceptions.custom.DateNotAvailableException;
import com.app.fishcompetition.common.exceptions.custom.UserAllReadyExistException;
import com.app.fishcompetition.enums.IdentityDocumentType;
import com.app.fishcompetition.enums.Provider;
import com.app.fishcompetition.mapper.MemberDtoConverter;
import com.app.fishcompetition.model.dto.request.SignupRequest;
import com.app.fishcompetition.model.dto.response.MemberDTO;
import com.app.fishcompetition.model.entity.Member;
import com.app.fishcompetition.repositories.MemberRepository;
import com.app.fishcompetition.services.MemberService;
import com.app.fishcompetition.services.security.AddRoleToUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.*;

@Service
@RequiredArgsConstructor
public class MemberServiceImpl implements MemberService {

    private final MemberRepository memberRepository;
    private final PasswordEncoder passwordEncoder;
    private final AddRoleToUserService addRoleToUserService;
    private final MemberDtoConverter memberDtoConverter;;

    @Override
    public List<Member> getAllMembers() {
        return memberRepository.findAll();
    }

    @Override
    public List<Member> searchMember(String searchText){
        return memberRepository.searchMember(searchText);
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
    public MemberDTO addMember(SignupRequest member) {

        memberRepository.findByEmail(member.getEmail())
                .ifPresent(
                        (Member existingUser)->  {throw  new UserAllReadyExistException("User with this email already exist");}
                );
        var memberToSave = Member.builder()
                .firstName(member.getFirstName())
                .lastName(member.getLastName())
                .email(member.getEmail())
                .password(passwordEncoder.encode(member.getPassword()))
                .identityDocumentType(member.getIdentityDocumentType())
                .nationality(member.getNationality())
                .identityNumber(member.getIdentityNumber())
                .enabled(false)
                .accountNonLocked(true)
                .accountNonExpired(true)
                .credentialsNonExpired(true)
                .authorities(new ArrayList<>())
                .provider(Provider.local)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now()).build();

        var savedMember = memberRepository.save(memberToSave);
        addRoleToUserService.addRoleToUser(member.getEmail(),"ROLE_ADHERENT");
        return memberDtoConverter.convertMemberTODto(savedMember) ;
    }

    @Override
    public Member updateMember(UUID memberId, Member member) {
        return null;
    }

    @Override
    public void deleteMember(UUID memberId) {
        Optional<Member> memberToDelete = getMemberById(memberId);
        if(memberToDelete.isPresent()) {
            memberRepository.delete(memberToDelete.get());
        } else {
            throw new NoSuchElementException("fish with id " + memberId + " does not exist");
        }
    }

    @Override
    public void enableAccount(String email) {
            Member member = memberRepository.findByEmail(email).orElseThrow(() -> new UsernameNotFoundException("Member with email " + email + " does not exist"));
            member.setEnabled(true);
            memberRepository.save(member);
        }
}
