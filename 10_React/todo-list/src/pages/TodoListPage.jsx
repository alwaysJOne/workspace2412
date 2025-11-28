import { useState } from 'react';
import { useTodos } from '../context/TodoContext';
import TodoForm from '../components/TodoForm/TodoForm';
import TodoItem from '../components/TodoItem';
import {
  Container,
  Title,
  FilterButtons,
  FilterButton,
  TodoListContainer,
  EmptyMessage
} from './TodoListPage.styled';

const TodoListPage = () => {
  const { todos, addTodo, toggleTodo, deleteTodo } = useTodos();
  const [filter, setFilter] = useState('all');

  const filteredTodos = todos.filter(todo => {
    if (filter === 'completed') return todo.completed;
    if (filter === 'pending') return !todo.completed;
    return true;
  });

  return (
    <Container>
      <Title>할일 목록</Title>

      <TodoForm onAdd={addTodo} />

      <FilterButtons>
        <FilterButton active={filter === 'all'} onClick={() => setFilter('all')}>
          전체 ({todos.length})
        </FilterButton>
        <FilterButton active={filter === 'pending'} onClick={() => setFilter('pending')}>
          미완료 ({todos.filter(t => !t.completed).length})
        </FilterButton>
        <FilterButton active={filter === 'completed'} onClick={() => setFilter('completed')}>
          완료 ({todos.filter(t => t.completed).length})
        </FilterButton>
      </FilterButtons>

      <TodoListContainer>
        {filteredTodos.length === 0 ? (
          <EmptyMessage>할일이 없습니다.</EmptyMessage>
        ) : (
          filteredTodos.map(todo => (
            <TodoItem
              key={todo.id}
              todo={todo}
              onToggle={toggleTodo}
              onDelete={deleteTodo}
            />
          ))
        )}
      </TodoListContainer>
    </Container>
  );
};

export default TodoListPage;
