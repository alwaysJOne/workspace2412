import { createContext, useContext, useState, useEffect } from 'react';

const TodoContext = createContext();

export const useTodos = () => {
  const context = useContext(TodoContext);
  if (!context) {
    throw new Error('useTodos must be used within TodoProvider');
  }
  return context;
};

export const TodoProvider = ({ children }) => {
  const [todos, setTodos] = useState(() => {
    const savedTodos = localStorage.getItem('todos');
    return savedTodos ? JSON.parse(savedTodos) : [];
  });

  useEffect(() => {
    localStorage.setItem('todos', JSON.stringify(todos));
  }, [todos]);

  const addTodo = (text, category = 'personal') => {
    const newTodo = {
      id: Date.now(),
      text,
      completed: false,
      category,
      createdAt: new Date().toISOString(),
    };
    setTodos(prev => [...prev, newTodo]);
    return newTodo;
  };

  const toggleTodo = (id) => {
    setTodos(prev =>
      prev.map(todo =>
        todo.id === id ? { ...todo, completed: !todo.completed } : todo
      )
    );
  };

  const deleteTodo = (id) => {
    setTodos(prev => prev.filter(todo => todo.id !== id));
  };

  const updateTodo = (id, updates) => {
    setTodos(prev =>
      prev.map(todo =>
        todo.id === id ? { ...todo, ...updates } : todo
      )
    );
  };

  const getTodoById = (id) => {
    return todos.find(todo => todo.id === parseInt(id));
  };

  const getTodosByCategory = (category) => {
    return todos.filter(todo => todo.category === category);
  };

  const getStats = () => {
    const total = todos.length;
    const completed = todos.filter(todo => todo.completed).length;
    const pending = total - completed;
    const byCategory = {
      work: todos.filter(todo => todo.category === 'work').length,
      personal: todos.filter(todo => todo.category === 'personal').length,
      study: todos.filter(todo => todo.category === 'study').length,
    };

    return { total, completed, pending, byCategory };
  };

  const value = {
    todos,
    addTodo,
    toggleTodo,
    deleteTodo,
    updateTodo,
    getTodoById,
    getTodosByCategory,
    getStats,
  };

  return <TodoContext.Provider value={value}>{children}</TodoContext.Provider>;
};
