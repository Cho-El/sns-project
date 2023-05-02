package com.example.snsmysql.domain.member.service;

import com.example.snsmysql.domain.member.dto.MemberDto;
import com.example.snsmysql.domain.member.dto.MemberNicknameHistoryDto;
import com.example.snsmysql.domain.member.entity.Member;
import com.example.snsmysql.domain.member.entity.MemberNicknameHistory;
import com.example.snsmysql.domain.member.repository.MemberNicknameHistoryRepository;
import com.example.snsmysql.domain.member.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MemberReadService {
    private final MemberRepository memberRepository;
    private final MemberNicknameHistoryRepository memberNicknameHistoryRepository;

    public List<MemberNicknameHistoryDto> getNicknameHistories(Long memberId) {
        return memberNicknameHistoryRepository
                .findAllByMemberId(memberId)
                .stream()
                .map(this::toDto)
                .toList();
    }
    public MemberDto getMember(Long id) {
        var member = memberRepository.findById(id).orElseThrow();
        return toDto(member);
    }

    private MemberNicknameHistoryDto toDto(MemberNicknameHistory history){
        return new MemberNicknameHistoryDto(
                history.getId(),
                history.getMemberId(),
                history.getNickname(),
                history.getCreatedAt()
        );
    }
    public MemberDto toDto(Member member) {
        return new MemberDto(member.getId(), member.getEmail(),  member.getNickname(), member.getBirthday());
    }
}
