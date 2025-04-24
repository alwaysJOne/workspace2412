import React from 'react'
import { useUser } from "./UserContext"

const Header = () => {
    const user = useUser();
    
    return (
        <header>
            <h2>안녕하세요, {user.name}님</h2>
            <p>나이 {user.age}세, 등급 {user.role}</p>
        </header>
    )
}

export default Header