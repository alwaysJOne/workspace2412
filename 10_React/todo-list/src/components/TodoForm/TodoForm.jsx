import { useState, useCallback } from 'react';
import { FormContainer, Input, Select, AddButton } from './TodoForm.styled';

const TodoForm = ({ onAdd }) => {
  const [text, setText] = useState('');
  const [category, setCategory] = useState('personal');

  const handleSubmit = useCallback(() => {
    if (text.trim() === '') return;
    onAdd(text, category);
    setText('');
  }, [text, category, onAdd]);

  const handleKeyDown = useCallback((e) => {
    if (e.key === 'Enter') {
      handleSubmit();
    }
  }, [handleSubmit]);

  return (
    <FormContainer>
      <Input
        type="text"
        value={text}
        onChange={(e) => setText(e.target.value)}
        onKeyDown={handleKeyDown}
        placeholder="할일을 입력하세요."
      />
      <Select value={category} onChange={(e) => setCategory(e.target.value)}>
        <option value="work">업무</option>
        <option value="personal">개인</option>
        <option value="study">학습</option>
      </Select>
      <AddButton onClick={handleSubmit}>
        추가
      </AddButton>
    </FormContainer>
  );
};

export default TodoForm;
