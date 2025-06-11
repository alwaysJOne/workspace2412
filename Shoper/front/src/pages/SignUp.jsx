import React from 'react';
import { AuthContainer, AuthLink, Button, Form, Input, InputGroup, Label, Title } from '../styles/Auth.styles';

function SignUp() {
  return (
    <AuthContainer>
      <Title>회원가입</Title>
      <Form>
        <InputGroup>
          <Label htmlFor="username">사용자 이름</Label>
          <Input id="username" type="text" placeholder="사용자 이름을 입력하세요." />
        </InputGroup>

        <InputGroup>
          <Label htmlFor="username">이메일</Label>
          <Input id="email" type="email" placeholder="이메일을 입력하세요." />
        </InputGroup>

        <InputGroup>
          <Label htmlFor="password">비밀번호</Label>
          <Input id="password" type="password" placeholder="비밀번호를 입력하세요." />
        </InputGroup>

        <InputGroup>
          <Label htmlFor="confirmPassword">비밀번호 확인</Label>
          <Input id="confirmPassword" type="password" placeholder="비밀번호를 다시 입력하세요." />
        </InputGroup>

        <Button type="submit">{'가입하기'}</Button>
      </Form>
      <AuthLink to="/login">이미 계정이 있으신가요? 로그인하기</AuthLink>
    </AuthContainer>
  );
}

export default SignUp;
