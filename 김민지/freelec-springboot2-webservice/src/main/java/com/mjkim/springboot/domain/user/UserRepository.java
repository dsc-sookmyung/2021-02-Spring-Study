package com.mjkim.springboot.domain.user;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    Optional<User> findByEmail(String email); // 처음 가입하는지 이미 생성된 사용자인지 email로 판단
}
