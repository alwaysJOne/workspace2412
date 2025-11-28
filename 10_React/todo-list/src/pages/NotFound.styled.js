import styled from 'styled-components';
import { Link } from 'react-router-dom';

export const Container = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 60vh;
  text-align: center;
`;

export const ErrorCode = styled.h1`
  font-size: 120px;
  font-weight: bold;
  color: #4b7fcc;
  margin: 0;
  line-height: 1;
`;

export const Title = styled.h2`
  font-size: 32px;
  color: #333;
  margin: 24px 0 16px;
`;

export const Message = styled.p`
  font-size: 18px;
  color: #666;
  margin-bottom: 40px;
`;

export const HomeButton = styled(Link)`
  display: inline-block;
  padding: 12px 32px;
  background: #4b7fcc;
  color: white;
  text-decoration: none;
  border-radius: 4px;
  font-size: 16px;
  transition: background 0.2s;

  &:hover {
    background: #3a63a3;
  }
`;
