package com.example.snsmysql.application.controller;

import com.example.snsmysql.domain.member.dto.MemberDto;
import com.example.snsmysql.domain.member.dto.MemberNicknameHistoryDto;
import com.example.snsmysql.domain.member.dto.RegisterMemberCommand;
import com.example.snsmysql.domain.member.entity.Member;
import com.example.snsmysql.domain.member.service.MemberReadService;
import com.example.snsmysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
public class MemberController {
    final private MemberWriteService memberWriteService;
    final private MemberReadService memberReadService;
    @PostMapping("/members")
    public MemberDto register(@RequestBody RegisterMemberCommand command) {
        return memberReadService.toDto(memberWriteService.register(command));
    }

    @GetMapping("/members/{id}")
    public MemberDto getMember(@PathVariable Long id) {
        return memberReadService.getMember(id);
    }

    @PostMapping("/{id}/name")
    public MemberDto changeNickname(@PathVariable Long id, @RequestBody String nickname) {
        memberWriteService.changeNickname(id, nickname);
        return memberReadService.getMember(id);
    }

    @GetMapping("/{memberId}/nickname-histories")
    public List<MemberNicknameHistoryDto> getNicknameHistories (@PathVariable Long memberId) {
        return memberReadService.getNicknameHistories(memberId);
    }
}
