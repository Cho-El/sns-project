package com.example.snsmysql.domain.follow.service;

import com.example.snsmysql.domain.follow.entity.Timeline;
import com.example.snsmysql.domain.follow.repository.TimelineRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class TimelineWriteService {
    private final TimelineRepository timelineRepository;

    // 게시물 작성시 follow들에게 전달
    public void deliveryToTimeLine(Long postId, List<Long> toMemberIds) {
        var timelines = toMemberIds.stream()
                .map((memberId) -> toTimeline(postId, memberId))
                .toList();

        timelineRepository.bulkInsert(timelines);
    }

    private static Timeline toTimeline(Long postId, Long memberId) {
        return Timeline.builder().memberId(memberId).postId(postId).build();
    }
}
