package com.app.fishcompetition.mapper;

import com.app.fishcompetition.model.dto.MemberDto;
import com.app.fishcompetition.model.entity.Member;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberDtoConverter {

    private final ModelMapper modelMapper;
    public Member convertDtoTOMember(MemberDto memberDto) {
        Member member = modelMapper.map(memberDto, Member.class);
        return member;
    }
    public MemberDto convertMemberTODto(Member member) {
        MemberDto memberDto = modelMapper.map(member, MemberDto.class);
        return memberDto;
    }
}
