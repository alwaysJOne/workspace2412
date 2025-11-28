# React Todo List 애플리케이션 구현 과제

## 📋 과제 개요

React를 활용하여 **완전한 기능을 갖춘 Todo List 애플리케이션**을 구현하는 과제입니다. 이 과제를 통해 현대적인 React 개발 패턴과 상태 관리, 라우팅, 스타일링 기법을 학습합니다.

### 학습 목표
- ✅ Context API를 활용한 전역 상태 관리
- ✅ React Router를 통한 SPA(Single Page Application) 라우팅
- ✅ Styled-components를 사용한 CSS-in-JS 스타일링
- ✅ localStorage를 활용한 데이터 영속화
- ✅ React Hooks의 효과적인 활용 (useState, useEffect, useContext, useCallback 등)
- ✅ 컴포넌트 성능 최적화 (memo, useCallback)
- ✅ 폼 처리 및 사용자 입력 검증

### 기술 스택
| 기술 | 버전 | 용도 |
|------|------|------|
| React | 19.x | UI 라이브러리 |
| React Router | 7.x | 클라이언트 사이드 라우팅 |
| Styled-components | 6.x | CSS-in-JS 스타일링 |
| Vite | 6.x | 빌드 도구 및 개발 서버 |

---

## 🎯 필수 구현 기능

### 1. 할일 관리 (CRUD)
- ✏️ **생성(Create)**: 새로운 할일 추가 (텍스트 + 카테고리 선택)
- 📖 **조회(Read)**: 할일 목록 조회 및 개별 할일 상세 보기
- ✨ **수정(Update)**: 할일 내용, 카테고리, 완료 상태 수정
- 🗑️ **삭제(Delete)**: 할일 삭제 (확인 대화상자 포함)

### 2. 카테고리 시스템
세 가지 카테고리로 할일을 분류:
- 📊 **업무(work)** - 업무 관련 할일
- 👤 **개인(personal)** - 개인적인 할일 (기본값)
- 📚 **학습(study)** - 학습 관련 할일

### 3. 필터링 및 분류
- **전체**: 모든 할일 표시
- **진행중**: 완료되지 않은 할일만 표시
- **완료**: 완료된 할일만 표시
- **카테고리별**: 특정 카테고리의 할일만 표시

### 4. 통계 대시보드
- 전체 할일 개수
- 진행중인 할일 개수
- 완료된 할일 개수
- 카테고리별 할일 개수

### 5. 데이터 영속화
- localStorage를 사용하여 브라우저를 닫아도 데이터 유지
- 자동 저장 (상태 변경 시마다 localStorage 업데이트)

---

## 📱 페이지 구성

### 1. 홈/대시보드 페이지 (`/`)
**목적**: 할일 통계를 한눈에 확인

**구현 요구사항**:
- 전체/진행중/완료 통계 카드 표시
- 카테고리별 할일 개수 카드 표시 (클릭 시 해당 카테고리 페이지로 이동)
- 반응형 그리드 레이아웃 (CSS Grid 사용)

**예시 UI**:
```
┌─────────────────────────────────────────┐
│  전체 할일: 12  │  진행중: 5  │  완료: 7  │
├─────────────────────────────────────────┤
│  📊 업무: 4  │  👤 개인: 6  │  📚 학습: 2  │
└─────────────────────────────────────────┘
```

---

### 2. 할일 목록 페이지 (`/todos`)
**목적**: 모든 할일을 관리하는 메인 페이지

**구현 요구사항**:
- 할일 추가 폼 (텍스트 입력 + 카테고리 선택)
- 필터 버튼 (전체/진행중/완료) - 활성 필터 시각적 표시
- 각 필터의 할일 개수 표시
- 할일 목록 (클릭 시 상세 페이지로 이동)
- 체크박스로 완료 상태 토글
- 삭제 버튼
- 빈 상태 메시지 (할일이 없을 때)

**폼 검증**:
- 빈 텍스트 제출 방지
- Enter 키로 제출 가능

---

### 3. 할일 상세/수정 페이지 (`/todos/:id`)
**목적**: 개별 할일의 상세 정보 확인 및 수정

