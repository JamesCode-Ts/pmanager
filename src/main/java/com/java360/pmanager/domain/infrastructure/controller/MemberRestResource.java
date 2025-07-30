package com.java360.pmanager.domain.infrastructure.controller;

import com.java360.pmanager.domain.applicationservice.MemberService;
import com.java360.pmanager.domain.applicationservice.ProjectService;
import com.java360.pmanager.domain.entity.Member;
import com.java360.pmanager.domain.entity.Project;
import com.java360.pmanager.domain.infrastructure.dto.MemberDTO;
import com.java360.pmanager.domain.infrastructure.dto.ProjectDTO;
import com.java360.pmanager.domain.infrastructure.dto.SaveMemberDataDTO;
import com.java360.pmanager.domain.infrastructure.dto.SaveProjectDataDTO;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

import static com.java360.pmanager.domain.infrastructure.controller.RestConstants.PATH_MEMBERS;
import static com.java360.pmanager.domain.infrastructure.controller.RestConstants.PATH_PROJECTS;

@RestController
@RequestMapping(PATH_MEMBERS)
@RequiredArgsConstructor
@SuppressWarnings("unused")
public class MemberRestResource {

    private final MemberService memberService;

    // Estudar esta estrutura no chatgpt
    @PostMapping
    public ResponseEntity<MemberDTO> createMember(@RequestBody @Valid SaveMemberDataDTO saveMemberData) {
        Member member = memberService.createMember(saveMemberData);

        return ResponseEntity
                .created(URI.create(PATH_MEMBERS + member.getId()))
                .body(MemberDTO.create(member));

    }

    @GetMapping("/{id}")
    public ResponseEntity<MemberDTO> loadMemberById(@PathVariable("id") String memberId){
          Member member = memberService.loadMemberbyId(memberId);
          return ResponseEntity.ok(MemberDTO.create(member));

    }

}




