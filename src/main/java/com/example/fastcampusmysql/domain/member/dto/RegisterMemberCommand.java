package com.example.fastcampusmysql.domain.member.dto;

import java.time.LocalDate;

public record RegisterMemberCommand( // record : getter, setter를 자동으로 만들어주고, get.email 처럼 프로퍼티 형식으로 사용가능
        String email,
        String nickname,
        LocalDate birthday
) {
}
