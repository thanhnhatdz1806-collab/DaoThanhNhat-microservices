import { orderApi } from "./api";

export const orderService = {
  create: async (data) => {
    const response = await orderApi.post("/orders", data);
    return response.data;
  }
};