**구현 요구사항**:
- URL 파라미터로 할일 ID 받기 (`useParams` 사용)
- 할일 텍스트 수정 가능 (textarea)
- 카테고리 변경 가능 (select)
- 상태 배지 표시 (완료/진행중)
- 생성 날짜 표시 (한국어 형식)
- 저장 버튼 (수정사항 반영)
- 삭제 버튼 (확인 대화상자 후 삭제)
- 목록으로 돌아가기 버튼
- 존재하지 않는 ID인 경우 404 페이지로 리디렉션 또는 에러 메시지

**예시 UI**:
```
┌─────────────────────────────────────┐
│ [Textarea: 할일 내용]                │
│ [Select: 카테고리]                   │
│ 상태: [✅ 완료 | ⏳ 진행중]           │
│ 생성일: 2025. 11. 28. 오전 10:30:45  │
│ [💾 저장] [🗑️ 삭제] [← 목록으로]     │
└─────────────────────────────────────┘
```

---

### 4. 카테고리별 페이지 (`/category/:category`)
**목적**: 특정 카테고리의 할일만 모아서 보기

**구현 요구사항**:
- URL 파라미터로 카테고리 받기 (work/personal/study)
- 카테고리 탭 버튼 (빠른 전환)
- 해당 카테고리 할일 추가 폼 (카테고리 자동 선택)
- 카테고리별 할일 목록
- 체크박스로 완료 상태 토글
- 삭제 버튼
- 빈 상태 메시지

---

### 5. 404 에러 페이지 (`*`)
**목적**: 존재하지 않는 경로 처리

**구현 요구사항**:
- 404 에러 메시지
- 홈으로 돌아가기 링크
- 깔끔한 UI 디자인

---

## 🏗️ 프로젝트 구조

```
src/
├── components/
│   ├── Layout/
│   │   ├── Header.jsx              # 네비게이션 헤더
│   │   ├── Layout.jsx              # 레이아웃 래퍼 (Outlet 사용)
│   │   └── Layout.styled.js        # 레이아웃 스타일
│   ├── TodoForm/
│   │   ├── TodoForm.jsx            # 할일 추가 폼
│   │   └── TodoForm.styled.js      # 폼 스타일
│   ├── TodoItem.jsx                # 개별 할일 아이템
│   ├── TodoItem.styled.js          # 아이템 스타일
│   ├── TodoList.jsx                # 할일 리스트 컨테이너
│   └── TodoList.styled.js          # 리스트 스타일
├── context/
│   └── TodoContext.jsx             # 전역 상태 관리 Context
├── pages/
│   ├── Home.jsx                    # 대시보드 페이지
│   ├── Home.styled.js
│   ├── TodoListPage.jsx            # 할일 목록 페이지
│   ├── TodoListPage.styled.js
│   ├── TodoDetail.jsx              # 할일 상세 페이지
│   ├── TodoDetail.styled.js
│   ├── CategoryPage.jsx            # 카테고리별 페이지
│   ├── CategoryPage.styled.js
│   ├── NotFound.jsx                # 404 페이지
│   └── NotFound.styled.js
├── routes/
│   ├── routes.jsx                  # 라우트 정의
│   └── routePaths.js               # 라우트 상수 및 카테고리 설정
├── App.jsx                         # TodoProvider로 앱 래핑
├── App.styled.js
├── main.jsx                        # 엔트리 포인트
└── index.css                       # 글로벌 스타일
```

---

## 💾 데이터 구조

### Todo 객체
```javascript
{
  id: number,              // 고유 ID (Date.now() 사용)
  text: string,            // 할일 내용
  completed: boolean,      // 완료 상태
  category: string,        // 'work' | 'personal' | 'study'
  createdAt: string        // ISO8601 형식 타임스탬프
}
```

### Context State
```javascript
{
  todos: Array,                        // 모든 할일 배열
  addTodo(text, category),             // 할일 추가
  toggleTodo(id),                      // 완료 상태 토글
  deleteTodo(id),                      // 할일 삭제
  updateTodo(id, updates),             // 할일 수정
  getTodoById(id),                     // ID로 할일 조회
  getTodosByCategory(category),        // 카테고리별 할일 조회
  getStats()                           // 통계 데이터 반환
}
```

---

## 🛠️ 구현 가이드

### 1. Context API 구현

