import { useEffect } from 'react';
import { useParams, useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { useUsers } from '../context/UserContext';
import { StatusBadge  } from '../components/user/StatusBadge';

const Container = styled.div`
  max-width: 600px;
  margin: 0 auto;
  background: white;
  padding: 32px;
  border-radius: 8px;
  box-shadow: 0 2px 4px rgba(0,0,0,0.1);
`;

const Title = styled.h2`
  color: #2c3e50;
  margin-bottom: 24px;
`;

const InfoGroup = styled.div`
  margin-bottom: 16px;
`;

const Label = styled.span`
  display: inline-block;
  width: 100px;
  color: #7f8c8d;
  font-weight: bold;
`;

const Value = styled.span`
  color: #2c3e50;
`;

const ButtonGroup = styled.div`
  display: flex;
  gap: 16px;
  margin-top: 32px;
`;

const Button = styled.button`
  padding: 8px 16px;
  border: none;
  border-radius: 4px;
  font-size: 16px;
  cursor: pointer;
  transition: background-color 0.3s;
  
  &:hover {
    opacity: 0.9;
  }
`;

const BackButton = styled(Button)`
  background-color: #3498db;
  color: white;
`;

const DeleteButton = styled(Button)`
  background-color: #e74c3c;
  color: white;
`;

function UserDetail() {
  const { id } = useParams();
  const navigate = useNavigate();
  const { getUserById, deleteUser } = useUsers();
  const user = getUserById(parseInt(id));

  useEffect(() => {
    if (!user) {
      navigate('/');
    }
  }, [user, navigate]);

  const handleDelete = () => {
    if (window.confirm('정말로 이 사용자를 삭제하시겠습니까?')) {
      deleteUser(parseInt(id));
      navigate('/');
    }
  };

  if (!user) {
    return null;
  }

  return (
    <Container>
      <Title>사용자 상세 정보</Title>
      
      <InfoGroup>
        <Label>이름:</Label>
        <Value>{user.name}</Value>
      </InfoGroup>
      
      <InfoGroup>
        <Label>나이:</Label>
        <Value>{user.age}세</Value>
      </InfoGroup>
      
      <InfoGroup>
        <Label>상태:</Label>
        <StatusBadge isOnline={user.isOnline}>
          {user.isOnline ? '온라인' : '오프라인'}
        </StatusBadge>
      </InfoGroup>
      
      <ButtonGroup>
        <BackButton onClick={() => navigate('/')}>목록으로</BackButton>
        <DeleteButton onClick={handleDelete}>삭제</DeleteButton>
      </ButtonGroup>
    </Container>
  );
}

export default UserDetail; 