import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import UseStateTest from './components/useState/UseStateTest'
import SignUp from './components/useState/SignUp'
import LandingPage from './components/useState/LandingPage'
import UseRefTest from './components/useRef/UseRefTest'
import UseRefScroll from './components/useRef/UseRefScroll'

function App() {
  return (
    <>
      {/* <UseStateTest /> */}
      {/* <SignUp />  */}
      {/* <LandingPage /> */}
      {/* <UseRefTest /> */}
      <UseRefScroll />
    </>
  )
}

export default App
