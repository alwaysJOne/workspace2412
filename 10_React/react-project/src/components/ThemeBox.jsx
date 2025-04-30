import React from 'react';
import styled from 'styled-components';
import { FaCloudMoonRain, FaSun } from 'react-icons/fa';

const ThemeBox = ({ onToggleTheme }) => {
  return (
    <Wrapper>
      <h2>테마 테스트 박스</h2>
      <p>현재 테마에 따라 배경/글자 색상을 변경해보자</p>
      <ToggleButton onClick={onToggleTheme}>
        <FaSun /> / <FaCloudMoonRain /> 테마 토글
      </ToggleButton>
    </Wrapper>
  );
};

export default ThemeBox;

const Wrapper = styled.div`
  padding: 40px;
  text-align: center;
`;

const ToggleButton = styled.button`
  display: flex;
  align-items: center;
  gap: 12px;
  padding: 10px 14px;
  background: ${({ theme }) => theme.primary};
  color: white;
  font-size: 18px;
  border: none;
  border-radius: 8px;
  transition: 0.2s;

  &:hover {
    opacity: 0.9;
  }
`;
