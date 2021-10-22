package com.jojoldu.book.springboot.web;

import com.jojoldu.book.springboot.service.posts.PostsService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@RequiredArgsConstructor
@Controller
public class IndexController {

    private final PostsService postsService;

    @GetMapping("/")
    public String index(Model model) {  //Model: 서버 템플릿 엔진에서 사용할 수 있는 객체 저장 가능.
        // 여기선 postsService.findAllDesc()로 가져온 결과를 posts로 index.mustache에 전달
        model.addAttribute("posts", postsService.findAllDesc());
        //머스테치 스타터 덕분에 컨트롤러에서 문자열 반환할 때 앞 경로와 뒤 파일 확장자는 자동 지정
        return "index";  // src/main/resources/templates/index.mustache로 전환돼 view resolver가 처리
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
