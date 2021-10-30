# &lt;스프링부트와 AWS로 혼자 구현하는 웹 서비스> 따라해보기

> [이동욱님 책](http://www.yes24.com/Product/Goods/83849117) 보고 하는 공부 

</br>

## CH.3 스프링 부트에서 JPA로 데이터베이스 다뤄보자 
### 🌱 1. JPA 소개 
- JPA 쓰는 이유 : 구현체와 저장소 교체가 쉬움
- 개발자가 객체지향적으로 프로그래밍을 하고 JPA가 관계형 데이터베이스에 맞게 SQL 대신 생성해서 실행 

### 🌱 2. 프로젝트에 Spring Data JPA 적용하기 
- **@Entity** : DB의 테이블과 매칭될 클래스 나타내는 annotation 
- **@Getter** : 클래스 내 모든 필드의 getter 메소드 자동 생성 
- Entity class에는 절대 setter 메소드 만들지 ❌
- 값 변경이 필요하면 목적과 의도 명확하게 나타내는 메소드 추가 
- **@Builder** : 값 채워 넣을 때 사용. 어느 필드에 어떤 값을 채워야 할지 명확하게 인지 가능 

### 🌱 3. Spring Data JPA 테스트 모드 작성하기 
- **@After**  : 단위 테스트가 끝날 때마다 수행되는 메소드 지정 
- @SpringBootTest 사용할 경우 자동으로 H2 데이터베이스 실행 

### 🌱 4. 등록/수정/조회 API 만들기 
- web layer → API 요청을 받을 Controller. 뷰 템플릿 영역
- service layer → @service. Controller와 Dto 사이의 영역. @Transactional 사용되는 영역. 트랜젝션, 도메인 기능 간의 순서 보장 
- repository layer → 데이터 저장소에 접근하는 영역. 
- Dto → 계층 간에 데이터 교환을 위한 객체 
- Domain model → 비즈니스 처리 담당. @Entity가 사용된 영역 

### 🌱 5. JPA Auditing으로 생성시간/수정시간 자동화하기 
- **@MappedSuperClass** : JPA Entity들이 믈래스를 상속할 경우 필드들도 칼럼으로 인식하게 만드는 annotation

#### 😭TroubleShooting😭
- 이번에도 깃헙과 한 판 싸웠습니다... 개인 레포지토리에도 올리고 그룹 레포지토리에도 올리는 방법을 몰라 고생했었고 지난 번에 깃허브에서 pull받을 때 문제가 있었어서 push했던 레포지토리가 열리지 않아 개인 레포에 있던 플젝을 가져와 브랜치를 다시 파서 올렸습니다...
- 처음에 프로젝트 열었을 때 실행이 안돼서 당황했었는데 intellij에서 프로젝트를 열려면 root폴더에 gradle settings 파일이 있어야 한다고 합니다. 그래서 settings 파일이 들어있는 상위 폴더를 새 프로젝트로 열었더니 gradle이 잘 돌아가는 것을 볼 수 있었습니다! 

</br> 

--- 

## CH4. 머스테치로 화면 구성하기 
### 🌱 1. 서버 템플릿 엔진과 머스테치 소개 
- **템플릿 엔진** : 지정된 템플릿 양식과 데이터가 합쳐져 HTML문서 출력하는 SW ex) React의 View파일들 
- **서버 템플릿 엔진** : 서버에서 구동. 서버에서 자바로 문자열 만들고 HTML로 변환해서 브라우저로 전달. js - 단순한 문자열
- **클라 템플릿 엔진** : 브라우저에서 화면 생성. 서버에서 json or xml 형식 데이터만 전달. 클라이언트에서 조립. 
- **머스테치** : 수많은 언어를 지원하는 가장 심플한 템플릿 엔진. 자바에서는 서버 템플릿 엔진으로
   `compile('org.springframework.boot:spring-boot-starter-mustache')`
### 🌱 2. 기본 페이지 만들기 
- 머스테치 파일 기본 위치 : `src/main/resources/templates`
    머스테치 스타터 → 경로와 확장자는 자동 지정됨 
- Controller에 URL mapping 하기 (IndexController.java)



### 🌱 3. 게시글 등록 화면 만들기 
- FE 라이브러리 사용하는 방법 :
    1. 외부 CDN 사용 4
    2. 직접 라이브러리 받아서 사용 
- **레이아웃 방식**으로 부트스트랩과 제이쿼리 index.mustache에 추가
    → 공통 영역을 별도의 파일로 분리하여 필요할 때 가져다 쓰는 방식     
- HTML은 위에서부터 코드가 진행됨. css를 윗 부분에, js는 아래에 쓰는 것이 좋음
- 게시글을 새로 쓰는 페이지인 `posts-save.mustache` 파일 만들기
- 버튼에 기능을 주기 위해 API 호출하는 js파일 생성 `index.js`
- `index.js` 에서 변수 속성으로 함수 추가한 이유 :    
    → 브라우저의 scope는 공용공간으로, 다른 js파일의 이름이 동일한 함수가 있다면 덮어씌우기 때문에 `index.js`만의 scope(유효범위)를 만든 것.


### 🌱 4. 전체 조회 화면 만들기 
**머스테치 문법** 
1. `{{#posts}}` posts 리스트 순회. for문과 동일하다고 생각 
2. `{{변수명}}` 객체의 필드 사용 
- IndexController에서 쓰이는 **Model** :
    `postsService.findAllDesc()` 로 가져온 결과 posts로 index.mustache에 전달

### 🌱 5. 게시글 수정, 삭제 화면 만들기 
- REST에서 CRUD HTTP Method에서 매핑
    1. Create → POST (@PostMapping) 
    2. Read → GET (@GetMapping)
    3. Update → PUT (@PutMapping)
    4. Delete → DELETE (@DelteMapping) 
- `index.js` 에서 버튼 등록하기

```cpp
$('#btn-name').on('click', function(){
	처리할 함수 ;
});
```

