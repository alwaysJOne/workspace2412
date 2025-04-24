import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import UseStateTest from './components/useState/UseStateTest'
import SignUp from './components/useState/SignUp'
import LandingPage from './components/useState/LandingPage'
import UseRefTest from './components/useRef/UseRefTest'
import UseRefScroll from './components/useRef/UseRefScroll'
import UseMemoTest from './components/useMemo/UseMemoTest'
import UseCallbackTest from './components/useCallback/UseCallbackTest'
import EffectView from './components/useEffect/EffectView'
import BlackOrWhite from './components/useContext/BlackOrWhite'
import MyForm from './components/customHook/MyForm'
import ToggleBox from './components/customHook/ToggleBox'
import { UserProvider } from './components/useContext/UserContext'
import Header from './components/useContext/Header'

function App() {
  return (
    <>
      {/* <UseStateTest /> */}
      {/* <SignUp />  */}
      {/* <LandingPage /> */}
      {/* <UseRefTest /> */}
      {/* <UseRefScroll /> */}
      {/* <UseMemoTest /> */}
      {/* <UseCallbackTest /> */}
      {/* <EffectView /> */}
      {/* <BlackOrWhite /> */}
      {/* <MyForm /> */}
      {/* <ToggleBox /> */}
      <UserProvider>
        <Header />
      </UserProvider>
    </>
  )
}

export default App
