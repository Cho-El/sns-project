package com.example.snsmysql.domain.member.service;

import com.example.snsmysql.domain.member.dto.RegisterMemberCommand;
import com.example.snsmysql.domain.member.entity.Member;
import com.example.snsmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class MemberWriteService {
    final private MemberRepository memberRepository;
    public Member register(RegisterMemberCommand command) {
        var member = Member.builder()
                .email(command.email())
                .nickname(command.nickname())
                .birthday(command.birthday())
                .build();
        var savedMember = memberRepository.save(member);
        return savedMember;
    }
}
