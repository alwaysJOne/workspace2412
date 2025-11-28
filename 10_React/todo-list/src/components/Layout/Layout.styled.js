import styled from 'styled-components';
import { Link } from 'react-router-dom';

export const LayoutContainer = styled.div`
  min-height: 100vh;
  background: #f3f2f2;
`;

export const HeaderContainer = styled.header`
  background: #ffffff;
  box-shadow: 0 2px 4px rgba(0, 0, 0, 0.1);
  padding: 0 24px;
`;

export const Nav = styled.nav`
  max-width: 1200px;
  margin: 0 auto;
  display: flex;
  align-items: center;
  justify-content: space-between;
  height: 64px;
`;

export const Logo = styled(Link)`
  font-size: 24px;
  font-weight: bold;
  color: #4b7fcc;
  text-decoration: none;

  &:hover {
    color: #3a63a3;
  }
`;

export const NavLinks = styled.div`
  display: flex;
  gap: 24px;
  align-items: center;
`;

export const NavLink = styled(Link)`
  color: #333;
  text-decoration: none;
  font-size: 16px;
  padding: 8px 12px;
  border-radius: 4px;
  transition: all 0.2s;

  &:hover {
    background: #f0f0f0;
    color: #4b7fcc;
  }

  &.active {
    background: #4b7fcc;
    color: #ffffff;
  }
`;

export const MainContent = styled.main`
  max-width: 1200px;
  margin: 0 auto;
  padding: 40px 24px;
`;
