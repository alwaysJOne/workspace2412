import { Outlet } from 'react-router-dom';
import Header from './Header';
import { LayoutContainer, MainContent } from './Layout.styled';

const Layout = () => {
  return (
    <LayoutContainer>
      <Header />
      <MainContent>
        <Outlet />
      </MainContent>
    </LayoutContainer>
  );
};

export default Layout;
