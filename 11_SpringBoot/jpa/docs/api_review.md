# API 구조 실무 검토

## 전체 평가

현재 프로젝트는 **전반적으로 실무에서 사용하는 표준적인 Spring Boot + JPA 구조**를 잘 따르고 있습니다.
다만 몇 가지 개선 포인트가 있습니다.

---

## ✅ 잘 구현된 부분

### 1. **3계층 아키텍처 (Layered Architecture)**
```
Controller → Service → Repository
```
- ✅ 각 계층의 책임이 명확하게 분리됨
- ✅ Controller는 HTTP 요청/응답만 처리
- ✅ Service는 비즈니스 로직 처리
- ✅ Repository는 데이터 접근만 담당
- **실무 평가**: 표준적인 구조, 매우 좋음 👍

### 2. **DTO 패턴**
```java
public class MemberDto {
    public static class Create { }    // 생성용
    public static class Update { }    // 수정용
    public static class Response { }  // 응답용
}
```
- ✅ Entity를 직접 노출하지 않음
- ✅ 용도별로 DTO 분리 (Create, Update, Response)
- ✅ Inner static class로 구조화
- **실무 평가**: 실무에서도 많이 사용하는 패턴 👍

### 3. **ResponseEntity 사용**
```java
@GetMapping("/{userId}")
public ResponseEntity<MemberDto.Response> getMember(@PathVariable String userId) {
    return ResponseEntity.ok(memberService.findMember(userId));
}
```
- ✅ HTTP 상태 코드 명시적 제어
- ✅ 응답 타입 제네릭으로 명확히 선언
- **실무 평가**: 표준적인 방식 👍

### 4. **RESTful API 설계**
```
GET    /api/members          - 전체 조회
GET    /api/members/{id}     - 단건 조회
POST   /api/members          - 생성
PUT    /api/members/{id}     - 수정
DELETE /api/members/{id}     - 삭제
```
- ✅ HTTP 메서드를 적절히 사용
- ✅ 리소스 중심의 URI 설계
- ✅ PathVariable로 ID 전달
- **실무 평가**: RESTful 원칙을 잘 따름 👍

### 5. **페이징 처리**
```java
@GetMapping
public ResponseEntity<PageResponse<BoardDto.Response>> getBoards(
    @PageableDefault(size = 5, sort = "createDate", direction = Sort.Direction.DESC)
    Pageable pageable
) {
    return ResponseEntity.ok(new PageResponse<>(boardService.getBoardList(pageable)));
}
```
- ✅ Spring Data의 Pageable 활용
- ✅ 기본값 설정 (@PageableDefault)
- ✅ 커스텀 PageResponse DTO로 wrapping
- **실무 평가**: 매우 좋은 방식 👍

### 6. **트랜잭션 관리**
```java
@Service
@Transactional(readOnly = true)
public class BoardService {
    @Transactional
    public Long createBoard(...) { }
}
```
- ✅ 클래스 레벨에서 readOnly 기본 설정
- ✅ 변경 작업에만 @Transactional 추가
- **실무 평가**: 효율적인 트랜잭션 관리 👍

### 7. **파일 업로드 처리**
```java
@PostMapping
public ResponseEntity<Long> createBoard(
    @ModelAttribute BoardDto.Create boardCreate
) throws IOException { }
```
- ✅ @ModelAttribute로 multipart 데이터 받기
- ✅ UUID로 파일명 중복 방지
- **실무 평가**: 기본적인 파일 업로드 구현은 적절함 👍

---

## ⚠️ 개선이 필요한 부분

### 1. **예외 처리 일관성 부족** ⭐ 중요
**현재 문제점**:
```java
// MemberService.java
throw new IllegalArgumentException("존재하지 않는 회원입니다.");

// BoardService.java
throw new EntityNotFoundException("게시글을 찾을 수 없습니다.");
```
- ❌ 같은 상황에 다른 예외 사용 (일관성 없음)
- ❌ 전역 예외 처리(@ControllerAdvice) 없음
- ❌ 클라이언트가 받는 에러 응답 형식이 일관적이지 않음

**실무 권장 방식**:
```java
// 1. 커스텀 예외 정의
public class ResourceNotFoundException extends RuntimeException {
    public ResourceNotFoundException(String message) {
        super(message);
    }
}

// 2. 전역 예외 처리
@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<ErrorResponse> handleResourceNotFound(
            ResourceNotFoundException ex
    ) {
        ErrorResponse error = new ErrorResponse(
            "NOT_FOUND",
            ex.getMessage(),
            LocalDateTime.now()
        );
        return ResponseEntity.status(HttpStatus.NOT_FOUND).body(error);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorResponse> handleException(Exception ex) {
        // 로깅 및 일반 에러 응답
    }
}

// 3. 일관된 에러 응답 DTO
@Getter
@AllArgsConstructor
public class ErrorResponse {
    private String code;
    private String message;
    private LocalDateTime timestamp;
}
```

