import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { useUsers } from '../context/UserContext';
import UserCard from '../components/user/UserCard';
import SearchBar from '../components/user/SearchBar';
import { Button } from '../components/common/Button';
import { useTheme } from '../context/ThemeContext';

const Container = styled.div`
  max-width: 1200px;
  margin: 0 auto;
`;

const Header = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 32px;
`;

const Title = styled.h1`
  color: ${props => props.theme === 'dark' ? '#ffffff' : '#000000'};
  font-size: 28px;
`;

const UserGrid = styled.div`
  display: grid;
  grid-template-columns: repeat(auto-fill, minmax(300px, 1fr));
  gap: 24px;
`;

function UserList() {
  const { isDarkMode } = useTheme();
  const navigate = useNavigate();
  const { users } = useUsers();
  const [searchTerm, setSearchTerm] = useState('');

  const filteredUsers = users.filter(user =>
    user.name.toLowerCase().includes(searchTerm.toLowerCase())
  );

  return (
    <Container>
      <Header>
        <Title theme={isDarkMode ? 'dark' : 'light'}>사용자 목록</Title>
        <Button onClick={() => navigate("/user")}>사용자 등록</Button>
      </Header>
      
      <SearchBar
        value={searchTerm}
        onChange={(e) => setSearchTerm(e.target.value)}
      />
      
      <UserGrid>
        {filteredUsers.map(user => (
          <UserCard key={user.id} user={user} />
        ))}
      </UserGrid>
    </Container>
  );
}

export default UserList; 