package com.example.snsmysql.application.controller;

import com.example.snsmysql.application.usecase.CreatePostUsecase;
import com.example.snsmysql.application.usecase.GetTimeLinePostsUsecase;
import com.example.snsmysql.domain.post.CursorRequest;
import com.example.snsmysql.domain.post.PageCursor;
import com.example.snsmysql.domain.post.dto.DailyPostCount;
import com.example.snsmysql.domain.post.dto.DailyPostCountRequest;
import com.example.snsmysql.domain.post.dto.PostCommand;
import com.example.snsmysql.domain.post.entity.Post;
import com.example.snsmysql.domain.post.service.PostReadService;
import com.example.snsmysql.domain.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostWriteService postWriteService;
    private final PostReadService postReadService;
    private  final GetTimeLinePostsUsecase getTimeLinePostsUsecase;

    private final CreatePostUsecase createPostUsecase;
    @PostMapping("")
    public Long create(@RequestBody PostCommand command) {
        return createPostUsecase.execute(command);
    }

    @PostMapping("/daily-post-counts")
    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {
        return postReadService.getDailyPostCount(request);
    }

    @GetMapping("/members/{memberId}")
    public Page<Post> getPosts(
            @PathVariable Long memberId,
            Pageable pageable
            ) {
        return postReadService.getPosts(memberId, pageable);
    }

    @GetMapping("/members/{memberId}/by-cursor")
    public PageCursor<Post> getPostsByCursor(
            @PathVariable Long memberId,
            CursorRequest cursorRequest
    ) {
        return postReadService.getPosts(memberId, cursorRequest);
    }

    @GetMapping("/member/{memberId}/timeline")
    public PageCursor<Post> getTimeline(
            @PathVariable Long memberId,
            CursorRequest cursorRequest
    ) {
        return getTimeLinePostsUsecase.executeByTimeline(memberId, cursorRequest);
    }

    @PostMapping("/{postId}/like")
    public void likePost(Long postId) {
        postWriteService.likePost(postId);
    }
}
