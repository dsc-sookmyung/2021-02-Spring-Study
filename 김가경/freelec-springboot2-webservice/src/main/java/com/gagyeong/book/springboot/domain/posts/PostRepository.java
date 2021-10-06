package com.gagyeong.book.springboot.domain.posts;

import org.springframework.data.jpa.repository.JpaRepository;

public interface PostRepository extends JpaRepository<Posts, Long> {
}
