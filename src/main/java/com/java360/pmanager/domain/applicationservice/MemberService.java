package com.java360.pmanager.domain.applicationservice;

import com.java360.pmanager.domain.entity.Member;
import com.java360.pmanager.domain.repository.MemberRepository;
import com.java360.pmanager.domain.infrastructure.dto.SaveMemberDataDTO;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member createMember(SaveMemberDataDTO saveMemberDataDTO){

        Member member = Member
                .builder()
                .name(saveMemberDataDTO.getName())
                .email(saveMemberDataDTO.getEmail())
                .secret(UUID.randomUUID().toString())
                .deleted(false)
                .build();

        memberRepository.save(member);

        return member;

    }
}
