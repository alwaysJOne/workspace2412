import styled from 'styled-components';

export const Container = styled.div`
  width: 100%;
  margin: 0 auto;
  padding: 24px;
  background: #ffffff;
  max-width: 500px;
  border-radius: 12px;
  box-shadow: 0 4px 6px #e7e7e7;
`;

export const Title = styled.h1`
  font-size: 48px;
  font-weight: bold;
  margin-bottom: 24px;
`;

export const InputContainer = styled.div`
  display: flex;
  margin-bottom: 24px;
`;

export const Input = styled.input`
  flex: 1;
  padding: 12px;
  border: 1px solid #e2e2e2;
  outline: none;
  border-radius: 4px 0 0 4px;

  &:focus {
    border-color: #4b7fcc;
  }
`;

export const AddButton = styled.button`
  padding: 12px 24px;
  background: #4b7fcc;
  color: #ffffff;
  border: none;
  border-radius: 0 4px 4px 0;
  cursor: pointer;

  &:hover {
    background-color: #4b7fccdd;
  }
`;

export const TodoListContainer = styled.ul`
  list-style: none;
  padding: 0;
  margin: 0;
`;
