package com.app.fishcompetition.controllers;

import com.app.fishcompetition.common.responses.RequestResponseWithDetails;
import com.app.fishcompetition.common.responses.RequestResponseWithoutDetails;
import com.app.fishcompetition.mapper.MemberDtoConverter;
import com.app.fishcompetition.model.dto.request.SignupRequest;
import com.app.fishcompetition.model.dto.response.MemberDTO;
import com.app.fishcompetition.model.entity.Member;
import com.app.fishcompetition.services.MemberService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@RequestMapping("/api")
@RestController
@RequiredArgsConstructor(onConstructor = @__(@Autowired))
public class MemberController {

    private final RequestResponseWithDetails requestResponseWithDetails;
    private final RequestResponseWithoutDetails requestResponseWithoutDetails;;
   private final MemberService memberService;;
   private final MemberDtoConverter memberDtoConverter;;

    @PostMapping("/member")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<RequestResponseWithDetails> addMember(@Valid  @RequestBody SignupRequest memberDto) {
        Map<String,Object> response = new HashMap<>();;
        MemberDTO memberToAdd = memberService.addMember(memberDto);
        requestResponseWithDetails.setMessage("Member added successfully");
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setStatus("200");
        response.put("member",memberToAdd);
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }

    @GetMapping("/members")
    @PreAuthorize("hasRole('ROLE_JURY')")
    public ResponseEntity<RequestResponseWithDetails> getAllMembers() {
        Map<String,Object> response = new HashMap<>();;
        List<MemberDTO> membersData = memberService.getAllMembers().stream()
                .map(memberDtoConverter::convertMemberTODto)
                .collect(Collectors.toList());
        response.put("members",membersData);

        requestResponseWithDetails.setMessage("Members retrieved successfully");
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }
    @GetMapping("/members/{pageNumber}/{pageSize}")
    @PreAuthorize("hasRole('ROLE_JURY')")
    public ResponseEntity<RequestResponseWithDetails> getMemberById(@PathVariable("pageNumber") int pageNumber, @PathVariable("pageSize") int pageSize) {
        Map<String,Object> response = new HashMap<>();
        Page<Member> members = memberService.getAllMembersWithPagination(pageNumber,pageSize);
        List<MemberDTO> membersData  = members.stream().
                map(memberDtoConverter::convertMemberTODto).collect(Collectors.toList());
        response.put("members",membersData);
        requestResponseWithDetails.setMessage("Member retrieved successfully");
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }
    @GetMapping("/member")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<RequestResponseWithDetails> searchMember(@RequestParam  String searchText){
        Map<String,Object> response = new HashMap<>();
        List<Member> members = memberService.searchMember(searchText);
        List<MemberDTO> membersData = members.stream()
                .map(memberDtoConverter::convertMemberTODto)
                .collect(Collectors.toList());
        response.put("members",membersData);
        requestResponseWithDetails.setMessage("Members retrieved successfully");
        requestResponseWithDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithDetails.setStatus("200");
        requestResponseWithDetails.setDetails(response);
        return ResponseEntity.ok().body(requestResponseWithDetails);
    }
    @PutMapping("/member/enable")
    @PreAuthorize("hasRole('ROLE_MANAGER')")
    public ResponseEntity<RequestResponseWithoutDetails> enableMember(@RequestParam String email) {
        memberService.enableAccount(email);
        requestResponseWithoutDetails.setMessage("Member account enabled successfully");
        requestResponseWithoutDetails.setTimestamp(LocalDateTime.now());
        requestResponseWithoutDetails.setStatus("200");
        return ResponseEntity.ok().body(requestResponseWithoutDetails);
    }
}
