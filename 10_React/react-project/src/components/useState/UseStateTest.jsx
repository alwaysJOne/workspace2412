import React, { useState } from 'react'

/*
    state : 컴포넌트의 상태값
    useState : 컴포넌트의 상태를 생성하고 관리할 수 있게 해주는 react hook
    -> 컴포넌트는 state값이 변경되면 이를 확인하고 요소를 리렌더링 해준다.

    [사용법]
    const [변수명, set변수명] = useState(초기값);

    React에서 setState와 같은 상태 업데이트 함수는 비동기적으로 작동한다.
    즉, 상태를 바로바로 반영하지 않고, 여러 상태 업데이트를 배치에서 한번에 처리하여고 한다.

    setNum(prevNum => prevNum - 1)에서는 클로저가 해당 데이터를 저장해서 사용할 수 있게 해준다.
*/

// function setNum = (value) => {
//     if(typeof value === 'function'){
//         prevNum = value(prevNum);
//     } else {
//         prevNum = value;
//     }
// }

const UseStateTest = () => {
    //const num = 0;
    const [num, setNum] = useState(0);

    const onClickPlus = () => {
        //num += 1; 현재 상태를 직접 상용해서 변경 -> state변경x -> 리렌더링x
        setNum(num + 1);
        setNum(num + 1);
        //결과적으로 num + 1이 됨 -> 실시간으로 처리되는 것이 아니라 모아두었다가 한번에 처리하는 방식
    }

    const onClickMinus = () => {
        setNum(prevNum => prevNum - 1);
        setNum((prevNum) => { 
            console.log("이전 상태 출력 : ", prevNum)
            return prevNum - 1;
        });
        console.log("상태 업데이트 요청");
        // 상태가 이전 상태에 의존하는 경우에는
        //항상 상태 업데이트 함수에 콜백을 사용하는 방식(prevNum => prevNum - 1)을 사용해야 한다.
    }

    return (
        <div>
            <div>COUNT : {num}</div> 
            <button onClick={onClickPlus}> + </button>
            <button onClick={onClickMinus}> - </button>
        </div>
    )
}

export default UseStateTest