import { Link } from "react-router-dom";

export default function Header({ cartCount = 0 }) {
  return (
    <header className="header">
      <div className="header-container">
        <Link to="/" className="brand-logo">
          ☕ <span>Coffee Shop</span>
        </Link>
        <nav className="nav-links">
          <Link to="/" className="nav-link">Trang chủ</Link>
          <Link to="/products" className="nav-link">Thực đơn</Link>
          <Link to="/admin" className="nav-link admin-tag">Quản trị</Link>
        </nav>
        <div className="header-actions">
          <Link to="/products" className="cart-badge">
            🛒 Giỏ hàng
            {cartCount > 0 && <span className="badge-count">{cartCount}</span>}
          </Link>
        </div>
      </div>
    </header>
  );
}
