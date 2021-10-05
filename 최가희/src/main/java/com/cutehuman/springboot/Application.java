package com.cutehuman.springboot;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/*이 프로젝트의 메인 클래스*/
@SpringBootApplication //스프링부트의 자동설정, 스프링 Bean 읽기와 생성 모두 자동 설정
public class Application {
    public static void main(String[] args){
        //SpringApplication.run ~> 내장 WAS 실행
        SpringApplication.run(Application.class, args);
    }
}

/*
+) WAS: Web Application Server
- 내장 WAS는 별도로 외부에 WAS를 두지 않고 애플리케이션을 실행할 때 내부에서 WAS를 실행하는 것
  ~> 항상 서버에 톰캣을 설치할 필요가 없게되고, 스프링 부트로 만들어진 Jar 파일로 실행하면 됨
  +) Jar: Java 패키징 파일
- 내장 WAS를 사용하는 것을 권장하는 이유는 '언제 어디서나 같은 환경에서 스프링 부트를 배포'할 수 있기 때문
 */
