import React, { useState } from 'react'
import UseEffectTest from './UseEffectTest'

const EffectView = () => {
    const [isView, setIsView] = useState(true);

    return (
        <div>
            <button onClick={()=> setIsView(prev => !prev)}>
                화면보기
            </button>
            {   isView &&
                <UseEffectTest />
            }
        </div>
    )
}

export default EffectView