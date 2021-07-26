package com.soyeon.springboot.service.posts;

import com.soyeon.springboot.domain.posts.Posts;
import com.soyeon.springboot.domain.posts.PostsRepository;
import com.soyeon.springboot.web.dto.PostResponseDto;
import com.soyeon.springboot.web.dto.PostsListResponseDto;
import com.soyeon.springboot.web.dto.PostsSaveRequestDto;
import com.soyeon.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class PostsService {

    private final PostsRepository postsRepository;

    @Transactional
    public Long save(PostsSaveRequestDto requestDto) {
        return postsRepository.save(requestDto.toEntity()).getId();
    }

    @Transactional
    public Long update(Long id, PostsUpdateRequestDto requestDto) {
        Posts updatePost = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 계시글이 없습니다. id : "+ id));
        updatePost.update(requestDto.getTitle(), requestDto.getContent());
        return id;
    }

    @Transactional
    public PostResponseDto findById(Long id) {
        Posts updatePost = postsRepository.findById(id).orElseThrow(()-> new IllegalArgumentException("해당 계시글이 없습니다. id : "+ id));
        return new PostResponseDto(updatePost);
    }

    @Transactional(readOnly = true)
    public List<PostsListResponseDto> findAllDesc() {
        return postsRepository.findAllDesc().stream().map(PostsListResponseDto::new).collect(Collectors.toList());
    }


}
