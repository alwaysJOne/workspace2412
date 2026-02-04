# Service 전환 가이드

## 개요

이 프로젝트에는 **두 가지 Service 구현 방식**이 공존하며, Controller에서 **주석만으로 쉽게 전환**할 수 있습니다.

---

## 1. 현재 구조

### Service 종류

| 방식 | Service 클래스 | Repository | 특징 |
|------|----------------|------------|------|
| **Spring Data JPA** | `MemberServiceJpa`<br>`BoardServiceJpa` | `MemberJpaRepository`<br>`BoardJpaRepository`<br>`TagJpaRepository` | 간결한 코드<br>생산성 높음<br>**현재 사용 중** |
| **JPQL (EntityManager)** | `MemberService`<br>`BoardService` | `MemberRepositoryImpl`<br>`BoardRepositoryImpl`<br>`TagRepositoryImpl` | 직접 제어<br>학습 목적<br>복잡한 쿼리 |

---

## 2. 빠른 전환 방법

### ✅ 현재 상태: Spring Data JPA 사용 중

#### MemberController.java (Line 25-30)
```java
// ========== Service 선택 (주석으로 전환) ==========
// 1. Spring Data JPA 버전 (현재 사용 중)
private final MemberServiceJpa memberService;

// 2. JPQL 버전 (EntityManager 직접 사용)
// private final MemberService memberService;
```

#### BoardController.java (Line 31-36)
```java
// ========== Service 선택 (주석으로 전환) ==========
// 1. Spring Data JPA 버전 (현재 사용 중)
private final BoardServiceJpa boardService;

// 2. JPQL 버전 (EntityManager 직접 사용)
// private final BoardService boardService;
```

---

### 🔄 JPQL 방식으로 전환하기

**수업에서 JPQL을 설명할 때 아래와 같이 변경**

#### MemberController.java 수정
```java
// ========== Service 선택 (주석으로 전환) ==========
// 1. Spring Data JPA 버전 (현재 사용 중)
// private final MemberServiceJpa memberService;

// 2. JPQL 버전 (EntityManager 직접 사용)
private final MemberService memberService;
```

#### BoardController.java 수정
```java
// ========== Service 선택 (주석으로 전환) ==========
// 1. Spring Data JPA 버전 (현재 사용 중)
// private final BoardServiceJpa boardService;

// 2. JPQL 버전 (EntityManager 직접 사용)
private final BoardService boardService;
```

**주의**: 두 줄만 주석 전환하면 됩니다!
- 기존 사용 중인 Service를 주석 처리 (`//` 추가)
- 사용할 Service의 주석 해제 (`//` 제거)

---

## 3. 수업 진행 순서 (권장)

### 1단계: Spring Data JPA 먼저 (현재 상태)
**목적**: 빠르게 JPA의 편리함 체험

```java
// Controller - 현재 상태 그대로 사용
private final MemberServiceJpa memberService;
private final BoardServiceJpa boardService;
```

**설명 포인트**:
- ✅ JpaRepository 상속만으로 기본 CRUD 자동 제공
- ✅ Method Query로 간단한 쿼리 자동 생성
- ✅ 페이징/정렬 자동 지원
- ✅ 생산성이 매우 높음

**실습**:
- `MemberJpaRepository.java` 코드 확인
- `findByUserNameContaining()` 같은 Method Query 사용법
- `BoardServiceJpa.java`에서 Repository 사용 방식

---

### 2단계: JPQL (EntityManager) 학습
**목적**: JPA 내부 동작 원리 이해

```java
// Controller - 주석 전환
// private final MemberServiceJpa memberService;
private final MemberService memberService;

// private final BoardServiceJpa boardService;
private final BoardService boardService;
```

**설명 포인트**:
- ✅ EntityManager를 직접 사용
- ✅ JPQL 쿼리 직접 작성
- ✅ 페이징 처리를 수동으로 구현
- ✅ JPA의 내부 동작 원리 이해

**실습**:
- `MemberRepositoryImpl.java` 코드 확인
- JPQL 쿼리 문법 학습
- `BoardRepositoryImpl.java`에서 페이징 처리 방식

---

## 4. 코드 비교

### 예제 1: 전체 회원 조회

#### Spring Data JPA 방식 (MemberServiceJpa.java)
```java
public List<MemberDto.Response> findAllMember() {
    return memberJpaRepository.findAll() // 한 줄로 끝!
            .stream()
            .map(member -> MemberDto.Response.of(...))
            .toList();
}
```

#### JPQL 방식 (MemberService.java)
```java
public List<MemberDto.Response> findAllMember() {
    return memberRepository.findAll() // 직접 JPQL 실행
            .stream()
            .map(member -> MemberDto.Response.of(...))
            .toList();
}

// MemberRepositoryImpl.java
public List<Member> findAll() {
    return em.createQuery("select m from Member m", Member.class)
            .getResultList();
}
```

---

### 예제 2: 상태별 게시글 페이징 조회

#### Spring Data JPA 방식 (BoardServiceJpa.java)
```java
public Page<BoardDto.Response> getBoardList(Pageable pageable) {
    // Method Query로 한 줄!
    Page<Board> page = boardJpaRepository.findByStatus(
        CommonEnums.Status.Y,
        pageable
    );

    return page.map(board -> BoardDto.Response.ofSimple(...));
}
```

