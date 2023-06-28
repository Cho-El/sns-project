package com.example.snsmysql.domain.follow.service;

import com.example.snsmysql.domain.follow.entity.Timeline;
import com.example.snsmysql.domain.follow.repository.TimelineRepository;
import com.example.snsmysql.domain.post.CursorRequest;
import com.example.snsmysql.domain.post.PageCursor;
import com.example.snsmysql.domain.post.entity.Post;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class TimelineReadService {
    private final TimelineRepository timelineRepository;

    public PageCursor<Timeline> getTimelines(Long memberId, CursorRequest cursorRequest) {
        var timelines= findAllBy(memberId, cursorRequest);
        var nextKey = timelines.stream()
                .mapToLong(Timeline::getId)
                .min().orElse(CursorRequest.NONE_KEY);

        return new PageCursor<>(cursorRequest.next(nextKey), timelines);
    }

    private List<Timeline> findAllBy(Long memberId, CursorRequest cursorRequest) {
        if (cursorRequest.hasKey()) {
            return timelineRepository.findAllByLessThanIdAndMemberIdAndOrderByIdDesc(cursorRequest.key(), memberId, cursorRequest.size());
        }
        return timelineRepository.findAllByMemberIdAndOrderByIdDesc(memberId, cursorRequest.size());

    }
}
