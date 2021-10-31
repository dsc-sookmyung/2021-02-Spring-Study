package com.jojoldu.book.springboot.domain.posts;

import com.jojoldu.book.springboot.domain.BaseTimeEntity;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Getter  // 롬복 어노테이션 - 클래스 내의 모든 필드의 Getter 메소드를 자동생성
@NoArgsConstructor  // 롬복 어노테이션 - 기본 생성자 자동 추가, public Posts() {}와 같은 효과
@Entity  // 테이블과 링크될 클래스임을 나타냄. 카멜케이스 네이밍인 클래스와 언더스코어 네이밍 테이블과 매칭
public class Posts extends BaseTimeEntity {
    @Id  // 해당 테이블의 PK (primary key) 필드
    @GeneratedValue(strategy = GenerationType.IDENTITY)  // JPA 어노테이션 - PK의 생성 규칙
    private Long id;

    @Column(length = 500, nullable = false)  // 테이블의 칼럼 (굳이 선언 안 해도 필드는 모두 칼럼이 됨. 추가로 필요한 변경 옵션 있을 때 사용. 문자열 VARCHAR 255가 기본값인데 )
    private String title;

    @Column(columnDefinition = "TEXT", nullable = false)
    private String content;

    private String author;

    @Builder  // 롬복 어노테이션 - 해당 클래스의 빌더 패턴 클래스 생성. 생성자 상단에 선언 시 생성자에 포함된 필드만 빌더에 포함
    public Posts(String title, String content, String author) {
        this.title = title;
        this.content = content;
        this.author = author;
    }

    public void update(String title, String content) {
        this.title = title;
        this.content = content;
    }
}
