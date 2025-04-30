import axios from 'axios';
import React, { useEffect, useState } from 'react';

const TodoList = () => {
  const [todos, setTodos] = useState([]);

  const getTodos = async () => {
    const res = await axios.get('http://localhost:3001/todos');
    setTodos(res.data);
  };

  useEffect(() => {
    getTodos();
  });

  return (
    <div>
      <h2>할일 목록</h2>
      <ul>
        {todos.map((todo) => (
          <li key={todo.id}>
            {todo.text} {todo.completed ? 'O' : 'X'}
          </li>
        ))}
      </ul>
    </div>
  );
};

export default TodoList;
