import axios from "axios";

// Kết nối Product Service (Menu Service)
export const productApi = axios.create({
  baseURL: import.meta.env.VITE_PRODUCT_API_URL,
  timeout: 10000
});

// Kết nối Order Service
export const orderApi = axios.create({
  baseURL: import.meta.env.VITE_ORDER_API_URL,
  timeout: 10000
});
