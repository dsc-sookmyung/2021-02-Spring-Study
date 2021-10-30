package com.jojoldu.book.springboot.config.auth;

import com.jojoldu.book.springboot.domain.user.Role;
import lombok.RequiredArgsConstructor;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

@RequiredArgsConstructor
@EnableWebSecurity        //Spring Security 설정들 활성화
public class SecurityConfig extends WebSecurityConfigurerAdapter {
    private final CustomOAuth2UserService customOAuth2UserService;

    @Override
    protected void configure(HttpSecurity http) throws Exception {
        http
                //h2-console 화면 사용 위해 해당 옵션들 disable 시켜줌
                .csrf().disable()
                .headers().frameOptions().disable()

                .and()
                    .authorizeRequests() //URL별 권한 관리 설정하는 옵션의 시작점 (이게 있어야 andMatchers(권한 관리 대상 지정) 사용 가능)
                    .antMatchers("/", "/css/**", "/images/**", "/js/**", "/h2-console/**", "/profile").permitAll() //permitAll: 전체 열람 권한
                    .antMatchers("/api/v1/**").hasRole(Role.USER.name()) //"/api/v1/**" 주소 가진 API는 USER 권한을 가진 사람만 가능하도록
                    .anyRequest().authenticated() //설정값들 이외 나머지 URL은 / 인증된(로그인한) 사용자에게만 허용
                .and()
                    .logout() //로그아웃 기능에 대한 여러 설정의 진입점
                        .logoutSuccessUrl("/") //로그아웃 성공 시 / 주소로 이동
                .and()
                    .oauth2Login()
                        .userInfoEndpoint() //OAuth2 로그인 성공 이후 사용자 정보 가져올 때의 설정들
                            .userService(customOAuth2UserService); //리소스 서버(소셜 서비스들)에서 사용자 정보를 가져온 상태에서 추가로 진행하고자 하는 기능
    }
}
