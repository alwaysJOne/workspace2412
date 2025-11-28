import { useState, useCallback } from 'react';
import TodoItem from './TodoItem';
import {
  Container,
  Title,
  InputContainer,
  Input,
  AddButton,
  TodoListContainer
} from './TodoList.styled';

const TodoList = () => {
  const [newTodo, setNewTodo] = useState('');
  const [todos, setTodos] = useState([]);

  const addTodo = useCallback(() => {
    if (newTodo.trim() === '') return;

    const todo = {
      id: Date.now(),
      text: newTodo,
      completed: false,
    };

    setTodos(prevTodos => [...prevTodos, todo]);
    setNewTodo('');
  }, [newTodo]);

  const handleKeyDown = useCallback((ev) => {
    if (ev.key === 'Enter') {
      addTodo();
    }
  }, [addTodo]);

  const handleToggle = useCallback((id) => {
    setTodos(prevTodos =>
      prevTodos.map(todo =>
        todo.id === id ? { ...todo, completed: !todo.completed } : todo
      )
    );
  }, []);

  const handleDelete = useCallback((id) => {
    setTodos(prevTodos => prevTodos.filter(todo => todo.id !== id));
  }, []);

  return (
    <Container>
      <Title>Todo List</Title>
      <InputContainer>
        <Input
          type="text"
          value={newTodo}
          onChange={(e) => setNewTodo(e.target.value)}
          onKeyDown={handleKeyDown}
          placeholder="할일을 입력하세요."
        />
        <AddButton onClick={addTodo}>
          추가
        </AddButton>
      </InputContainer>
      <TodoListContainer>
        {todos.map(todo => (
          <TodoItem
            key={todo.id}
            todo={todo}
            onToggle={handleToggle}
            onDelete={handleDelete}
          />
        ))}
      </TodoListContainer>
    </Container>
  );
};

export default TodoList;
