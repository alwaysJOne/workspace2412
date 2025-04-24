import React, { useState } from 'react'
import useToggle from './useToggle';

const ToggleBox = () => {
    const [isView, toggleIsView] = useToggle();

    return (
        <div>
            <button onClick={toggleIsView}>
                {isView ? "숨기기" : "보이기"}
            </button>
            {isView && <div>짜잔!!! 여기는 숨겨진 영역입니다.!</div>}
        </div>
    )
}

export default ToggleBox