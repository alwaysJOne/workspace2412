import React, {useState} from 'react'
import Toolbar from './Toolbar'
import Grade from './Grade'

const LandingPage = () => {
    const [isLogin, setIsLoging] = useState(false);
    
    const onClickLogin = () => {
        setIsLoging(true);
    }

    const onClickLogout = () => {
        setIsLoging(false);
    }

    return (
        <div>
            <Toolbar 
                isLogin={isLogin}
                onClickLogin = {onClickLogin}
                onClickLogout= {onClickLogout}
             />
            <Grade isLogin={isLogin} />
        </div>
    )
}

export default LandingPage