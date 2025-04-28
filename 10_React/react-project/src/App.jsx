import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import styled from 'styled-components'
import CounterDisplay from './components/CounterDisplay'
import CounterControls from './components/CounterControls'
import TodoList from './components/TodoList'

const AppContainer = styled.div`
  display: flex;
  flex-direction: column;
  align-items: center;
  justify-content: center;
  min-height: 100vh;
  width: 100vw;
  padding: 24px;
  text-align: center;
  transition: all 0.3s;
`

const Section = styled.section`
  width: 100%;
  max-width: 800px;
  margin: 0 auto;
  padding: 18px;
  border-radius: 8px;
  margin-bottom: 20px;
`

function App() {
  return (
    <AppContainer>
      <Section>
        <h2>Zustand 전역 상태 관리</h2>
        <CounterDisplay />
        <CounterControls />
      </Section>
      <Section>
        <h2>zustand TodoList</h2>
        <TodoList />
      </Section>
    </AppContainer>
  )
}

export default App