**TodoContext.jsx 구조**:
```javascript
import { createContext, useContext, useState, useEffect } from 'react';

const TodoContext = createContext();

export function TodoProvider({ children }) {
  // localStorage에서 초기 데이터 로드
  const [todos, setTodos] = useState(() => {
    const saved = localStorage.getItem('todos');
    return saved ? JSON.parse(saved) : [];
  });

  // todos 변경 시 localStorage 자동 저장
  useEffect(() => {
    localStorage.setItem('todos', JSON.stringify(todos));
  }, [todos]);

  // CRUD 함수들 구현
  const addTodo = (text, category = 'personal') => {
    // 구현...
  };

  const toggleTodo = (id) => {
    // 구현...
  };

  const deleteTodo = (id) => {
    // 구현...
  };

  const updateTodo = (id, updates) => {
    // 구현...
  };

  // 조회 함수들
  const getTodoById = (id) => {
    // 구현...
  };

  const getTodosByCategory = (category) => {
    // 구현...
  };

  const getStats = () => {
    // 전체/완료/진행중 개수 계산
    // 카테고리별 개수 계산
    // 구현...
  };

  return (
    <TodoContext.Provider value={{
      todos,
      addTodo,
      toggleTodo,
      deleteTodo,
      updateTodo,
      getTodoById,
      getTodosByCategory,
      getStats
    }}>
      {children}
    </TodoContext.Provider>
  );
}

export function useTodos() {
  const context = useContext(TodoContext);
  if (!context) {
    throw new Error('useTodos must be used within TodoProvider');
  }
  return context;
}
```

---

### 2. React Router 설정

**routes.jsx 구조**:
```javascript
import { createBrowserRouter } from 'react-router-dom';
import Layout from '../components/Layout/Layout';
import Home from '../pages/Home';
import TodoListPage from '../pages/TodoListPage';
import TodoDetail from '../pages/TodoDetail';
import CategoryPage from '../pages/CategoryPage';
import NotFound from '../pages/NotFound';

export const router = createBrowserRouter([
  {
    path: '/',
    element: <Layout />,
    children: [
      { index: true, element: <Home /> },
      { path: 'todos', element: <TodoListPage /> },
      { path: 'todos/:id', element: <TodoDetail /> },
      { path: 'category/:category', element: <CategoryPage /> },
      { path: '*', element: <NotFound /> }
    ]
  }
]);
```

**routePaths.js** (상수 관리):
```javascript
export const ROUTES = {
  HOME: '/',
  TODOS: '/todos',
  TODO_DETAIL: (id) => `/todos/${id}`,
  CATEGORY: (category) => `/category/${category}`,
};

export const CATEGORIES = {
  WORK: 'work',
  PERSONAL: 'personal',
  STUDY: 'study'
};

export const CATEGORY_NAMES = {
  work: '업무',
  personal: '개인',
  study: '학습'
};
```

---

### 3. Styled-components 사용법

**기본 패턴**:
```javascript
import styled from 'styled-components';

export const Container = styled.div`
  max-width: 800px;
  margin: 0 auto;
  padding: 20px;
`;

export const Button = styled.button`
  padding: 8px 16px;
  background: ${props => props.active ? '#4b7fcc' : '#ffffff'};
  color: ${props => props.active ? '#ffffff' : '#333'};
  border: 1px solid #ddd;
  border-radius: 4px;
  cursor: pointer;
  transition: all 0.2s;

  &:hover {
    background: ${props => props.active ? '#3a63a3' : '#f5f5f5'};
  }
`;
```

**권장 색상 팔레트**:
```javascript
// Primary
const PRIMARY_BLUE = '#4b7fcc';
const PRIMARY_BLUE_HOVER = '#3a63a3';

// Backgrounds
const BG_GRAY = '#f3f2f2';
const WHITE = '#ffffff';

// Text
const TEXT_PRIMARY = '#333';
const TEXT_SECONDARY = '#666';
const TEXT_TERTIARY = '#999';

// Borders
const BORDER_COLOR = '#ddd';
```

---

### 4. 성능 최적화

**TodoItem 메모이제이션**:
```javascript
import { memo } from 'react';

const TodoItem = memo(({ todo, onToggle, onDelete }) => {
  // 컴포넌트 구현...
});

export default TodoItem;
```

**useCallback 활용**:
```javascript
const handleToggle = useCallback((id) => {
  toggleTodo(id);
}, [toggleTodo]);

const handleDelete = useCallback((id) => {
  if (window.confirm('정말 삭제하시겠습니까?')) {
    deleteTodo(id);
  }
}, [deleteTodo]);
```

---

## 🎨 UI/UX 요구사항

### 1. 반응형 디자인
- 모바일, 태블릿, 데스크톱 모두 지원
- 최대 너비 800px (가독성을 위해)
- CSS Grid 및 Flexbox 활용

