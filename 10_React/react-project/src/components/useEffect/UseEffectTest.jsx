import React, { useEffect, useState } from 'react'

const UseEffectTest = () => {
    const [name, setName] = useState("김개똥");
    const [num, setNum] = useState(0);
    
    const handleChangeName = (ev) => {
        setName(ev.target.value);
    }

    const handleClickNum = () => {
        setNum(prev => prev + 1);
    }

    //1. 의존성 배열이 없을 때
    useEffect(() => {
        console.log("의존성 없음 : 모든 렌더링마다 실행됨.")
    })

    //2. 빈 배열을 의존성으로 설정했을 때
    useEffect(() => {
        console.log("빈 배열을 의존성으로 설정했을 때 : 컴포넌트가 마운트 될 때 1번만 실행")
    }, [])

    return (
        <div>
            <h2>useEffect 테스트</h2>
            <p>안녕하세요. <strong>{name}</strong>입니다.</p>

            <input 
                type="text"
                onChange={handleChangeName}
                value={name}
                placeholder='이름을 입력해주세요.'
            />

            <p>버튼을 <strong>{num}</strong>번 클릭했습니다.</p>
            <button onClick={handleClickNum}>+1증가</button>
        </div>
    )
}

export default UseEffectTest