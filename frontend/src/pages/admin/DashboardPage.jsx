import { Link } from "react-router-dom";

export default function DashboardPage() {
  return (
    <div className="dashboard-page">
      <h2>📊 Tổng Quan Quản Trị Quán Cà Phê</h2>
      <p className="dashboard-subtitle">
        Quản lý danh mục, món ăn, thực đơn và đơn hàng phát sinh (Dành cho Câu E).
      </p>

      <div className="stats-grid">
        <div className="stat-card">
          <h4>Microservice 1</h4>
          <p className="stat-name">Menu Service</p>
          <span className="stat-port">Port 8081</span>
          <span className="stat-status online">🟢 Hoạt động</span>
        </div>
        <div className="stat-card">
          <h4>Microservice 2</h4>
          <p className="stat-name">Order Service</p>
          <span className="stat-port">Port 8082</span>
          <span className="stat-status online">🟢 Hoạt động</span>
        </div>
        <div className="stat-card">
          <h4>Microservice 3</h4>
          <p className="stat-name">Customer Service</p>
          <span className="stat-port">Port 8083</span>
          <span className="stat-status online">🟢 Hoạt động</span>
        </div>
      </div>

      <div className="quick-actions-box">
        <h3>Lối tắt kiểm tra</h3>
        <div className="actions-row">
          <Link to="/products" className="btn-action">
            🛒 Đi đến trang đặt món (Client)
          </Link>
        </div>
      </div>
    </div>
  );
}
