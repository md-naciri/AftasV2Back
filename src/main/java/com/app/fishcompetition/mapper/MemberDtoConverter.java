package com.app.fishcompetition.mapper;

import com.app.fishcompetition.model.dto.response.MemberDTO;
import com.app.fishcompetition.model.entity.Member;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberDtoConverter {

    private final ModelMapper modelMapper;
    public Member convertDtoTOMember(MemberDTO memberDto) {
        Member member = modelMapper.map(memberDto, Member.class);
        return member;
    }
    public MemberDTO convertMemberTODto(Member member) {
        MemberDTO memberDto = modelMapper.map(member, MemberDTO.class);
        return memberDto;
    }
}
