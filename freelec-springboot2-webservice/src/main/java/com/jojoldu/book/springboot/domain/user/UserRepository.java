package com.jojoldu.book.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); //소셜로그인으로 반환되는 값 중 email을 통해 이미생성된/새로가입한 사용자 판단
}
