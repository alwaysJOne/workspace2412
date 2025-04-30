import { useState } from 'react';
import './App.css';
import IconButtons from './components/IconButtons';
import GlobalStyle from './GlobalStyle';
import { ThemeProvider } from 'styled-components';
import { darkTheme, lightTheme } from './themes';
import ThemeBox from './components/ThemeBox';
import { toast, ToastContainer } from 'react-toastify';
import { performToast } from './utils/performToast';
import SimpleForm from './components/SimpleForm';
import LoaderDemo from './components/LoaderDemo';
import TodoList from './components/TodoList';

performToast({ msg: '요청에 성공하였습니다.1', type: 'success' });

function App() {
  const [isDark, setIsDark] = useState(false);
  const toggleTheme = () => setIsDark(!isDark);

  const apiUrl = import.meta.env.VITE_API_URL;
  console.log(apiUrl);

  return (
    <>
      {/* <IconButtons /> */}
      {/* <ThemeProvider theme={isDark ? darkTheme : lightTheme}>
        <GlobalStyle />
        <ThemeBox onToggleTheme={toggleTheme} />
      </ThemeProvider> */}
      {/* <ToastContainer /> */}
      {/* <SimpleForm /> */}
      {/* <LoaderDemo /> */}
      <TodoList />
    </>
  );
}

export default App;
