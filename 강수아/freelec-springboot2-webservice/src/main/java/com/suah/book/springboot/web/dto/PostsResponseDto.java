package com.suah.book.springboot.web.dto;

import com.suah.book.springboot.domain.posts.Posts;
import lombok.Getter;

@Getter
public class PostsResponseDto {

    private Long id;
    private String title;
    private String content;
    private String author;

    public PostsResponseDto(Posts entity){
        this.id = entity.getId();
        this.title = entity.getContent();
        this.content = entity.getAuthor();
        this.author = entity.getAuthor();
    }
}
