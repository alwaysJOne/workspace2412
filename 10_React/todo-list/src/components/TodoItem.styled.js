import styled from 'styled-components';
import { Link } from 'react-router-dom';

export const TodoItemContainer = styled.li`
  display: flex;
  align-items: center;
  justify-content: space-between;
  padding: 6px 12px;
  border: 1px solid #e2e2e2c8;
  margin-bottom: 12px;
  border-radius: 6px;
`;

export const TodoContent = styled.div`
  display: flex;
  align-items: center;
  gap: 12px;
  flex: 1;
`;

export const TodoText = styled(Link)`
  text-decoration: ${props => props.completed ? 'line-through' : 'none'};
  color: ${props => props.completed ? '#999' : '#118534'};
  cursor: pointer;
  transition: color 0.2s;

  &:hover {
    color: #4b7fcc;
  }
`;

export const Checkbox = styled.input`
  width: 18px;
  height: 18px;
  cursor: pointer;
`;

export const ButtonGroup = styled.div`
  display: flex;
  gap: 8px;
`;

export const ViewButton = styled(Link)`
  color: #4b7fcc;
  background: none;
  border: none;
  cursor: pointer;
  outline: none;
  padding: 4px 8px;
  text-decoration: none;
  font-size: 14px;

  &:hover {
    color: #3a63a3;
    text-decoration: underline;
  }
`;

export const DeleteButton = styled.button`
  color: #d62727;
  background: none;
  border: none;
  cursor: pointer;
  outline: none;
  padding: 4px 8px;

  &:hover {
    color: #ac2020;
  }
`;
