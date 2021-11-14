package com.mjkim.springboot.config.auth;

import com.mjkim.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity // Spring Security 설정 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {

    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        // h2-console 화면을 사용하기 위해 해당 옵션 비활성화
        // authorizeRequests: URL별 권한 관리를 설정하는 옵션의 시작점 / authorizeRequests가 선언돼야만 antMatchers 옵션 사용 가능
        // antMatchers: 권한 관리 대상을 지정하는 옵션 / URL, HTTP 메소드별 관리 가능
        // permitAll: 전체 열람 권한
        // hasRole(Role.USER.name()): USER 권한을 가진 사람만 가능하도록 함
        http
                .csrf().disable()
                .headers().frameOptions().disable()
                .and()
                    .authorizeRequests()
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**").permitAll()
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name())
                    .anyRequest().authenticated() // anyRequest: 설정된 값 이외의 나머지 URL들을 나타냄
                .and()
                    .logout().logoutSuccessUrl("/")
                .and()
                    .oauth2Login()
                        .userInfoEndpoint() // OAuth2 로그인 성공 이후 사용자 정보 가져올 때 설정
                            .userService(customOAuth2UserService); // 소셜 로그인 성공 시 후속 조치를 진행할 UserService 인터페이스의 구현체 등록

    }

}
