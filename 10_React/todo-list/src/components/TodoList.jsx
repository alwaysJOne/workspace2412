import React, { useState } from 'react'
import styled from 'styled-components'
import TodoItem from './TodoItem'

const Container = styled.div`
    width: 100%;
    margin: 0 auto;
    padding: 24px;
    background: #ffffff;
    max-width: 500px;
    border-radius: 12px;
    box-shadow: 0 4px 6px #e7e7e7;
`

const Title = styled.h1`
    font-size: 48px;
    font-weight: bold;
    margin-bottom: 24px;
`

const InputContainer = styled.div`
    display: flex;
    margin-bottom: 24px;
`

const Input = styled.input`
    flex: 1;
    padding: 12px;
    border: 1px solid #e2e2e2;
    outline: none;
    border-radius: 4px 0 0 4px;

    &:focus{
        border-color: #4b7fcc;
    }
`
const AddButton = styled.button`
    padding: 12px 24px;
    background: #4b7fcc;
    color: #ffffff;
    border: none;
    border-radius: 0 4px 4px 0;
    cursor: pointer;

    &:hover{
        background-color: #4b7fccdd;
    }
`

const TodoListContainer = styled.ul`
    list-style: none;
`


const TodoList = () => {
    const [newTodo, setNewTodo] = useState('');
    const [todos, setTodos] = useState([]);

    const addTodo = () => {
        if(newTodo.trim() === '') return;
    
        const todo = {
            id : Date.now(),
            text : newTodo,
            completed : false,
        }

        setTodos([...todos, todo]);
        setNewTodo('');
    }

    const handleKeyDown = (ev) => {
        if(ev.key === "Enter"){
            addTodo();
        }
    }

    const onToggle = (id) =>{
        //배열의 갯수 -> 유지
        //상태만 변경 -> 특정조건으로
        setTodos(todos.map(todo => 
            todo.id === id ? {...todo, completed: !todo.completed} : todo
        ));
    }

    const onDelete = (id) => {
        setTodos(todos.filter(todo => todo.id !== id));
    }

    return (
        <Container>
            <Title>Todo List</Title>
            <InputContainer>
                <Input
                    type="text"
                    value={newTodo}
                    onChange={(e) => setNewTodo(e.target.value)}
                    onKeyDown={handleKeyDown}
                    placeholder='할일을 입력하세요.'
                />
                <AddButton onClick={addTodo}>
                    추가
                </AddButton>
            </InputContainer>
            <TodoListContainer>
                {todos.map(todo => 
                    <TodoItem
                      key={todo.id}
                      todo={todo}
                      onToggle = {onToggle}
                      onDelete = {onDelete}
                    />
                )}
            </TodoListContainer>
        </Container>
    )
}

export default TodoList
