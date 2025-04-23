import React, { useState } from 'react'

const UseCallbackTest = () => {
    const [num, setNum] = useState(1);
    const [dark, setDark] = useState(false);

    const theme = {
        backgroundColor: dark ? "#333" : "#fff",
        color: dark ? "#fff" : "#333",
        padding: "12px",
    }
  
    const onChangeNum = (ev) => {
        setNum(parseInt(ev.target.value))
    };



    return (
        <div style={theme}>
            <h2>useCallback 테스트</h2>
            <input 
                type="number"
                value={num}
                onChange={onChangeNum}
            />

            <button onClick={() => setDark(prev => !prev)}>
                테마 변경
            </button>
        </div>
  )
}

export default UseCallbackTest