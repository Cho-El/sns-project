package com.example.snsmysql.domain.post.service;

import com.example.snsmysql.domain.member.dto.MemberDto;
import com.example.snsmysql.domain.post.dto.PostCommand;
import com.example.snsmysql.domain.post.entity.Post;
import com.example.snsmysql.domain.post.entity.PostLike;
import com.example.snsmysql.domain.post.repository.PostLikeRepository;
import com.example.snsmysql.domain.post.repository.PostRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@RequiredArgsConstructor
@Service
public class PostLikeWriteService {
    private final PostLikeRepository postLikeRepository;

    public Long create(Post post, MemberDto memberDto) {
        var postLike = PostLike
                .builder()
                .postId(post.getId())
                .memberId(memberDto.id())
                .build();
        return postLikeRepository.save(postLike).getPostId();
    }
}
