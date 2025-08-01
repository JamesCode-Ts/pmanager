package com.java360.pmanager.domain.applicationservice;

import com.java360.pmanager.domain.entity.Member;
import com.java360.pmanager.domain.exception.MemberNotFoundExeption;
import com.java360.pmanager.domain.exception.DuplicateMemberException;
import com.java360.pmanager.domain.repository.MemberRepository;
import com.java360.pmanager.domain.infrastructure.dto.SaveMemberDataDTO;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.UUID;

@Service
@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;

    public Member createMember(SaveMemberDataDTO saveMemberData){
          if(existsMemberWithEmail(saveMemberData.getEmail(), null)){
              throw new DuplicateMemberException(saveMemberData.getEmail());
        }
        Member member = Member
                .builder()
                .name(saveMemberData.getName())
                .email(saveMemberData.getEmail())
                .secret(UUID.randomUUID().toString())
                .deleted(false)
                .build();

        memberRepository.save(member);

        return member;

    }

    public Member loadMemberbyId(String memberId){
        return memberRepository
                .findByIdAndDeleted(memberId, false)
                .orElseThrow(() -> new MemberNotFoundExeption(memberId));
    }

    @Transactional
    public void deletedMember(String memberId){
        Member member = loadMemberbyId(memberId);
        member.setDeleted(true);
    }

    @Transactional
    public Member updateMember(String memberId, SaveMemberDataDTO saveMemberData){
        if(existsMemberWithEmail(saveMemberData.getEmail(), memberId)){
            throw new DuplicateMemberException(saveMemberData.getEmail());
        }

        Member member = loadMemberbyId(memberId);

        member.setName(saveMemberData.getName());
        member.setEmail(saveMemberData.getEmail());

        return member;
    }

    private boolean existsMemberWithEmail(String email, String idToExclude){
        return memberRepository
                .findByEmailAndDeleted(email,false)
                .filter(m -> !Objects.equals(m.getId(), idToExclude))
                .isPresent();
    }


    public List<Member> findMembers(String email){
        List<Member> members;

        if(Objects.isNull(email)){
            members = memberRepository.findAllNotDeleted();

        }else{
            members = memberRepository
                    .findByEmailAndDeleted(email, false)
                    .map(List::of)
                    .orElse(List.of());
        }
       return members;
    }
}
