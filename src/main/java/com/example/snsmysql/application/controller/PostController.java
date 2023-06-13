package com.example.snsmysql.application.controller;

import com.example.snsmysql.domain.post.dto.DailyPostCount;
import com.example.snsmysql.domain.post.dto.DailyPostCountRequest;
import com.example.snsmysql.domain.post.dto.PostCommand;
import com.example.snsmysql.domain.post.service.PostReadService;
import com.example.snsmysql.domain.post.service.PostWriteService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/posts")
public class PostController {
    private final PostWriteService postWriteService;
    private final PostReadService postReadService;
    @PostMapping("")
    public Long create(PostCommand command) {
        return postWriteService.create(command);
    }

    @PostMapping("/daily-post-counts")
    public List<DailyPostCount> getDailyPostCount(DailyPostCountRequest request) {
        return postReadService.getDailyPostCount(request);
    }
}
