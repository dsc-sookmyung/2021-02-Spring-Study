package com.suah.book.springboot.web;


import com.suah.book.springboot.service.posts.PostsService;
import com.suah.book.springboot.web.dto.PostsResponseDto;
import com.suah.book.springboot.web.dto.PostsSaveRequestDto;
import com.suah.book.springboot.web.dto.PostsUpdateRequestDto;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
public class PostsApiController {

    private final PostsService postsService;

    //등록 기능
    @PostMapping("/api/v1/posts")
    public Long save(@RequestBody PostsSaveRequestDto requestDto){
        return postsService.save(requestDto);
    }

    //수정 기능
    @PutMapping("/api/v1/posts/{id}")
    public Long update(@PathVariable Long id, @RequestBody PostsUpdateRequestDto requestDto){
        return postsService.update(id, requestDto);
    }

    //조회 기능
    @GetMapping("/api/v1/posts/{id}")
    public PostsResponseDto findbyId (@PathVariable Long id){
        return postsService.findById(id);
    }

    //삭제 기능
    @DeleteMapping("/api/v1/posts/{id}")
    public Long delete(@PathVariable Long id){
        postsService.delete(id);
        return id;
    }
}
