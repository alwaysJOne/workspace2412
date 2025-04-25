import { Routes, Route, Link } from 'react-router-dom';
import styled from 'styled-components';
import UserList from './pages/UserList';
import UserDetail from './pages/UserDetail';
import UserRegistration from './pages/UserRegistration';
import NotFound from './pages/NotFound';
import { UserProvider } from './context/UserContext';
import { ThemeProvider, useTheme } from './context/ThemeContext';
import { IoLogoClosedCaptioning } from "react-icons/io";
import { BsSunFill, BsMoonFill } from "react-icons/bs";

const AppContainer = styled.div`
  height: 100vh;
  display: flex;
  flex-direction: column;
  background-color: ${props => props.theme === 'dark' ? '#1a1a1a' : '#f5f6fa'};
  color: ${props => props.theme === 'dark' ? '#ffffff' : '#000000'};
`;

const Nav = styled.nav`
  box-sizing: border-box;
  background-color: ${props => props.theme === 'dark' ? '#2c3e50' : '#03c75a'};
  padding: 16px;
  height: 80px;
  display: flex;
  justify-content: space-between;
`;

const NavContent = styled.div`
  display: flex;
  align-items: center;
`;

const NavLink = styled(Link)`
  color: white;
  font-weight: 600;
  text-decoration: none;
  margin-right: 20px;
  font-size: 20px;
  padding: 8px 16px;
  border-radius: 4px;

  &:hover{
    scale: 0.98;
    border-bottom: 2px solid white;
  }
`;

const MainContent = styled.main`
  flex: 1;
  padding: 32px;
`;

const Logo = styled(IoLogoClosedCaptioning)`
  width: 100px;
  height: 100%;
  color: white;
`

const ThemeButton = styled.button`
  background: none;
  border: none;
  color: white;
  font-size: 24px;
  cursor: pointer;
  padding: 8px;
  display: flex;
  align-items: center;
  justify-content: center;
  
  &:hover {
    opacity: 0.8;
  }
`;

function AppContent() {
  const { isDarkMode, toggleTheme } = useTheme();

  return (
    <AppContainer theme={isDarkMode ? 'dark' : 'light'}>
      <Nav theme={isDarkMode ? 'dark' : 'light'}>
        <Logo />
        <NavContent>
          <NavLink to="/">사용자 목록</NavLink>
          <NavLink to="/user">사용자 등록</NavLink>
          <ThemeButton onClick={toggleTheme}>
            {isDarkMode ? <BsSunFill /> : <BsMoonFill />}
          </ThemeButton>
        </NavContent>
      </Nav>
      <MainContent>
        <Routes>
          <Route path="/" element={<UserList />} />
          <Route path="/user" element={<UserRegistration />} />
          <Route path="/user/:id" element={<UserDetail />} />
          <Route path="*" element={<NotFound />} />
        </Routes>
      </MainContent>
    </AppContainer>
  );
}

function App() {
  return (
    <ThemeProvider>
      <UserProvider>
        <AppContent />
      </UserProvider>
    </ThemeProvider>
  );
}

export default App;
