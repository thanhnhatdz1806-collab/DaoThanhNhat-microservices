import { Link } from "react-router-dom";

export default function HomePage() {
  return (
    <div className="home-page">
      <div className="hero-section">
        <h1>☕ Chào Mừng Đến Với Coffee Shop</h1>
        <p>Thưởng thức hương vị cà phê phin truyền thống và espresso thượng hạng mỗi ngày.</p>
        <Link to="/products" className="btn-hero">
          Xem Thực Đơn & Đặt Món Ngay
        </Link>
      </div>

      <div className="features-section">
        <div className="feature-item">
          <div className="feature-icon">🌿</div>
          <h3>100% Cà phê nguyên chất</h3>
          <p>Hạt cà phê Robusta và Arabica được tuyển chọn kỹ lưỡng.</p>
        </div>
        <div className="feature-item">
          <div className="feature-icon">⚡</div>
          <h3>Phục vụ nhanh chóng</h3>
          <p>Hệ thống xử lý đơn hàng liên microservices tức thì.</p>
        </div>
        <div className="feature-item">
          <div className="feature-icon">🍰</div>
          <h3>Bánh ngọt nướng tươi</h3>
          <p>Croissant, Tiramisu thơm ngon chuẩn vị châu Âu.</p>
        </div>
      </div>
    </div>
  );
}