### 2. **응답 형식 불일치** ⭐ 중요
**현재 문제점**:
```java
// 회원 생성 → String(userId) 반환
@PostMapping
public ResponseEntity<String> addMember(@RequestBody MemberDto.Create createDto) {
    String userId = memberService.createMember(createDto);
    return ResponseEntity.ok(userId);
}

// 게시글 생성 → Long(boardNo) 반환
@PostMapping
public ResponseEntity<Long> createBoard(@ModelAttribute BoardDto.Create boardCreate) {
    return ResponseEntity.ok(boardService.createBoard(boardCreate));
}

// 회원 수정 → Response DTO 반환
@PutMapping("/{userId}")
public ResponseEntity<MemberDto.Response> updateMember(...) {
    return ResponseEntity.ok(memberService.updateMember(userId, updateDto));
}
```
- ❌ 생성 API 응답이 일관적이지 않음
- ❌ 클라이언트가 ID만 받으면 다시 조회 API를 호출해야 함

**실무 권장 방식**:
```java
// 생성 후 전체 데이터 반환 또는 URI 반환
@PostMapping
public ResponseEntity<MemberDto.Response> addMember(
        @RequestBody MemberDto.Create createDto
) {
    MemberDto.Response response = memberService.createMember(createDto);
    return ResponseEntity
            .status(HttpStatus.CREATED)  // 201 상태 코드
            .body(response);
}

// 또는 Location Header 사용
@PostMapping
public ResponseEntity<Void> addMember(@RequestBody MemberDto.Create createDto) {
    String userId = memberService.createMember(createDto);
    URI location = URI.create("/api/members/" + userId);
    return ResponseEntity.created(location).build();
}
```

### 3. **HTTP 상태 코드 미흡**
**현재 문제점**:
```java
@PostMapping
public ResponseEntity<String> addMember(...) {
    String userId = memberService.createMember(createDto);
    return ResponseEntity.ok(userId);  // 200 OK
}

@DeleteMapping("/{userId}")
public ResponseEntity<Void> deleteMember(@PathVariable String userId) {
    memberService.deleteMember(userId);
    return ResponseEntity.ok().build();  // 200 OK
}
```
- ❌ POST → 200 대신 201 Created를 사용해야 함
- ❌ DELETE → 200 대신 204 No Content가 더 적절

**실무 권장 방식**:
```java
@PostMapping
public ResponseEntity<MemberDto.Response> addMember(...) {
    return ResponseEntity
            .status(HttpStatus.CREATED)  // 201
            .body(response);
}

@DeleteMapping("/{userId}")
public ResponseEntity<Void> deleteMember(@PathVariable String userId) {
    memberService.deleteMember(userId);
    return ResponseEntity.noContent().build();  // 204
}
```

### 4. **파일 업로드 경로 하드코딩**
**현재 문제점**:
```java
private final String UPLOAD_PATH = "C:\\dev_tool";
```
- ❌ 절대 경로 하드코딩
- ❌ Windows 전용 경로 (배포 환경에서 문제)
- ❌ application.yml로 설정 가능해야 함

**실무 권장 방식**:
```yaml
# application.yml
file:
  upload:
    path: ${user.home}/uploads  # 환경 변수 사용
    # 또는 /var/app/uploads (Linux)
```

```java
@Service
public class BoardService {
    @Value("${file.upload.path}")
    private String uploadPath;
}
```

### 5. **검색 엔드포인트 설계**
**현재 구조**:
```java
GET /api/members/search/name?name=김철수
```
- ⚠️ 동작은 하지만, 검색 조건이 늘어나면 엔드포인트가 계속 증가

**실무 권장 방식 (선택사항)**:
```java
// 방법 1: 쿼리 파라미터 활용
GET /api/members?name=김철수&age=25&gender=M

@GetMapping
public ResponseEntity<List<MemberDto.Response>> searchMembers(
    @RequestParam(required = false) String name,
    @RequestParam(required = false) Integer age,
    @RequestParam(required = false) Gender gender
) { }

// 방법 2: 검색 조건 DTO
@GetMapping("/search")
public ResponseEntity<List<MemberDto.Response>> searchMembers(
    @ModelAttribute MemberSearchCondition condition
) { }
```

### 6. **Board 수정에 PATCH vs PUT 혼용**
**현재**:
```java
@PatchMapping("/{id}")
public ResponseEntity<BoardDto.Response> updateBoard(...) { }
```
- ⚠️ PATCH 사용 중
- ⚠️ 하지만 전체 필드를 수정하는 경우 PUT이 더 적절

**실무 권장**:
- **PUT**: 전체 리소스 교체 (모든 필드 포함)
- **PATCH**: 부분 수정 (일부 필드만 변경)

현재 코드는 제목, 내용, 파일, 태그 등 여러 필드를 수정하므로 **PUT이 더 적절**

### 7. **Validation 누락**
**현재 문제점**:
```java
@PostMapping
public ResponseEntity<String> addMember(@RequestBody MemberDto.Create createDto) {
    // 유효성 검증 없음
}
```

