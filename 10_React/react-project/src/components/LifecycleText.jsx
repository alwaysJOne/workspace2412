import React, { Component } from 'react'
/*
    class component
    state(필드대체)를 가지고있고 이를 수정할 수 있음.
    생명주기에 따른 메서드를 사용할 수 있음.

    state값이 변경되는 리액트는 변화를 감지하고 그에맞게 컴포넌트를 리렌더링 한다.
    그래서 state값을 변경할 때는 state에 어떤 값을 직접 넣어서 변경하는 것이 아닌
    this.setState라는 함수를 이용해서 새로운 state값을 넣어주면 된다.

    react의 component 라이프사이클은 생성(mount), 수정(update), 제거(unmount) 단계로 나누어
    각 단계마다 componentDidMount, componentDidUpdate, componentWillUnmount같은 메서드를 실행하여
    DOM을 조적하거나 리소스를 정리할 수 있는 기능을 말함.
*/

class LifecycleText extends Component {
    constructor(props){
        super(props);

        //js에서는 class에 필드영역이 없기때문에
        //저장하고싶은 데이터를 state라는 객체에 key-value형태로 저장한다.
        this.state = {
            count: 0,
        }

        console.log("constructor : 컴포넌트 생성자 호출");
    }

    // 컴포넌트가 마운트되었을 때 호출(처음 렌더링될 때)
    componentDidMount(){
        console.log('componentDidMount : 컴포넌트가 마운트 되었습니다.')
    }

    //컴포넌트가 업데이트된 후 호출
    componentDidUpdate(prevProps, prevState){
        console.log("componentDidUpdate : 컴포넌트가 업데이트 되었습니다.")
        console.log("이전 state : ", prevState);
        console.log("이전 props : ", prevProps);
    }

    // 컴포넌트가 언마운트 될 때 호출
    componentWillUnmount() {
        console.log("componentWillUnmount : 컴포넌트가 언마운트 됩니다.");
    }

    increment = () => {
        this.setState({
            count: this.state.count + 1
        })
    }

    render() {
        return (
            <div>
                <p>Count : {this.state.count}</p>
                <button onClick={this.increment}> 1증가 </button>
            </div>
        )
    }
}

export default LifecycleText