package com.example.snsmysql.application.usecase;

import com.example.snsmysql.domain.follow.service.FollowReadService;
import com.example.snsmysql.domain.follow.service.FollowWriteService;
import com.example.snsmysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreateFollowMemberUsecase {
    /*
        follow와 member의 오케스트레이션 -> 흐름만을 제어해주는 서비스
     */
    private final MemberReadService memberReadService;
    private final FollowWriteService followWriteService;

    public void execute(Long fromMemberId, Long toMemberId) {
        /*
            입력받은 memberId로 회원 조회
            FollowWriteService.create()
         */
        var fromMember = memberReadService.getMember(fromMemberId);
        var toMember = memberReadService.getMember(toMemberId);
        followWriteService.create(fromMember,toMember);
    }
}
