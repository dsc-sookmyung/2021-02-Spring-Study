package com.jojoldu.book.springboot.web;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class IndexController {
    @GetMapping("/")
    public String index() {
        //머스테치 스타터 덕분에 컨트롤러에서 문자열 반환할 때 앞 경로와 뒤 파일 확장자는 자동 지정
        return "index";  // src/main/resources/templates/index.mustache로 전환돼 view resolver가 처리
    }

    @GetMapping("/posts/save")
    public String postsSave() {
        return "posts-save";
    }
}
