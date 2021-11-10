package com.cutehuman.springboot.web.domain.posts;


import com.cutehuman.springboot.domain.posts.Posts;
import com.cutehuman.springboot.domain.posts.PostsRepository;
import org.junit.After;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@SpringBootTest // 4.
public class PostsRepositoryTest {
    @Autowired
    PostsRepository postsRepository;

    @After // 1.
    public void cleanup(){
        postsRepository.deleteAll();
    }

    @Test
    public void 게시글저장_불러오기(){
        //given
        String title = "테스트 게시글";
        String content = "테스트 본문";

        postsRepository.save(Posts.builder() // 2.
                                .title(title)
                                .content(content)
                                .author("cutehuman")
                                .build());

        //when
        List<Posts> postsList = postsRepository.findAll(); // 3.

        //then
        Posts posts = postsList.get(0);
        assertThat(posts.getTitle()).isEqualTo(title);
        assertThat(posts.getContent()).isEqualTo(content);
    }

    @Test
    public void BaseTimeEntity_등록(){
        //given
        LocalDateTime now = LocalDateTime.of(2019,6,4,0,0,0);
        postsRepository.save(Posts.builder()
                .title("title")
                .content("content")
                .author("author")
                .build());

        //when
        List<Posts> postsList = postsRepository.findAll();

        //then
        Posts posts = postsList.get(0);

        System.out.println(">>>>>>>> createDate=" + posts.getCreateDate()
            + ", modifiedDate=" + posts.getModifieDate());
        assertThat(posts.getCreateDate()).isAfter(now);
        assertThat(posts.getModifieDate()).isAfter(now);
    }
}

/*
1. @After
- JUnit에서 단위 테스트가 끝날 때마다 수행되는 메소드를 지정
- 보통은 배포 전 전체 테스트를 수행할 때 테스트간 데이터 침법을 막기 위해 사용
- 여러 테스트가 동시에 수행되면 테스트용 DB인 H2에 데이터가 그대로 남아 있어 
  디음 테스트 실행 시 테스트가 실패할 수 있음 
  
2. postsRepository.save
- 테이블 posts에 insert/update 쿼리를 실행
- id 값이 있다면 update, 없다면 insert 쿼리가 실행됨

3. postsRepository.findAll
- 테이블 posts에 있는 모든 데이터를 조회해오는 메소드

4. @SpringBootTest
- 별다른 설정 없이 사용할 경우 H2 데이터베이스를 자동으로 실행해줌
*/

/*
+) @RunWith(SpringRunner.class)
- 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
- 여기서는 SpringRunner라는 스프링 실행자 사용
- 즉, 스프링 부트 테스트와 JUnit 사이에 연결자 역할을 함

+) @Autowired
- 스프링이 관리하는 빈(Bean)을 주입 받음

+) assertThat
- assertj라는 테스트 검증 라이브러리의 검증 메소드
- 검증하고 싶은 대상을 메소드 인자로 받음
- 메소드 체이닝이 지원되어 isEqualTo와 같이 메소드를 이어서 사용 가능 
 */
