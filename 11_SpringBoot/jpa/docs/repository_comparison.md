# Repository 구현 방식 비교

## 개요
이 프로젝트에는 **두 가지 Repository 구현 방식**이 공존합니다:

1. **JPQL 방식** (EntityManager + 직접 구현)
2. **Spring Data JPA 방식** (JpaRepository 상속)

---

## 1. JPQL 방식 (기존 코드)

### 구조
```
MemberRepository (interface)
    ↓ 구현
MemberRepositoryImpl (class)
    - EntityManager 사용
    - JPQL 쿼리 직접 작성
```

### 장점
- **쿼리를 직접 제어**: 복잡한 쿼리를 세밀하게 작성 가능
- **학습 목적**: JPA의 내부 동작 원리 이해에 유리
- **유연성**: EntityManager의 모든 기능 활용 가능

### 단점
- **보일러플레이트 코드**: 반복적인 코드 작성 필요
- **개발 생산성**: 기본 CRUD도 직접 구현해야 함
- **오타 위험**: 문자열 기반 JPQL 쿼리로 컴파일 타임 검증 제한적

### 파일 목록
- `MemberRepository.java` + `MemberRepositoryImpl.java`
- `BoardRespository.java` + `BoardRepositoryImpl.java`
- `TagRepositoryImpl.java`

---

## 2. Spring Data JPA 방식 (신규 추가)

### 구조
```
MemberJpaRepository extends JpaRepository<Member, String>
    - 인터페이스만 선언
    - 구현체는 Spring Data JPA가 자동 생성
```

### 장점
- **생산성 향상**: 기본 CRUD 메서드 자동 제공
- **간결한 코드**: 메서드 이름만으로 쿼리 자동 생성
- **타입 안정성**: 컴파일 타임에 메서드 시그니처 검증
- **페이징/정렬**: Pageable, Sort 자동 지원

### 단점
- **학습 곡선**: 메서드 네이밍 규칙 학습 필요
- **블랙박스**: 내부 동작이 추상화되어 있음
- **복잡한 쿼리**: 매우 복잡한 쿼리는 여전히 @Query 필요

### 파일 목록
- `MemberJpaRepository.java`
- `BoardJpaRepository.java`
- `TagJpaRepository.java`

---

## 코드 비교

### 예제 1: 이름으로 회원 검색

#### JPQL 방식 (MemberRepositoryImpl.java)
```java
@Override
public List<Member> findByName(String name) {
    String query = "select m from Member m where m.userName LIKE :username";
    return em.createQuery(query, Member.class)
            .setParameter("username", "%" + name + "%")
            .getResultList();
}
```

#### Spring Data JPA 방식 (MemberJpaRepository.java)
```java
// Method Query - 메서드 이름만으로 자동 생성
List<Member> findByUserNameContaining(String name);

// 또는 @Query 사용
@Query("select m from Member m where m.userName LIKE %:username%")
List<Member> findByNameWithQuery(@Param("username") String name);
```

---

### 예제 2: 상태별 게시글 페이징 조회

#### JPQL 방식 (BoardRepositoryImpl.java)
```java
@Override
public Page<Board> findByStatus(CommonEnums.Status status, Pageable pageable) {
    // 데이터 조회
    String query = "select b from Board b where b.status=:status";
    List<Board> boards = em.createQuery(query, Board.class)
            .setParameter("status", status)
            .setFirstResult((int) pageable.getOffset())
            .setMaxResults(pageable.getPageSize())
            .getResultList();

    // 전체 개수 조회
    String countQuery = "select count(b) from Board b where b.status=:status";
    Long totalCount = em.createQuery(countQuery, Long.class)
            .setParameter("status", status)
            .getSingleResult();

    return new PageImpl<>(boards, pageable, totalCount);
}
```

#### Spring Data JPA 방식 (BoardJpaRepository.java)
```java
// Method Query - 한 줄로 끝!
Page<Board> findByStatus(CommonEnums.Status status, Pageable pageable);
```

---

### 예제 3: 태그 이름으로 조회

#### JPQL 방식 (TagRepositoryImpl.java)
```java
public Optional<Tag> findByTagName(String tagName) {
    String query = "select t from Tag t where t.tagName = :tagName";
    List<Tag> tags = em.createQuery(query, Tag.class)
            .setParameter("tagName", tagName)
            .getResultList();

    return tags.isEmpty() ? Optional.empty() : Optional.of(tags.get(0));
}
```

#### Spring Data JPA 방식 (TagJpaRepository.java)
```java
// Method Query
Optional<Tag> findByTagName(String tagName);
```

---

## Spring Data JPA 주요 기능

### 1. Method Query (메서드 네이밍 규칙)

#### 기본 패턴
```java
// findBy + 필드명 + 조건
findByUserName(String name)                    // WHERE user_name = ?
findByUserNameAndEmail(String name, String email) // WHERE user_name = ? AND email = ?
findByUserNameOrEmail(String name, String email)  // WHERE user_name = ? OR email = ?
```

#### 조건 키워드
```java
findByUserNameContaining(String name)          // LIKE %name%
findByUserNameStartingWith(String name)        // LIKE name%
findByUserNameEndingWith(String name)          // LIKE %name
findByAgeBetween(int min, int max)             // WHERE age BETWEEN min AND max
findByAgeGreaterThan(int age)                  // WHERE age > ?
findByAgeLessThan(int age)                     // WHERE age < ?
findByEmailIsNull()                            // WHERE email IS NULL
findByEmailIsNotNull()                         // WHERE email IS NOT NULL
```

