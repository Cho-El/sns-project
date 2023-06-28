package com.example.snsmysql.application.usecase;

import com.example.snsmysql.domain.follow.entity.Follow;
import com.example.snsmysql.domain.follow.entity.Timeline;
import com.example.snsmysql.domain.follow.service.FollowReadService;
import com.example.snsmysql.domain.follow.service.TimelineReadService;
import com.example.snsmysql.domain.post.CursorRequest;
import com.example.snsmysql.domain.post.PageCursor;
import com.example.snsmysql.domain.post.entity.Post;
import com.example.snsmysql.domain.post.service.PostReadService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class GetTimeLinePostsUsecase {
    private final FollowReadService followReadService;
    private final PostReadService postReadService;

    private final TimelineReadService timelineReadService;
    public PageCursor<Post> execute(Long memberId, CursorRequest cursorRequest) {
        /*
            fan out on Read 모델 조회 -> pull 모델 -> 읽기 시 부하
            1. memberId -> follow 조회
            2. 1번 결과로 게시물 조회
         */
        var followings = followReadService.getFollowings(memberId);
        var followingMemberIds = followings.stream().map(Follow::getToMemberId).toList();
        return postReadService.getPosts(followingMemberIds, cursorRequest);
    }

    public PageCursor<Post> executeByTimeline(Long memberId, CursorRequest cursorRequest) {
        /*
            fan out on write 모델 구현 -> push 모델 -> 쓰기 시 부하
            1. timeLine 조회
            2. 1번의 해당 게시물 조회
         */
        var timelines = timelineReadService.getTimelines(memberId, cursorRequest);
        var postIds = timelines.body().stream().map(Timeline::getPostId).toList();
        var posts =  postReadService.getPosts(postIds);

        return new PageCursor<>(timelines.nextCursorRequest(),posts);
    }
}
