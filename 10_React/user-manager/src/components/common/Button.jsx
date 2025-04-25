import styled from 'styled-components';

export const Button = styled.button`
  width: max-content;
  padding: 12px;
  border: none;
  border-radius: 8px;
  font-size: 16px;
  font-weight: 800;
  cursor: pointer;
  transition: all 0.1s ease;
  text-decoration: none;
  display: flex;
  align-items: center;
  justify-content: center;
  background-color: white;
  color: #212529;
  
  &:hover {
    scale: 0.98;
  }

  &:focus{
    outline: none;
  }
`;

export const PrimaryButton = styled(Button)`
  background-color: #3498db;
  color: white;
  
  &:hover {
    background-color: #2980b9;
  }
`;

export const SecondaryButton = styled(Button)`
  background-color: #f8f9fa;
  color: #34495e;
  border: 1px solid #e0e0e0;
  
  &:hover {
    background-color: #e9ecef;
  }
`; 