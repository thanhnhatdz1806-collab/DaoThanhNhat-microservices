import { Routes, Route } from "react-router-dom";
import ClientLayout from "../layouts/ClientLayout";
import AdminLayout from "../layouts/AdminLayout";
import HomePage from "../pages/client/HomePage";
import ProductListPage from "../pages/client/ProductListPage";
import OrderSuccessPage from "../pages/client/OrderSuccessPage";
import DashboardPage from "../pages/admin/DashboardPage";

export default function AppRoutes() {
  return (
    <Routes>
      {/* Route Client */}
      <Route element={<ClientLayout />}>
        <Route path="/" element={<HomePage />} />
        <Route path="/products" element={<ProductListPage />} />
        <Route path="/order-success" element={<OrderSuccessPage />} />
      </Route>

      {/* Route Admin */}
      <Route path="/admin" element={<AdminLayout />}>
        <Route index element={<DashboardPage />} />
      </Route>
    </Routes>
  );
}
