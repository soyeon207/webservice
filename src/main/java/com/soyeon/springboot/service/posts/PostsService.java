package com.soyeon.springboot.service.posts;

import com.soyeon.springboot.domain.posts.Posts;
import com.soyeon.springboot.domain.posts.PostsRepository;
import com.soyeon.springboot.web.dto.PostResponseDto;
import com.soyeon.springboot.web.dto.PostsSaveRequestDto;
import com.soyeon.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;

@RequiredArgsConstructor
@Service
@Transactional
public class PostsService {

    private final PostsRepository postsRepository;

    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts updatePost = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 계시글이 없습니다. id : "+ id));
        updatePost.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    public PostResponseDto findById(Long id) {
        Posts updatePost = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 계시글이 없습니다. id : "+ id));
        return new PostResponseDto(updatePost);
    }


}
