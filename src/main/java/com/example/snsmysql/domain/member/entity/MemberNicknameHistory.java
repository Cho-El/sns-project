package com.example.snsmysql.domain.member.entity;

import lombok.Builder;
import lombok.Getter;

import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class MemberNicknameHistory {
    private final Long id;
    private final String nickname;
    private final Long memberId;
    private final LocalDateTime createdAt;

    @Builder
    public MemberNicknameHistory(Long id, String nickname, Long memberId, LocalDateTime createdAt) {
        this.id = id;
        this.nickname = Objects.requireNonNull(nickname);;
        this.memberId = Objects.requireNonNull(memberId);
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
    }
}
