package com.example.snsmysql.controller;

import com.example.snsmysql.domain.member.dto.MemberDto;
import com.example.snsmysql.domain.member.dto.RegisterMemberCommand;
import com.example.snsmysql.domain.member.service.MemberReadService;
import com.example.snsmysql.domain.member.service.MemberWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

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
}
