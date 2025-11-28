import styled from 'styled-components';
import { Link } from 'react-router-dom';

export const Container = styled.div`
  max-width: 800px;
  margin: 0 auto;
`;

export const Header = styled.div`
  margin-bottom: 24px;
`;

export const Title = styled.h1`
  font-size: 48px;
  font-weight: bold;
  margin-bottom: 12px;
`;

export const CategoryTabs = styled.div`
  display: flex;
  gap: 12px;
  margin-bottom: 24px;
`;

export const CategoryTab = styled(Link)`
  padding: 8px 16px;
  border: 1px solid #e2e2e2;
  background: ${props => props.active ? '#4b7fcc' : '#ffffff'};
  color: ${props => props.active ? '#ffffff' : '#333'};
  border-radius: 4px;
  text-decoration: none;
  transition: all 0.2s;

  &:hover {
    background: ${props => props.active ? '#3a63a3' : '#f0f0f0'};
  }
`;

export const TodoListContainer = styled.ul`
  list-style: none;
  padding: 0;
  margin: 0;
`;

export const EmptyMessage = styled.div`
  text-align: center;
  padding: 40px;
  color: #999;
  font-size: 16px;
`;
