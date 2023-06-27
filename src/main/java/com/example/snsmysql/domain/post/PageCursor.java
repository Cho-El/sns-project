package com.example.snsmysql.domain.post;

import java.util.List;

public record PageCursor<T> (
        CursorRequest nextCursorRequest, // 마지막 커서
        List<T> body // Post 내용들
) {

}