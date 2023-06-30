package com.example.snsmysql.domain.post.dto;

import java.time.LocalDateTime;

public record PostDto(
        Long id,
        String Contents,
        LocalDateTime createdAt,
        Long LikeCount
) {
}
