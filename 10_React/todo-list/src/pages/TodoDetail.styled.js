import styled from 'styled-components';

export const Container = styled.div`
  max-width: 600px;
  margin: 0 auto;
  background: #ffffff;
  padding: 32px;
  border-radius: 12px;
  box-shadow: 0 2px 8px rgba(0, 0, 0, 0.1);
`;

export const Header = styled.div`
  display: flex;
  justify-content: space-between;
  align-items: center;
  margin-bottom: 24px;
`;

export const Title = styled.h1`
  font-size: 32px;
  font-weight: bold;
  color: #333;
`;

export const BackButton = styled.button`
  padding: 8px 16px;
  background: #f0f0f0;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  color: #333;

  &:hover {
    background: #e0e0e0;
  }
`;

export const DetailSection = styled.div`
  margin-bottom: 24px;
`;

export const Label = styled.label`
  display: block;
  font-weight: 500;
  margin-bottom: 8px;
  color: #555;
`;

export const Input = styled.input`
  width: 100%;
  padding: 12px;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  font-size: 16px;

  &:focus {
    outline: none;
    border-color: #4b7fcc;
  }
`;

export const Select = styled.select`
  width: 100%;
  padding: 12px;
  border: 1px solid #e2e2e2;
  border-radius: 4px;
  font-size: 16px;
  background: white;

  &:focus {
    outline: none;
    border-color: #4b7fcc;
  }
`;

export const StatusBadge = styled.span`
  display: inline-block;
  padding: 4px 12px;
  border-radius: 12px;
  font-size: 14px;
  background: ${props => props.completed ? '#28a745' : '#ffc107'};
  color: white;
`;

export const ButtonGroup = styled.div`
  display: flex;
  gap: 12px;
  margin-top: 24px;
`;

export const SaveButton = styled.button`
  flex: 1;
  padding: 12px;
  background: #4b7fcc;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;

  &:hover {
    background: #3a63a3;
  }
`;

export const DeleteButton = styled.button`
  padding: 12px 24px;
  background: #dc3545;
  color: white;
  border: none;
  border-radius: 4px;
  cursor: pointer;
  font-size: 16px;

  &:hover {
    background: #c82333;
  }
`;

export const NotFound = styled.div`
  text-align: center;
  padding: 40px;
  color: #999;
`;
