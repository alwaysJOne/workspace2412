import React, { useState, useMemo, useCallback } from 'react'
import ViewItem from './ViewItem';

const UseCallbackTest = () => {
    const [num, setNum] = useState(1);
    const [dark, setDark] = useState(false);

    //useCallback으로 함수 캐싱함
    const getItems = useCallback(() => num ? [num, num+1, num+2] : [0,0,0], [num]);

    const theme = useMemo(()=>({
        backgroundColor: dark ? "#333" : "#fff",
        color: dark ? "#fff" : "#333",
        padding: "12px",
    }), [dark])
  
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

            <ViewItem getItems={getItems} />
        </div>
  )
}

export default UseCallbackTest