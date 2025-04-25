import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import styled from 'styled-components';
import { useUsers } from '../context/UserContext';
import { Input, Checkbox } from '../components/common/Input';
import { PrimaryButton, SecondaryButton } from '../components/common/Button';

const FormContainer = styled.div`
  max-width: 500px;
  margin: 0 auto;
  background: white;
  padding: 40px;
  border-radius: 12px;
  box-shadow: 0 4px 6px rgba(0, 0, 0, 0.1);
`;

const FormTitle = styled.h2`
  margin-bottom: 32px;
  color: #2c3e50;
  font-size: 24px;
  text-align: center;
`;

const FormGroup = styled.div`
  margin-bottom: 24px;
`;

const Label = styled.label`
  display: block;
  margin-bottom: 8px;
  color: #34495e;
  font-size: 14px;
  font-weight: 500;
`;

const CheckboxContainer = styled.div`
  display: flex;
  align-items: center;
  margin-top: 12px;
  padding: 8px;
  background: #f8f9fa;
  border-radius: 8px;
`;

const ButtonGroup = styled.div`
  display: flex;
  gap: 16px;
  margin-top: 32px;
`;

const ErrorMessage = styled.p`
  color: #e74c3c;
  font-size: 14px;
  margin-top: 8px;
  padding-left: 4px;
`;

function UserRegistration() {
  const navigate = useNavigate();
  const { addUser } = useUsers();
  const [formData, setFormData] = useState({
    name: '',
    age: '',
    isOnline: false
  });
  const [errors, setErrors] = useState({});

  const validateForm = () => {
    const newErrors = {};
    if (!formData.name.trim()) {
      newErrors.name = '이름을 입력해주세요.';
    }
    if (!formData.age) {
      newErrors.age = '나이를 입력해주세요.';
    } else if (isNaN(formData.age) || formData.age < 0) {
      newErrors.age = '올바른 나이를 입력해주세요.';
    }
    return newErrors;
  };

  const handleSubmit = (e) => {
    e.preventDefault();
    const newErrors = validateForm();
    
    if (Object.keys(newErrors).length === 0) {
      addUser({
        name: formData.name.trim(),
        age: parseInt(formData.age),
        isOnline: formData.isOnline
      });
      navigate('/');
    } else {
      setErrors(newErrors);
    }
  };

  const handleChange = (e) => {
    const { name, value, type, checked } = e.target;
    setFormData(prev => ({
      ...prev,
      [name]: type === 'checkbox' ? checked : value
    }));
  };

  return (
    <FormContainer>
      <FormTitle>사용자 등록</FormTitle>
      <form onSubmit={handleSubmit}>
        <FormGroup>
          <Label htmlFor="name">이름</Label>
          <Input
            type="text"
            id="name"
            name="name"
            value={formData.name}
            onChange={handleChange}
            placeholder="이름을 입력하세요"
          />
          {errors.name && <ErrorMessage>{errors.name}</ErrorMessage>}
        </FormGroup>

        <FormGroup>
          <Label htmlFor="age">나이</Label>
          <Input
            type="number"
            id="age"
            name="age"
            value={formData.age}
            onChange={handleChange}
            placeholder="나이를 입력하세요"
          />
          {errors.age && <ErrorMessage>{errors.age}</ErrorMessage>}
        </FormGroup>

        <FormGroup>
          <CheckboxContainer>
            <Checkbox
              type="checkbox"
              id="isOnline"
              name="isOnline"
              checked={formData.isOnline}
              onChange={handleChange}
            />
            <Label htmlFor="isOnline">온라인 상태</Label>
          </CheckboxContainer>
        </FormGroup>

        <ButtonGroup>
          <PrimaryButton type="submit">등록하기</PrimaryButton>
          <SecondaryButton type="button" onClick={() => navigate('/')}>
            취소
          </SecondaryButton>
        </ButtonGroup>
      </form>
    </FormContainer>
  );
}

export default UserRegistration; 