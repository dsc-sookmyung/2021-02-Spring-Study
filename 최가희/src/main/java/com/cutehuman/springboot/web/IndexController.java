package com.cutehuman.springboot.web;

import com.cutehuman.springboot.service.posts.PostsService;
import com.cutehuman.springboot.web.dto.PostsResponseDto;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model){ // 1.
        model.addAttribute("posts", postsService.findAllDesc());
        return "index";
    }

    @GetMapping("/posts/save") //posts/save를 호출하면
    public String postsSave(){ //posts-save.mustache 호출
        return "posts-save";
    }

    @GetMapping("/posts/update/{id}")
    public String postsUpdate(@PathVariable Long id, Model model){
        PostsResponseDto dto = postsService.findById(id);
        model.addAttribute("post", dto);

        return "posts-update";
    }
}

/*
1. Model
- 서버 템플릿 엔진에서 사용할 수 있는 객체를 저장할 수 있음
- 여기서는 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달함
 */