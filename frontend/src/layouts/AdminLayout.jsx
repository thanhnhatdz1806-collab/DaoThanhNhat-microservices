import { Outlet, Link } from "react-router-dom";

export default function AdminLayout() {
  return (
    <div className="admin-container">
      <aside className="admin-sidebar">
        <div className="admin-brand">
          <h2>☕ Cafe Admin</h2>
        </div>
        <nav className="admin-menu">
          <Link to="/admin" className="admin-nav-item">📊 Tổng quan (Dashboard)</Link>
          <Link to="/products" className="admin-nav-item">🛒 Xem trang Client</Link>
        </nav>
      </aside>
      <div className="admin-main">
        <header className="admin-header">
          <h3>Hệ thống Quản trị Microservices</h3>
          <Link to="/" className="btn-back-home">Về trang chủ</Link>
        </header>
        <div className="admin-content">
          <Outlet />
        </div>
      </div>
    </div>
  );
}
