package com.gagyeong.book.springboot.web;

import com.gagyeong.book.springboot.service.posts.PostsService;
import com.gagyeong.book.springboot.web.dto.PostsResponseDto;
import com.gagyeong.book.springboot.web.dto.PostsSaveRequestDto;
import com.gagyeong.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    @PostMapping("/api/v1/posts")  // 등록
    public Long save(@RequestBody PostsSaveRequestDto requestDto) {
        return postsService.save(requestDto);
    }

    @PutMapping("/api/v1/posts/{id}")  // 수정
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto) {
        return postsService.update(id, requestDto);
    }

    @GetMapping("/api/v1/posts/{id}")  // 조회
    public PostsResponseDto findById (@PathVariable Long id) {
        return postsService.findById(id);
    }

    @DeleteMapping("/api/v1/posts/{id}")  // 삭제
    public Long delete(@PathVariable Long id) {
        postsService.delete(id);
        return id;
    }

}
