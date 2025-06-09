import api from './axios';
import { API_ENDPOINTS } from './config';

export const productService = {
  getProducts: async () => {
    try {
      const { data } = await api.get(API_ENDPOINTS.PRODUCTS.BASE);
      return data;
    } catch (error) {
      console.log('상품정보를 가져오지 못함 : ', error.response?.data?.message || '상품목록 불러오기 실패');
      throw error;
    }
  },
};
