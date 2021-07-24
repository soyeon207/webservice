package com.soyeon.springboot.domain.posts;

import lombok.RequiredArgsConstructor;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest
public class PostsRepositoryTest {

    @Autowired
    private PostsRepository postsRepository;

    @After
    public void cleanup() {
        postsRepository.deleteAll();
    }

    @Test
    public void 게시물저장_불러오기() {
        String title = "테스트게시글";
        String content = "테스트본문";

        postsRepository.save(Posts
                .builder()
                .title(title)
                .content(content)
                .author("qkrthdus207@naver.com")
                .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록() {
        LocalDateTime now = LocalDateTime.of(2021, 7, 24, 9, 52, 0);
        postsRepository.save(Posts.builder()
        .title("title")
        .content("content")
        .author("author")
        .build());

        List<Posts> postsList = postsRepository.findAll();

        Posts posts = postsList.get(0);
        System.out.println(">>>>>>> createdDate: "+posts.getCreatedDate()+", modifiedDate : "+posts.getModifiedDate());
        assertThat(posts.getCreatedDate()).isBefore(LocalDateTime.now());
        assertThat(posts.getModifiedDate()).isBefore(LocalDateTime.now());
    }

}
