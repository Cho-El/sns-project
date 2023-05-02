package com.example.snsmysql.domain.member.dto;

import java.time.LocalDate;
import java.time.LocalDateTime;

public record MemberNicknameHistoryDto(
        Long id,
        Long memberId,
        String nickname,
        LocalDateTime createdAt
) {
}
