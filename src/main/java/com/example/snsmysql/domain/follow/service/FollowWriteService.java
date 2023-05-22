package com.example.snsmysql.domain.follow.service;

import com.example.snsmysql.domain.follow.entity.Follow;
import com.example.snsmysql.domain.follow.repository.FollowRepository;
import com.example.snsmysql.domain.member.dto.MemberDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
@RequiredArgsConstructor
@Service
public class FollowWriteService {
    private final FollowRepository followRepository;
    public void create(MemberDto fromMember, MemberDto toMember) {
        /*
            form - to -> validate 적용하기
         */
        Assert.isTrue(!fromMember.id().equals(toMember.id()), "From, To 회원이 동일합니다.");

        var follow = Follow.builder()
                .fromMemberId(fromMember.id())
                .toMemberId(toMember.id())
                .build();

        followRepository.save(follow);
    }
}
