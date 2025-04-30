import { useState } from 'react'
import './App.css'
import IconButtons from './components/IconButtons'
import GlobalStyle from './GlobalStyle'
import { ThemeProvider } from 'styled-components'
import { darkTheme, lightTheme } from './themes'
import ThemeBox from './components/ThemeBox'

function App() {
  const [isDark, setIsDark] = useState(false);
  const toggleTheme = () => setIsDark(!isDark);

  return (
    <>
      {/* <IconButtons /> */}
      <ThemeProvider theme={isDark ? darkTheme : lightTheme}>
        <GlobalStyle />
        <ThemeBox onToggleTheme={toggleTheme}/>
      </ThemeProvider>
    </>
  )
}

export default App