#### 정렬
```java
findByUserNameOrderByAgeDesc(String name)      // ORDER BY age DESC
```

#### 개수 제한
```java
findTop10ByStatusOrderByCountDesc(Status status) // LIMIT 10
findFirst5ByGender(Gender gender)              // LIMIT 5
```

### 2. @Query 애노테이션

#### JPQL 사용
```java
@Query("select m from Member m where m.userName LIKE %:username%")
List<Member> findByNameWithQuery(@Param("username") String name);
```

#### Native Query 사용
```java
@Query(value = "SELECT * FROM MEMBER WHERE USER_NAME LIKE %?1%", nativeQuery = true)
List<Member> findByNameNative(String name);
```

### 3. @Modifying (UPDATE/DELETE)

```java
@Query("update Board b set b.count = b.count + 1 where b.boardNo = :boardNo")
@Modifying
void incrementCount(@Param("boardNo") Long boardNo);
```

### 4. 페이징과 정렬

```java
// Pageable 파라미터 추가
Page<Board> findByStatus(Status status, Pageable pageable);

// Service에서 사용
Pageable pageable = PageRequest.of(0, 10, Sort.by("createDate").descending());
Page<Board> page = boardJpaRepository.findByStatus(Status.Y, pageable);
```

---

## 사용 방법

### Service에서 선택적으로 사용

두 가지 방식을 모두 주입받아 상황에 따라 선택 가능:

```java
@Service
@RequiredArgsConstructor
public class MemberService {

    // JPQL 방식
    private final MemberRepository memberRepository;

    // Spring Data JPA 방식
    private final MemberJpaRepository memberJpaRepository;

    public void exampleMethod() {
        // JPQL 방식 사용
        List<Member> result1 = memberRepository.findByName("김");

        // Spring Data JPA 방식 사용
        List<Member> result2 = memberJpaRepository.findByUserNameContaining("김");
    }
}
```

---

## 추가된 Spring Data JPA 메서드 목록

### MemberJpaRepository

| 메서드 | 설명 | 타입 |
|--------|------|------|
| `findByUserNameContaining(String)` | 이름 검색 (LIKE) | Method Query |
| `findByNameWithQuery(String)` | 이름 검색 (@Query) | JPQL |
| `findByEmail(String)` | 이메일 조회 | Method Query |
| `findByAgeBetween(Integer, Integer)` | 나이 범위 조회 | Method Query |
| `findByGender(Gender)` | 성별 조회 | Method Query |
| `findByStatus(Status)` | 상태별 조회 | JPQL |

### BoardJpaRepository

| 메서드 | 설명 | 타입 |
|--------|------|------|
| `findByStatus(Status, Pageable)` | 상태별 조회 (페이징) | Method Query |
| `findByMember(Member)` | 작성자로 조회 | Method Query |
| `findByMemberUserId(String)` | 작성자 ID로 조회 | Method Query |
| `findByBoardTitleContaining(String)` | 제목 검색 | Method Query |
| `findByBoardTitleContainingOrBoardContentContaining(...)` | 제목+내용 검색 | Method Query |
| `findTop10ByStatusOrderByCountDesc(Status)` | 인기 게시글 Top 10 | Method Query |
| `findByUserIdAndStatus(String, Status, Pageable)` | 작성자+상태 조회 | JPQL |
| `searchByKeyword(String, Status, Pageable)` | 키워드 검색 | JPQL |
| `findByTagName(String, Status, Pageable)` | 태그별 조회 | JPQL + Join |
| `incrementCount(Long)` | 조회수 증가 | JPQL + @Modifying |

### TagJpaRepository

| 메서드 | 설명 | 타입 |
|--------|------|------|
| `findByTagName(String)` | 태그 이름 조회 | Method Query |
| `findByTagNameWithQuery(String)` | 태그 이름 조회 (@Query) | JPQL |
| `existsByTagName(String)` | 태그 존재 여부 | Method Query |
| `findByTagNameContaining(String)` | 태그 검색 | Method Query |
| `findUsedTags()` | 사용 중인 태그 | JPQL + Join |
| `findPopularTags()` | 인기 태그 (전체) | JPQL + GROUP BY |
| `findTopNPopularTags(int)` | 인기 태그 (N개) | Native Query |

---

## 권장 사용 시나리오

### JPQL 방식을 사용하는 경우
- ✅ 매우 복잡한 동적 쿼리
- ✅ 여러 테이블을 복잡하게 조인
- ✅ 성능 최적화가 필수적인 경우
- ✅ JPA 내부 동작 학습 목적

### Spring Data JPA 방식을 사용하는 경우
- ✅ 단순 CRUD 작업
- ✅ 기본적인 조회/검색
- ✅ 페이징/정렬이 필요한 경우
- ✅ 빠른 개발이 필요한 경우
- ✅ 대부분의 실무 프로젝트

---

## 결론

- **학습 단계**: JPQL 방식으로 JPA 이해도를 높인 후 → Spring Data JPA 활용
- **실무 프로젝트**: Spring Data JPA를 기본으로 사용, 필요시 @Query로 보완
- **이 프로젝트**: 두 가지 방식을 모두 경험하고 비교해볼 수 있는 좋은 학습 환경!

---

## 참고 자료

- [Spring Data JPA 공식 문서](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/)
- [JPA 프로그래밍 - 김영한](https://www.inflearn.com/course/ORM-JPA-Basic)
- [Method Query Keywords](https://docs.spring.io/spring-data/jpa/docs/current/reference/html/#jpa.query-methods.query-creation)