**실무 권장 방식**:
```java
// DTO에 검증 애노테이션 추가
public static class Create {
    @NotBlank(message = "아이디는 필수입니다")
    @Size(min = 4, max = 30)
    private String user_id;

    @NotBlank
    @Email
    private String email;

    @Min(0)
    @Max(150)
    private Integer age;
}

// Controller에서 @Valid 사용
@PostMapping
public ResponseEntity<MemberDto.Response> addMember(
    @Valid @RequestBody MemberDto.Create createDto
) { }

// 전역 예외 처리
@ExceptionHandler(MethodArgumentNotValidException.class)
public ResponseEntity<ErrorResponse> handleValidationException(
    MethodArgumentNotValidException ex
) {
    // 검증 오류 처리
}
```

---

## 🔧 실무에서 추가로 고려할 사항

### 1. **로깅**
```java
@Slf4j  // Lombok
@Service
public class MemberService {
    public MemberDto.Response findMember(String userId) {
        log.info("회원 조회 요청: userId={}", userId);
        // ...
        log.debug("조회 결과: {}", response);
        return response;
    }
}
```

### 2. **API 버저닝**
```java
@RequestMapping("/api/v1/members")  // 버전 명시
public class MemberController { }
```

### 3. **API 문서화 (Swagger/OpenAPI)**
```java
@Tag(name = "회원 관리", description = "회원 관련 API")
@RestController
public class MemberController {

    @Operation(summary = "회원 조회", description = "ID로 회원 정보를 조회합니다")
    @ApiResponses({
        @ApiResponse(responseCode = "200", description = "조회 성공"),
        @ApiResponse(responseCode = "404", description = "회원 없음")
    })
    @GetMapping("/{userId}")
    public ResponseEntity<MemberDto.Response> getMember(...) { }
}
```

### 4. **보안 헤더**
```java
// Security 설정에서
http.headers()
    .contentSecurityPolicy("...")
    .and()
    .xssProtection()
    .and()
    .frameOptions().deny();
```

### 5. **CORS 설정**
```java
@Configuration
public class WebConfig implements WebMvcConfigurer {
    @Override
    public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/**")
                .allowedOrigins("http://localhost:3000")
                .allowedMethods("GET", "POST", "PUT", "DELETE", "PATCH");
    }
}
```

### 6. **응답 압축**
```yaml
# application.yml
server:
  compression:
    enabled: true
    mime-types: application/json,application/xml
```

---

## 📊 종합 평가

### 현재 수준
- **아키텍처**: ★★★★★ (5/5) - 매우 좋음
- **DTO 패턴**: ★★★★★ (5/5) - 매우 좋음
- **REST API 설계**: ★★★★☆ (4/5) - 좋음
- **예외 처리**: ★★☆☆☆ (2/5) - 개선 필요
- **응답 형식**: ★★★☆☆ (3/5) - 개선 필요
- **코드 품질**: ★★★★☆ (4/5) - 좋음

### 실무 적합도
**학습용 프로젝트**: ★★★★★ (5/5)
- 핵심 개념을 잘 구현하고 있음
- Spring Boot + JPA의 기본을 충실히 따름

**소규모 실무 프로젝트**: ★★★★☆ (4/5)
- 예외 처리와 검증만 추가하면 충분히 사용 가능
- 기본 구조가 탄탄함

**중대규모 실무 프로젝트**: ★★★☆☆ (3/5)
- 전역 예외 처리, 검증, 로깅, API 문서화 필수
- 보안, 모니터링, 성능 최적화 고려 필요

---

## 🎯 우선순위별 개선 권장사항

### 1순위 (필수)
- ✅ **전역 예외 처리** (@ControllerAdvice)
- ✅ **DTO Validation** (@Valid, @NotNull 등)
- ✅ **HTTP 상태 코드 개선** (201, 204 등)

### 2순위 (권장)
- ✅ **응답 형식 통일** (생성 API 응답)
- ✅ **파일 경로 설정 외부화** (application.yml)
- ✅ **로깅 추가**

### 3순위 (선택)
- ✅ API 문서화 (Swagger)
- ✅ API 버저닝
- ✅ 검색 API 개선

---

## 결론

현재 프로젝트는 **Spring Boot + JPA 학습용으로 매우 잘 구성**되어 있습니다.

**장점**:
- ✅ 3계층 아키텍처 명확
- ✅ DTO 패턴 올바르게 적용
- ✅ RESTful API 원칙 준수
- ✅ 페이징 처리 우수
- ✅ 트랜잭션 관리 적절

**개선 포인트**:
- ⚠️ 전역 예외 처리 추가 필요
- ⚠️ DTO 검증 추가 필요
- ⚠️ HTTP 상태 코드 개선
- ⚠️ 응답 형식 통일

**실무 적용**:
현재 구조에서 **예외 처리와 검증만 추가**하면 소규모 실무 프로젝트에 바로 적용 가능한 수준입니다. 학습 목적으로는 핵심 개념을 매우 잘 구현하고 있어 훌륭합니다! 👍
