import { Link } from 'react-router-dom';
import styled from 'styled-components';
import { StatusBadge } from './StatusBadge';

const Card = styled(Link)`
  background: white;
  padding: 24px;
  border-radius: 12px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
  text-decoration: none;
  color: inherit;
  transition: all 0.3s ease;
  
  &:hover {
    transform: translateY(-4px);
    box-shadow: 0 4px 8px rgba(0,0,0,0.1);
  }
`;

const Name = styled.h3`
  color: #2c3e50;
  margin-bottom: 12px;
  font-size: 18px;
`;

const Info = styled.p`
  color: #7f8c8d;
  margin: 8px 0;
  font-size: 14px;
`;

function UserCard({ user }) {
  return (
    <Card to={`/user/${user.id}`}>
      <Name>{user.name}</Name>
      <Info>나이: {user.age}세</Info>
      <Info>
        상태: <StatusBadge isOnline={user.isOnline}>
          {user.isOnline ? '온라인' : '오프라인'}
        </StatusBadge>
      </Info>
    </Card>
  );
}

export default UserCard; 