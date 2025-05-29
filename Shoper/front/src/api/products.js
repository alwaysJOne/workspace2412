import axiosInstance from './axios';
import { API_ENDPOINTS } from './config';

export const getProducts = async () => {
  try {
    const { data } = await axiosInstance.get(API_ENDPOINTS.PRODUCTS);
    return data;
  } catch (error) {
    console.log('상품정보를 가져오지 못함 : ', error);
    throw error;
  }
};
