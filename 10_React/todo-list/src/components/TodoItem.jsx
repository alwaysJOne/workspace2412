import { memo } from 'react';
import {
  TodoItemContainer,
  TodoContent,
  TodoText,
  Checkbox,
  ButtonGroup,
  ViewButton,
  DeleteButton
} from './TodoItem.styled';

const TodoItem = ({ todo, onToggle, onDelete }) => {
  const handleToggle = () => onToggle(todo.id);
  const handleDelete = () => onDelete(todo.id);

  return (
    <TodoItemContainer>
      <TodoContent>
        <Checkbox
          type="checkbox"
          checked={todo.completed}
          onChange={handleToggle}
        />
        <TodoText to={`/todos/${todo.id}`} completed={todo.completed ? 'true' : undefined}>
          {todo.text}
        </TodoText>
      </TodoContent>
      <ButtonGroup>
        <ViewButton to={`/todos/${todo.id}`}>보기</ViewButton>
        <DeleteButton onClick={handleDelete}>삭제</DeleteButton>
      </ButtonGroup>
    </TodoItemContainer>
  );
};

export default memo(TodoItem);