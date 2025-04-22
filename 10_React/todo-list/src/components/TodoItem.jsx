import React from 'react'
import styled from 'styled-components'

const TodoItemContainer = styled.li`
    display: flex;
    align-items: center;
    justify-content: space-between;
    padding: 6px 12px;
    border: 1px solid #e2e2e2c8;
    margin-bottom: 12px;
    border-radius: 6px;
`

const TodoContent = styled.div`
    display: flex;
    align-items: center;
    gap: 12px;
`

const TodoText = styled.span`
    text-decoration: ${props => props.completed ? 'line-through' : 'none'}; 
    color: ${props => props.completed ? 'inherit' : '#118534'};
`

const Checkbox = styled.input`
    width: 18px;
    height: 18px;
`

const DeleteButton = styled.button`
    color: #d62727;
    background: none;
    border: none;
    cursor: pointer;
    outline: none;

    &:hover{
        color: #ac2020;
    }
`

const TodoItem = ({todo, onToggle, onDelete}) => {

  return (
    <TodoItemContainer>
        <TodoContent>
            <Checkbox 
                type="checkbox"
                checked={todo.completed}
                onChange={() => onToggle(todo.id)}
             />
             <TodoText completed = {todo.completed}>
                {todo.text}
             </TodoText>
        </TodoContent>
        <DeleteButton onClick={() => onDelete(todo.id)}>
            삭제
        </DeleteButton>
    </TodoItemContainer>
  )
}

export default TodoItem