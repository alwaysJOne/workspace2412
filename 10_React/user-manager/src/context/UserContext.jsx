import { createContext, useState, useContext } from 'react';

const UserContext = createContext();

// 초기 데이터
const initialUsers = [
  { id: 1, name: '홍길동', age: 25, isOnline: true },
  { id: 2, name: '김철수', age: 30, isOnline: false },
  { id: 3, name: '이영희', age: 28, isOnline: true },
];

export function UserProvider({ children }) {
  const [users, setUsers] = useState(initialUsers);

  const addUser = (newUser) => {
    const id = users.length + 1;
    setUsers([...users, { ...newUser, id }]);
  };

  const deleteUser = (userId) => {
    setUsers(users.filter(user => user.id !== userId));
  };

  const getUserById = (userId) => {
    return users.find(user => user.id === userId);
  };

  return (
    <UserContext.Provider value={{ users, addUser, deleteUser, getUserById }}>
      {children}
    </UserContext.Provider>
  );
}

export function useUsers() {
  const context = useContext(UserContext);
  if (!context) {
    throw new Error('useUsers must be used within a UserProvider');
  }
  return context;
} 