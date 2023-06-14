package com.example.snsmysql.factory;

import com.example.snsmysql.domain.post.entity.Post;
import org.jeasy.random.EasyRandom;
import org.jeasy.random.EasyRandomParameters;

import java.time.LocalDate;

import static org.jeasy.random.FieldPredicates.*;

public class PostFixtureFactory {
    public static EasyRandom get(Long memberId, LocalDate firstDate, LocalDate lastDate) {
        /*
            Predicate -> 조건자
            memberId 고정값
         */
        var memberIdPredicate = named("memberId")
                .and(ofType(Long.class))
                .and(inClass(Post.class));
        /*
            Predicate -> 조건자
            Id값 Null
         */
        var IdPredicate = named("id")
                .and(ofType(Long.class))
                .and(inClass(Post.class));

        /*
            Easy Random 파라미터 생성
            data 범위는 firstDate에서 lastDate까지 랜덤하게 생성
            Id 값이 Null인 경우에만 Post 생성하도록 코드를 짰기 때문에 Id값은 랜덤 값에서 제외
            memberId 또한 내가 지정한 memberId 값으로 고정
         */
        var param = new EasyRandomParameters()
                .dateRange(firstDate, lastDate)
                .excludeField(IdPredicate)
                .randomize(memberIdPredicate, () -> memberId);

        return new EasyRandom(param);
    }
}
