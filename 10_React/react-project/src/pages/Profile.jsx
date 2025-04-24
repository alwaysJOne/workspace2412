import React from 'react'
import { useParams } from 'react-router-dom'

const Profile = () => {
    //url에 등록되어있는 url 파라미터의 값을 가져옴
    const { username } = useParams();

    return (
        <div>
            <h2>{username}의 프로필 페이지입니다.</h2>
            <p>여기에 해당 사람의 정보를 보여주기</p>
        </div>
    )
}

export default Profile