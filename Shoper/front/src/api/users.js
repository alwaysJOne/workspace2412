import api from './axios';
import { API_ENDPOINTS } from './config';

export const userService = {
  //회원가입
  sugnUp: async (userData) => {
    console.log('????');
    try {
      const { data } = await api.post(API_ENDPOINTS.USERS.BASE, {
        email: userData.email,
        password: userData.password,
        username: userData.username,
        role: 'user',
        createdAt: new Date().toISOString(),
        updatedAt: new Date().toISOString(),
      });

      return data;
    } catch (error) {
      if (error.response) {
        const message = error.response?.data?.message || '회원가입에 실패했습니다.';
        throw new Error(message);
      }

      throw new Error('서버 통신 불량');
    }
  },
};
