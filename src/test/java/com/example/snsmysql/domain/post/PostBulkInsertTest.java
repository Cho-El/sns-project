package com.example.snsmysql.domain.post;

import com.example.snsmysql.domain.post.entity.Post;
import com.example.snsmysql.domain.post.repository.PostRepository;
import com.example.snsmysql.factory.PostFixtureFactory;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.util.StopWatch;

import java.time.LocalDate;
import java.util.stream.IntStream;

@SpringBootTest
public class PostBulkInsertTest {
    @Autowired
    private PostRepository postRepository;

    @Test
    public void bulkInsert() {
        var easyRandom = PostFixtureFactory.get(
                3L,
                LocalDate.of(2022, 1, 1),
                LocalDate.of(2022, 2, 1)
        );
        // stopWatch를 통한 시간 체크
        var stopWatch = new StopWatch();
        stopWatch.start();
        var posts = IntStream.range(0,1000000)
                .parallel()
                .mapToObj(i -> easyRandom.nextObject(Post.class))
                .toList();
        stopWatch.stop();;
        System.out.println("객체 생성 시간 : " + stopWatch.getTotalTimeSeconds());

        // AOP로 추후의 변경 예정
        var queryStopWatch = new StopWatch();
        queryStopWatch.start();
        postRepository.bulkInsert(posts);
        queryStopWatch.stop();
        System.out.println("DB 삽입 시간 : " + queryStopWatch.getTotalTimeSeconds());
    }
}
