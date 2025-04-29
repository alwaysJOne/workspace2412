import styled from "styled-components";

export const Container = styled.div`
    max-width: 1000px;
    margin: 0 auto;
    padding: 24px;
`

export const Loading = styled.div`
    text-align: center;
    padding: 24px;
    font-size: 24px;
    color: #666;
`

export const Error = styled.div`
    text-align: center;
    padding: 24px;
    font-size: 24px;
    color: red;
`

export const PostCard = styled.div`
    background: white;
    padding: 18px;
    margin-bottom: 18px;
    position: relative;
    border-radius: 8px;
    box-shadow: 0 2px 4px gainsboro;
`

export const Title = styled.h2`
    color: #333333;
    margin-bottom: 8px;
`

export const Content = styled.p`
    color: #333333;
    margin-bottom: 8px;
`

export const ButtonContainer = styled.div`
    display: flex;
    gap: 8px;
    justify-content: flex-end;
`

export const LoadingOverlay = styled.div`
    position: absolute;
    top: 0;
    left: 0;
    right: 0;
    bottom: 0;
    display: flex;
    align-items: center;
    justify-content: center;
    background: rgba(255,255,255,0.8);

    div{
        background: white;
        opacity: 0.8;
        font-size: 18px;
        font-weight: 700;
        border-radius: 4px;
    }
`