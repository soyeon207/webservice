package com.soyeon.springboot.web;

import com.soyeon.springboot.service.posts.PostsService;
import com.soyeon.springboot.web.dto.PostResponseDto;
import com.soyeon.springboot.web.dto.PostsSaveRequestDto;
import com.soyeon.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/posts")
public class PostApiController {

    private final PostsService postsService;

    @PostMapping
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/{id}")
    public PostResponseDto findById(@PathVariable Long id){
        return postsService.findById(id);
    }

}
