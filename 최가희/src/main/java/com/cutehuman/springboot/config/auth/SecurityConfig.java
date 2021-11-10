package com.cutehuman.springboot.config.auth;

import com.cutehuman.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // 1.
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable()
                .headers().frameOptions().disable() // 2.
                .and().authorizeRequests() // 3.
                .antMatchers("/", "/css/**", "/images/**",
                        "/js/**","h2-console/**").permitAll()
                .antMatchers("/api/v1/**").hasRole(Role.USER.name()) // 4.
                .anyRequest().authenticated() // 5.
                .and().logout().logoutSuccessUrl("/") // 6.
                .and().oauth2Login() // 7.
                .userInfoEndpoint() // 8.
                .userService(customOAuth2UserService); // 9.
    }
}

/*
1. @EnableWebSecurity
- Spring Security 설정들을 활성화시켜 줌

2. csrf().disable().headers().frameOptions().disable()
- h2-console 화면을 사용하기 위해 해당 옵션들을 disable함

3. authorizeRequests
- URL별 권한 관리를 설정하는 옵션의 시작점
- authorizeRequests가 선언되어야 antMatchers 옵션을 사용할 수 있음

4. antMatchers
- 권한 관리 대상을 지정하는 옵션
- URL, HTTP 메소드별로 관리가 가능함
- "/" 등 지정된 URL들은 permitAll()옵션을 통해 전체 열람 권한을 줌
- "/api/v1/**" 주소를 가진 API는  USER 권한을 가진 사람만 가능하도록 함

5. anyRequest
- 설정된 값들 이외 나머지 URL들을 나타냄
- 여기서는 authenticated()을 추가하여 나머지 URL들은 모두 인증된 사용자들에게만 허용하게 함
- 인증된 사용자는 로그인 사용자를 말함

6. logout().logoutSuccessUrl("/")
- 로그아웃 기능에 대한 여러 설정의 진입점
- 로그아웃 성공 시 "/" 주소로 이동함

7. oauth2Login
- OAuth 2 로그인 기능에 대한 여러 설정의 진입점

8. userInfoEndpoint
- OAuth 2 로그인 성공 이후 사용자 정보를 가져올 때의 설정들을 담당함

9. userService
- 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체 등록
- 리소스 서버(소셜 서비스)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능 명시 가능
 */
