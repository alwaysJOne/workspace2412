import React from 'react'

const text = "Hello, JSX";
const num = 10;

const JavaScript = () => {
  return (
    <div>
        <h2>변수 표현 방법</h2>
        <ul>
            <li>{text}</li>
            <li>{text + " TEST"}</li>
        </ul>
        <h2>숫자및 계산식 사용</h2>
        <ul>
            <li>{num}</li>
            <li>{num + 20}</li>
        </ul>

        <h2>Boolean 값</h2>
        <ul>
            <li>{true}</li>
            <li>{false}</li>
            <li>{undefined}</li>
            <li>{null}</li>
        </ul>

        <h2></h2>
    </div>
  )
}

export default JavaScript