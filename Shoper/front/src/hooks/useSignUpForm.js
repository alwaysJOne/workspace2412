import * as yup from 'yup';

//회원가입 폼의 유효성 검사 스키마
const signUpSchema = yup.object().shape({
  username: yup
    .string()
    .min(2, '사용자 이름은 2자 이상...')
    .max(20, '사용자 이름은 20자 이하...')
    .required('사용자 이름을 입력...'),

  email: yup.string().email('유효한 이메일 주소를 입력...').required('이메일을 입력...'),

  password: yup
    .string()
    .min(8, '비밀번호는 8자 이상...')
    .matches(/^(?=.*[A-Za-z])(?=.*\d)[A-Za-z\d]{8,}$/, '비밀번호는 영문자와 숫자를 포함...')
    .required('비밀번호를 입력해주세요.'),
});
