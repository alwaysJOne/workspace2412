// 라우트 경로 상수 관리
export const ROUTES = {
  HOME: '/',
  TODOS: '/todos',
  TODO_DETAIL: (id) => `/todos/${id}`,
  CATEGORY: (category) => `/category/${category}`,

  // 특정 카테고리 경로
  CATEGORY_WORK: '/category/work',
  CATEGORY_PERSONAL: '/category/personal',
  CATEGORY_STUDY: '/category/study',

  // 404
  NOT_FOUND: '*',
};

// 카테고리 목록
export const CATEGORIES = {
  WORK: 'work',
  PERSONAL: 'personal',
  STUDY: 'study',
};

// 카테고리 이름
export const CATEGORY_NAMES = {
  [CATEGORIES.WORK]: '업무',
  [CATEGORIES.PERSONAL]: '개인',
  [CATEGORIES.STUDY]: '학습',
};
