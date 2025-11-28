import { useState, useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import { useTodos } from '../context/TodoContext';
import { ROUTES, CATEGORIES, CATEGORY_NAMES } from '../routes/routePaths';
import {
  Container,
  Header,
  Title,
  BackButton,
  DetailSection,
  Label,
  Input,
  Select,
  StatusBadge,
  ButtonGroup,
  SaveButton,
  DeleteButton,
  NotFound
} from './TodoDetail.styled';

const TodoDetail = () => {
  const { id } = useParams();
  const navigate = useNavigate();
  const { getTodoById, updateTodo, deleteTodo } = useTodos();

  const todo = getTodoById(id);

  const [text, setText] = useState('');
  const [category, setCategory] = useState(CATEGORIES.PERSONAL);

  useEffect(() => {
    if (todo) {
      setText(todo.text);
      setCategory(todo.category);
    }
  }, [todo]);

  if (!todo) {
    return (
      <Container>
        <NotFound>할일을 찾을 수 없습니다.</NotFound>
        <BackButton onClick={() => navigate(ROUTES.TODOS)}>목록으로 돌아가기</BackButton>
      </Container>
    );
  }

  const handleSave = () => {
    updateTodo(todo.id, { text, category });
    navigate(ROUTES.TODOS);
  };

  const handleDelete = () => {
    if (window.confirm('정말 삭제하시겠습니까?')) {
      deleteTodo(todo.id);
      navigate(ROUTES.TODOS);
    }
  };

  return (
    <Container>
      <Header>
        <Title>할일 상세</Title>
        <BackButton onClick={() => navigate(ROUTES.TODOS)}>← 목록</BackButton>
      </Header>

      <DetailSection>
        <Label>상태</Label>
        <StatusBadge completed={todo.completed}>
          {todo.completed ? '완료' : '미완료'}
        </StatusBadge>
      </DetailSection>

      <DetailSection>
        <Label>할일 내용</Label>
        <Input
          type="text"
          value={text}
          onChange={(e) => setText(e.target.value)}
        />
      </DetailSection>

      <DetailSection>
        <Label>카테고리</Label>
        <Select value={category} onChange={(e) => setCategory(e.target.value)}>
          <option value={CATEGORIES.WORK}>{CATEGORY_NAMES[CATEGORIES.WORK]}</option>
          <option value={CATEGORIES.PERSONAL}>{CATEGORY_NAMES[CATEGORIES.PERSONAL]}</option>
          <option value={CATEGORIES.STUDY}>{CATEGORY_NAMES[CATEGORIES.STUDY]}</option>
        </Select>
      </DetailSection>

      <DetailSection>
        <Label>생성일</Label>
        <div>{new Date(todo.createdAt).toLocaleString('ko-KR')}</div>
      </DetailSection>

      <ButtonGroup>
        <SaveButton onClick={handleSave}>저장</SaveButton>
        <DeleteButton onClick={handleDelete}>삭제</DeleteButton>
      </ButtonGroup>
    </Container>
  );
};

export default TodoDetail;
