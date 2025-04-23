import React, { useMemo } from 'react'

const ViewState = ({ num }) => {
    console.log("ViewState 렌더링");

    //임의의 연산이 많은 복잡한 계산함수
    const getHeavyResult = (value) => {
        console.log("getHeavyResult 연산실행");
        let i = 0;
        while (i < 1000000000) i++;
        return value * 2;
    }

    //useMemo로 연산결과를 캐싱
    const heavyResult = useMemo(() => getHeavyResult(num), [num]);

    return (
        <div>
            <p>현재 숫자: {num}</p>
            <p>계산된 값(x2) : {heavyResult}</p>
        </div>
    )
}

export default ViewState