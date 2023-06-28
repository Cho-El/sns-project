package com.example.snsmysql.application.usecase;

import com.example.snsmysql.domain.follow.entity.Follow;
import com.example.snsmysql.domain.follow.service.FollowReadService;
import com.example.snsmysql.domain.follow.service.TimelineWriteService;
import com.example.snsmysql.domain.post.dto.PostCommand;
import com.example.snsmysql.domain.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@RequiredArgsConstructor
@Service
public class CreatePostUsecase {
    private final PostWriteService postWriteService;
    private final FollowReadService followReadService;
    private final TimelineWriteService timelineWriteService;

    public Long execute(PostCommand postCommand) {
        /*
            부하 이슈가 있을 수 있음
            -> 비동기로 처리
            -> RDB 이외의 DB 사용
            -> 성능 좋은 Cached 사용으로 해결
         */
        var postId = postWriteService.create(postCommand);
        var followerMemberIds = followReadService.getFollowers(postCommand.memberId()).stream()
                .map(Follow::getFromMemberId)
                .toList();
        timelineWriteService.deliveryToTimeLine(postId, followerMemberIds);

        return postId;
    }
}
