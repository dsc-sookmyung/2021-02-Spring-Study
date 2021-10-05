package com.cutehuman.springboot.web;

import com.cutehuman.springboot.web.dto.HelloResponseDto;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController //컨트롤러를 JSON을 반환하는 컨트롤러로 만들어줌
public class HelloController {
    
    // HTTP 메소드인 Get의 요청을 받을 수 있는 API 생성
    // '/hello'로 요청이 오면 문자열 hello를 반환
    @GetMapping("/hello") 
    public String hello(){
        return "hello";
    }

    @GetMapping("/hello/dto")
    public HelloResponseDto helloDto(@RequestParam("name") String name, // *
                                     @RequestParam("amount") int amount){
        return new HelloResponseDto(name, amount);
    }
}

/*
 @RequestParam
- 외부에서 API로 넘긴 파라ㅁ터를 가져오는 어노테이션
- 외부에서 name(@RequestParam("name"))이란 이름으로 넘긴 파라미터를
  메소드 파라미터(String name)에 저장하게 됨
*/