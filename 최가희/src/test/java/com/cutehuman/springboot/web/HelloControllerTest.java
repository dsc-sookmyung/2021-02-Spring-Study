package com.cutehuman.springboot.web;


import com.cutehuman.springboot.config.auth.SecurityConfig;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.FilterType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.hamcrest.Matchers.is;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@RunWith(SpringRunner.class) //1.
@WebMvcTest(controllers = HelloController.class, //2.
        excludeFilters = {
                @ComponentScan.Filter(type = FilterType.ASSIGNABLE_TYPE, classes = SecurityConfig.class)
        }
)
public class HelloControllerTest {
    @Autowired //3.
    private MockMvc mvc; //4.

    @WithMockUser(roles="USER")
    @Test
    public void hello가_리턴된다() throws Exception{
        String hello = "hello";

        mvc.perform(get("/hello")) //5.
                .andExpect(status().isOk()) //6.
                .andExpect(content().string(hello)); //7.
    }

    @WithMockUser(roles="USER")
    @Test
    public void helloDto가_리턴된다() throws Exception{
        String name = "hello";
        int amount = 1000;

        mvc.perform(
                get("/hello/dto").param("name", name) // 8.
                        .param("amount", String.valueOf(amount)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name", is(name))) // 9.
                .andExpect(jsonPath("$.amount", is(amount)));
    }

}

/*
1. @RunWith(SpringRunner.class)
- 테스트를 진행할 때 JUnit에 내장된 실행자 외에 다른 실행자를 실행시킴
- 여기서는 SpringRunner라는 스프링 실행자를 사용함
- 즉, 스프링부트 테스트와 JUnit 사이에 연결자 역할

2. @WebMvcTest
- 여러 스프링 테스트 어노테이션 중, Web(Spring MVC)에 집중할 수 있는 어노테이션
- 선언할 경우 @Controller, @ControllerAdvice 등을 사용할 수 있음
- 단, @Service, @Component, @Repository 등은 사용x
- 여기서는 컨트롤러만 사용

3. @Autowired
- 스프링이 관리하는 Bean을 주입 받음

4. private MockMvc mvc
- 웹 API를 테스트할 때 사용
- 스프링 MVC 테스트의 시작점
- 이 클래스를 통해 HTTP GET, POST 등에 대한 API 테스트 할 수 있음

5. mvc.perform(get("/hello"))
- MockMvc를 통해 /hello 주소로 HTTP GET 요청을 함
- 체이닝이 지원되어 아래와 같이 여러 검증 기능을 이어서 선언 가능

6. .andExpect(status().isOk())
- mvc.perform의 결과를 검증
- HTTP Header의 Status를 검증
 ~> 200, 404, 500 등의 상태. 여기선 200(OK) 검증

7. .andExpect(content().string(hello))
- mvc.perform의 결과를 검증
- 응답 본문의 내용 검증
- Controller에서 "hello"를 리턴하기 때문에 이 값이 맞는지 검증

8. param
- API 테스트할 때 사용된 요청 파라미터 설정 (String 값만 허용)
- 숫자/날짜 등의 데이터도 등록할 때는 문자열로 변경해야만 가능

9. jsonPath
- JSON 응답값을 필드별로 검증할 수 있는 메소드
- $를 기준으로 필드명 명시
*/