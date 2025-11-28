import { useLocation } from 'react-router-dom';
import { HeaderContainer, Nav, Logo, NavLinks, NavLink } from './Layout.styled';
import { ROUTES } from '../../routes/routePaths';

const Header = () => {
  const location = useLocation();

  const isActive = (path) => {
    return location.pathname === path ? 'active' : '';
  };

  return (
    <HeaderContainer>
      <Nav>
        <Logo to={ROUTES.HOME}>Todo App</Logo>
        <NavLinks>
          <NavLink to={ROUTES.HOME} className={isActive(ROUTES.HOME)}>
            홈
          </NavLink>
          <NavLink to={ROUTES.TODOS} className={isActive(ROUTES.TODOS)}>
            할일 목록
          </NavLink>
          <NavLink to={ROUTES.CATEGORY_WORK} className={location.pathname.startsWith('/category') ? 'active' : ''}>
            카테고리
          </NavLink>
        </NavLinks>
      </Nav>
    </HeaderContainer>
  );
};

export default Header;
