package com.example.snsmysql.domain.post.entity;

import lombok.Builder;
import lombok.Getter;
import org.springframework.util.Assert;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Getter
public class Post {
    private final Long id;
    @NotNull
    private final Long memberId;
    @NotNull
    private final String contents;
    private final LocalDate createdDate;
    private final LocalDateTime createdAt;

    private Long likeCount;

    private final Long version;

    @Builder
    public Post(Long id, Long memberId, String contents, LocalDate createdDate, LocalDateTime createdAt, Long likeCount, Long version) {
        this.id = id;
        this.memberId = memberId;
        this.contents = contents;
        this.createdDate = createdDate == null ? LocalDate.now() : createdDate;
        this.createdAt = createdAt == null ? LocalDateTime.now() : createdAt;
        this.version = version == null ? 0 : version;
        this.likeCount = likeCount == null ? 0 : likeCount;
    }

    public void incrementLikeCount() {
        this.likeCount += 1;
    }
}
