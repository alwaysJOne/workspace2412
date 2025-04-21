import { useState } from 'react'
import reactLogo from './assets/react.svg'
import viteLogo from '/vite.svg'
import './App.css'
import LifecycleText from './components/LifecycleText'
import Comment from './components/Comment';
import CommentList from './components/CommentList'

function App() {
  // const [isButton, setIsButton] = useState(true);
  // const toggleButton = () =>{
  //   setIsButton(!isButton);
  // }

  return (
    <>
      {/* {isButton && <LifecycleText/>}
      <button onClick={toggleButton}>count없애기</button> */}

      {/* <Comment message={"안녕하세요"}/> */}
      <CommentList />
    </>
  )
}

export default App
