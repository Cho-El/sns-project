package com.example.snsmysql.application.usecase;

import com.example.snsmysql.domain.follow.entity.Follow;
import com.example.snsmysql.domain.follow.service.FollowReadService;
import com.example.snsmysql.domain.member.dto.MemberDto;
import com.example.snsmysql.domain.member.service.MemberReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GetFollowingMemberUsecase {
    private final FollowReadService followReadService;
    private final MemberReadService memberReadService;

    public List<MemberDto> execute(Long memberId) {
        /*
            1. fromMemberId = memberId -> Follow List
            2. 1번 순회하면서 회원정보 찾기
         */
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(Follow::getToMemberId).toList();
        return memberReadService.getMembers(followingMemberIds);
    }
}