#### JPQL 방식 (BoardService.java)
```java
public Page<BoardDto.Response> getBoardList(Pageable pageable) {
    Page<Board> page = boardRespository.findByStatus(
        CommonEnums.Status.Y,
        pageable
    );

    return page.map(board -> BoardDto.Response.ofSimple(...));
}

// BoardRepositoryImpl.java
public Page<Board> findByStatus(Status status, Pageable pageable) {
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

---

## 5. 파일 위치 정리

### Spring Data JPA 관련 파일
```
src/main/java/com/kh/jpa/
├── repository/
│   ├── MemberJpaRepository.java      ← JpaRepository 상속
│   ├── BoardJpaRepository.java       ← JpaRepository 상속
│   └── TagJpaRepository.java         ← JpaRepository 상속
└── service/
    ├── MemberServiceJpa.java         ← Spring Data JPA 사용
    └── BoardServiceJpa.java          ← Spring Data JPA 사용
```

### JPQL (EntityManager) 관련 파일
```
src/main/java/com/kh/jpa/
├── repository/
│   ├── MemberRepository.java         ← 인터페이스
│   ├── MemberRepositoryImpl.java     ← EntityManager 사용
│   ├── BoardRespository.java         ← 인터페이스
│   ├── BoardRepositoryImpl.java      ← EntityManager 사용
│   └── TagRepositoryImpl.java        ← EntityManager 사용
└── service/
    ├── MemberService.java            ← JPQL 사용
    └── BoardService.java             ← JPQL 사용
```

### Controller 파일 (전환 지점)
```
src/main/java/com/kh/jpa/controller/
├── MemberController.java             ← 여기서 Service 선택
└── BoardController.java              ← 여기서 Service 선택
```

---

## 6. 전환 체크리스트

### Spring Data JPA → JPQL 전환 시

- [ ] `MemberController.java` Line 27 주석 처리
- [ ] `MemberController.java` Line 30 주석 해제
- [ ] `BoardController.java` Line 33 주석 처리
- [ ] `BoardController.java` Line 36 주석 해제
- [ ] 애플리케이션 재시작
- [ ] API 테스트 (기능은 동일해야 함)

### JPQL → Spring Data JPA 전환 시

- [ ] `MemberController.java` Line 27 주석 해제
- [ ] `MemberController.java` Line 30 주석 처리
- [ ] `BoardController.java` Line 33 주석 해제
- [ ] `BoardController.java` Line 36 주석 처리
- [ ] 애플리케이션 재시작
- [ ] API 테스트 (기능은 동일해야 함)

---

## 7. 자주 묻는 질문 (FAQ)

### Q1. 두 개를 동시에 사용할 수 있나요?
**A**: 가능하지만 권장하지 않습니다. Controller에서는 하나만 선택해서 사용하세요.

### Q2. 전환 후 재시작이 필요한가요?
**A**: 네, Spring Bean 주입이 변경되므로 애플리케이션을 재시작해야 합니다.

### Q3. 어떤 방식을 먼저 배워야 하나요?
**A**: Spring Data JPA를 먼저 배우면 JPA의 편리함을 빠르게 체험할 수 있습니다. 이후 JPQL을 배우면 내부 동작을 이해하는 데 도움이 됩니다.

### Q4. 실무에서는 어떤 방식을 사용하나요?
**A**: 대부분 Spring Data JPA를 기본으로 사용하고, 복잡한 쿼리가 필요할 때만 `@Query`로 JPQL을 작성합니다.

### Q5. API 엔드포인트는 동일한가요?
**A**: 네, Controller는 동일하므로 API 엔드포인트와 동작은 완전히 같습니다. 내부 구현 방식만 다릅니다.

---

## 8. 실습 예제

### 실습 1: Spring Data JPA 체험 (현재 상태)

1. 애플리케이션 실행
2. Postman으로 API 테스트
   - GET `/api/members` - 전체 회원 조회
   - GET `/api/boards?page=0&size=5` - 게시글 목록 (페이징)
3. 로그 확인: Hibernate가 자동 생성한 SQL 확인

### 실습 2: JPQL 방식으로 전환

1. Controller 주석 변경 (위 체크리스트 참고)
2. 애플리케이션 재시작
3. 동일한 API 테스트
4. 로그 확인: JPQL이 SQL로 변환되는 과정 확인
5. Repository 코드 비교: 어떤 차이가 있는지 확인

### 실습 3: 코드 비교

1. `MemberJpaRepository.java` vs `MemberRepositoryImpl.java`
2. `BoardServiceJpa.java` vs `BoardService.java`
3. 어떤 방식이 더 간결한지, 어떤 방식이 더 제어가 쉬운지 비교

---

## 9. 정리

### Spring Data JPA (현재 사용 중)
- ✅ **빠른 개발**: 기본 CRUD 자동 제공
- ✅ **간결한 코드**: Method Query로 쿼리 자동 생성
- ✅ **실무 표준**: 대부분의 Spring Boot 프로젝트에서 사용
- ❌ **학습 곡선**: 처음엔 블랙박스처럼 느껴질 수 있음

### JPQL (EntityManager)
- ✅ **완전한 제어**: 쿼리를 직접 작성
- ✅ **학습 효과**: JPA 내부 동작 이해에 유리
- ✅ **복잡한 쿼리**: 세밀한 제어가 필요할 때 유용
- ❌ **보일러플레이트**: 기본 CRUD도 직접 구현 필요

---

## 10. 추천 학습 순서

```
1. Spring Data JPA 체험 (현재 상태)
   ↓
2. Repository 코드 확인
   ↓
3. JPQL로 전환 (Controller 주석 변경)
   ↓
4. Repository 코드 비교
   ↓
5. 내부 동작 이해
   ↓
6. 실무에서는 Spring Data JPA 사용 권장
```

**주석 2줄만 바꾸면 즉시 전환됩니다!** 🚀
