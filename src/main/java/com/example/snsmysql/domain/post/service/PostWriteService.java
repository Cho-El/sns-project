package com.example.snsmysql.domain.post.service;

import com.example.snsmysql.domain.post.dto.PostCommand;
import com.example.snsmysql.domain.post.entity.Post;
import com.example.snsmysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostWriteService {
    private final PostRepository postRepository;

    public Long create(PostCommand command) {
        var post = Post
                .builder()
                .memberId(command.memberId())
                .contents(command.contents())
                .build();
        return postRepository.save(post).getId();
    }

    @Transactional
    public void likePost(Long postId) {
        /*
            동시성 이슈 발생
         */
        var post = postRepository.findById(postId, true).orElseThrow();
        post.incrementLikeCount();
        postRepository.save(post);
    }
    public void likePostByOptimisticLock(Long postId) {
        /*
            낙관적 락을 통한 동시성 이슈 해결
            데이터 삽입시 version이
         */
        var post = postRepository.findById(postId, false).orElseThrow();
        post.incrementLikeCount();
        postRepository.save(post);
    }
}
