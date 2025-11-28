import styled from 'styled-components';

export const FormContainer = styled.div`
  display: flex;
  gap: 12px;
  margin-bottom: 24px;
`;

export const Input = styled.input`
  flex: 1;
  padding: 12px;
  border: 1px solid #e2e2e2;
  outline: none;
  border-radius: 4px;

  &:focus {
    border-color: #4b7fcc;
  }
`;

export const Select = styled.select`
  padding: 12px;
  border: 1px solid #e2e2e2;
  outline: none;
  border-radius: 4px;
  background: white;
  cursor: pointer;

  &:focus {
    border-color: #4b7fcc;
  }
`;

export const AddButton = styled.button`
  padding: 12px 24px;
  background: #4b7fcc;
  color: #ffffff;
  border: none;
  border-radius: 4px;
  cursor: pointer;

  &:hover {
    background-color: #3a63a3;
  }

  &:disabled {
    background-color: #ccc;
    cursor: not-allowed;
  }
`;
