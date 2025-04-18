import React from 'react'
import './style.css';//외부css파일 가져오기
import styled from 'styled-components'; //styled-components라이브러리 가져오기

const roundBoxStyle = {
    position: "absolute",
    top: 50,
    left: 50,
    width: "200px",
    height: "200px",
    backgroundColor: "#daf6ff",
    boarderRadius: 50,
}

const HighlightDiv = styled.div`
    background: #f5fd89;
    padding: 10;
    font-size: 20px;
    border-radius: 5px;
`
const RoundBox = styled.div`
    position: absolute;
    top: ${props => props.top || 50}px;
    left: 50px;
    width: 200px;
    height: 200px;
    background: #daf6ff;
    border-radius: 50px;
`

const Style = () => {
  return (
    <>
        <h3>1. Object로 css작성</h3>
        <div 
            style={{
                position: "relative",
                width: 400,
                height: 1500,
                backgroundColor: "#f1f1f1",
            }}
        >
            <h3>2_1. css-in-js로 객체 스타일을 직접 작성</h3>
            <div style={roundBoxStyle}>안녕</div>
            <h3>2_2. 스타일 객체 재활용 가능</h3>
            <div style={{...roundBoxStyle, top: 300}}>
                {/* 3. class를 활용한 스타일 */}
                <div className='highlight'>안녕2</div>
            </div>

            {/*4. 조건적 스타일 */}
            <div style={{...roundBoxStyle, top: 550}}>
                <div className={1+1!==2 && "highlight"}>안녕3</div>
            </div>
            <RoundBox top={800}>
                <HighlightDiv>styled-components를 이용한 스타일링</HighlightDiv>
            </RoundBox>
        </div>
    </>
  )
}

export default Style