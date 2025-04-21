import React, { Component } from 'react'
import styled from 'styled-components'

const Container = styled.div`
    margin: 8px;
    padding: 8px;
    display: flex;
    flex-direction: row;
    border: 1px solid gray;
    border-radius: 16px;
`
const CommentText = styled.span`
    font-size: 18px;
`

class Comment extends Component {
    constructor(props){
        super(props)

        this.state = {}
    }

    // componentDidMount(){
    //     console.log(`${this.props.id}의 componentDidMount`)
    // }

    // componentDidUpdate(){
    //     console.log(`${this.props.id}의 componentDidUpdate`)
    // }

    // componentWillUnmount(){
    //     console.log(`${this.props.id}의 componentWillUnmount`)
    // }

    //컴포넌트가 업데이트 되기 전에 호출
    shouldComponentUpdate(nextProps, nextState){
        console.log(`${this.props.id}의 shouldComponentUpdate`)
        console.log(`${nextProps.id}의 shouldComponentUpdate`)

        
        return true;
    }

    render() {
        return (
            <Container>
                <CommentText>
                    {this.props.message}
                </CommentText>
            </Container>
        )
    }
}

export default Comment