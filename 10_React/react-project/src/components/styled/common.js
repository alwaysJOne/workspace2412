import styled from "styled-components";

export const Container = styled.div`
    max-width: 1000px;
    margin: 0 auto;
    padding: 24px;
    text-align: center;
`

export const Button = styled.button`
    display: inline-block;
    background: #6c6cff;
    color: white;
    padding: 12px 24px;
    border-radius: 4px;
    text-decoration: none;
    margin: 12px;
    cursor: pointer;

    &:hover{
        opacity: 0.9;
        color: white;
    }
`