### 2. 사용자 피드백
- 버튼 hover 효과
- 활성 상태 시각적 표시
- 로딩 상태 (선택사항)
- 에러 메시지 표시

### 3. 접근성
- 의미있는 HTML 태그 사용
- 키보드 네비게이션 지원 (Enter 키 제출 등)
- 명확한 레이블 및 placeholder

### 4. 일관성
- 일관된 색상 체계
- 일관된 여백 및 간격
- 일관된 버튼 스타일

---

## ✅ 평가 기준

### 1. 기능 완성도 (40점)
- [ ] 할일 CRUD 모두 정상 작동 (10점)
- [ ] 카테고리 시스템 구현 (5점)
- [ ] 필터링 기능 구현 (5점)
- [ ] 라우팅 및 네비게이션 (10점)
- [ ] localStorage 데이터 영속화 (5점)
- [ ] 통계 대시보드 (5점)

### 2. 코드 품질 (30점)
- [ ] Context API 올바른 사용 (10점)
- [ ] 컴포넌트 구조 및 재사용성 (10점)
- [ ] Hooks의 적절한 활용 (5점)
- [ ] 코드 가독성 및 주석 (5점)

### 3. UI/UX (20점)
- [ ] Styled-components 사용 (5점)
- [ ] 일관된 디자인 (5점)
- [ ] 반응형 레이아웃 (5점)
- [ ] 사용자 경험 (피드백, 에러 처리 등) (5점)

### 4. 성능 최적화 (10점)
- [ ] memo/useCallback 활용 (5점)
- [ ] 불필요한 리렌더링 방지 (5점)

### 5. 추가 기능 (보너스 10점)
- [ ] 아래 선택 구현 사항 중 구현한 기능

---

## 🌟 선택 구현 사항 (보너스)

다음 기능 중 원하는 것을 선택하여 구현하면 추가 점수를 받을 수 있습니다:

1. **검색 기능** (3점)
   - 할일 텍스트로 검색
   - 실시간 검색 결과 표시

2. **정렬 기능** (3점)
   - 생성일순, 완료 상태별, 카테고리별 정렬
   - 오름차순/내림차순 토글

3. **수정 기능** (2점)
   - 할일 목록에서 바로 인라인 편집

4. **마감일 기능** (4점)
   - 할일에 마감일 설정
   - 마감일 임박/지남 표시
   - 마감일순 정렬

5. **우선순위 기능** (3점)
   - 높음/보통/낮음 우선순위 설정
   - 우선순위별 색상 표시

6. **일괄 작업** (3점)
   - 전체 선택/해제
   - 완료된 항목 일괄 삭제

7. **애니메이션** (2점)
   - 할일 추가/삭제 시 부드러운 애니메이션
   - 페이지 전환 애니메이션

8. **다크 모드** (4점)
   - 라이트/다크 테마 전환
   - 사용자 설정 localStorage 저장

9. **데이터 내보내기/가져오기** (5점)
   - JSON 형식으로 데이터 내보내기
   - 파일에서 데이터 가져오기

10. **통계 차트** (5점)
    - 카테고리별/상태별 차트 시각화
    - Chart.js 또는 Recharts 사용

---

## 🚀 시작하기

### 1. 프로젝트 생성
```bash
npm create vite@latest todo-list -- --template react
cd todo-list
npm install
```

### 2. 필수 패키지 설치
```bash
npm install react-router-dom styled-components
```

### 3. 개발 서버 실행
```bash
npm run dev
```

### 4. 프로젝트 구조 생성
위의 폴더 구조를 참고하여 필요한 폴더와 파일을 생성합니다.

---

## 📚 참고 자료

