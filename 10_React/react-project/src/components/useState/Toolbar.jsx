import React, {useState} from 'react'

const Toolbar = ({isLogin, onClickLogin, onClickLogout}) => {
    

    return (
        <div>
            {
                isLogin ? 
                <div>
                    <div>안녕하세요. 최지원님</div>
                    <button onClick={onClickLogout}>로그아웃</button>
                </div> :
                <div>
                    <div>로그인이 필요한 서비스입니다.</div>
                    <button onClick={onClickLogin}>로그인</button>
                </div>
            }
        </div>
    )
}

export default Toolbar