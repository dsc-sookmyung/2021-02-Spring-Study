package com.mjkim.springboot.web;

import com.mjkim.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController // JSON을 반환하는 컨트롤러로 만듦
public class HelloController {

    @GetMapping("/hello") // GET 요청을 받을 수 있는 API로 만들어줌
    public String hello() {
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name,
                                     @RequestParam("amount") int amount) {
        // @RequestParam: 외부에서 API로 넘긴 파라미터를 가져오는 어노테이션
        // 외부에서 name이란 이름으로 넘긴 파라미터를 메소드 파라미터 name(String name)에 저장
        return new HelloResponseDto(name, amount);
    }
}