### 공식 문서
- [React 공식 문서](https://react.dev/)
- [React Router 공식 문서](https://reactrouter.com/)
- [Styled-components 공식 문서](https://styled-components.com/)
- [Vite 공식 문서](https://vitejs.dev/)

### 학습 자료
- React Hooks 가이드: https://react.dev/reference/react
- Context API 튜토리얼: https://react.dev/learn/passing-data-deeply-with-context
- localStorage API: https://developer.mozilla.org/ko/docs/Web/API/Window/localStorage

---

## 💡 구현 팁

### 1. 단계별 접근
1. **1단계**: 프로젝트 설정 및 기본 구조 생성
2. **2단계**: TodoContext 구현 (CRUD 기능)
3. **3단계**: 기본 컴포넌트 구현 (TodoItem, TodoList, TodoForm)
4. **4단계**: 라우팅 설정 및 페이지 구현
5. **5단계**: Styled-components로 스타일링
6. **6단계**: 성능 최적화 및 버그 수정
7. **7단계**: (선택) 추가 기능 구현

### 2. 디버깅
- React DevTools 확장 프로그램 사용
- console.log로 상태 변화 확인
- localStorage를 브라우저 개발자 도구에서 확인

### 3. 코드 작성 습관
- 작은 단위로 커밋하기
- 컴포넌트는 하나의 책임만 갖도록 분리
- 반복되는 코드는 함수나 컴포넌트로 추출
- PropTypes 또는 TypeScript로 타입 검증 (선택사항)

### 4. 흔한 실수 방지
- ❌ Context를 너무 많이 만들지 않기 (하나의 TodoContext로 충분)
- ❌ 불필요한 상태 복제하지 않기 (props로 충분하면 state 사용 안 함)
- ❌ useEffect 의존성 배열 누락하지 않기
- ❌ key prop 누락하지 않기 (리스트 렌더링 시)

---

## 📝 제출 방법

### 제출물
1. **소스 코드**: GitHub 저장소 링크 또는 ZIP 파일
2. **README.md**: 프로젝트 소개, 실행 방법, 구현 기능 설명
3. **실행 화면**: 주요 기능 스크린샷 또는 데모 영상 (선택사항)

### README.md 포함 사항
- 프로젝트 개요
- 기술 스택
- 실행 방법
- 구현한 필수 기능 체크리스트
- 구현한 선택 기능 (있다면)
- 어려웠던 점 및 해결 방법
- 느낀 점

### 제출 기한
- 과제 공지일로부터 **2주**

---

## ❓ FAQ

**Q: TypeScript를 사용해도 되나요?**
A: 네, TypeScript 사용을 권장합니다. 보너스 점수가 부여될 수 있습니다.

**Q: CSS Module이나 Tailwind CSS를 사용해도 되나요?**
A: 이 과제는 Styled-components 사용이 필수입니다. 다른 스타일링 방법은 인정되지 않습니다.

**Q: Redux를 사용해도 되나요?**
A: 이 과제는 Context API 사용이 필수입니다. Redux나 다른 상태 관리 라이브러리는 사용하지 마세요.

**Q: UI 라이브러리(Material-UI, Ant Design 등)를 사용해도 되나요?**
A: 아니요, 모든 컴포넌트와 스타일을 직접 구현해야 합니다.

**Q: 백엔드 API를 만들어도 되나요?**
A: 이 과제는 프론트엔드 중심입니다. localStorage로 충분하며, 백엔드는 평가하지 않습니다.

**Q: 테스트 코드를 작성해야 하나요?**
A: 필수는 아니지만, Jest/React Testing Library를 사용한 테스트 코드 작성 시 보너스 점수가 부여될 수 있습니다.

---

## 🎓 학습 체크리스트

과제를 시작하기 전에 다음 개념을 이해하고 있는지 확인하세요:

- [ ] React 컴포넌트 기본 (함수형 컴포넌트)
- [ ] JSX 문법
- [ ] Props와 State
- [ ] useState Hook
- [ ] useEffect Hook
- [ ] useContext Hook
- [ ] 배열 메서드 (map, filter, find 등)
- [ ] ES6+ 문법 (구조 분해, spread 연산자, 화살표 함수 등)
- [ ] localStorage API
- [ ] 이벤트 핸들링
- [ ] 조건부 렌더링

---

## 🏆 우수 과제 기준

다음 항목을 모두 만족하면 우수 과제로 선정됩니다:

1. ✅ 모든 필수 기능이 완벽하게 작동
2. ✅ 깔끔하고 일관된 UI/UX
3. ✅ 코드가 체계적이고 가독성이 높음
4. ✅ 성능 최적화가 적절히 적용됨
5. ✅ 2개 이상의 선택 기능 구현
6. ✅ 상세한 README.md 작성
7. ✅ 버그가 없고 안정적으로 동작

---

## 📞 문의

과제 관련 질문은 다음 방법으로 문의해주세요:
- 이메일: [담당자 이메일]
- 슬랙/디스코드: [채널명]
- 오피스 아워: [시간 및 장소]

**행운을 빕니다! 🚀**
