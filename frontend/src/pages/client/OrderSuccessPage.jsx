import { useLocation, Link, Navigate } from "react-router-dom";

export default function OrderSuccessPage() {
  const location = useLocation();
  const order = location.state?.order;

  if (!order) {
    return <Navigate to="/products" replace />;
  }

  const formatPrice = (price) => {
    return Number(price).toLocaleString("vi-VN") + " đ";
  };

  return (
    <div className="order-success-page">
      <div className="success-card">
        <div className="success-icon">🎉</div>
        <h2>Đặt Hàng Thành Công!</h2>
        <p className="success-desc">
          Đơn hàng của bạn đã được ghi nhận vào hệ thống và đang được quầy Barista chuẩn bị.
        </p>

        <div className="order-details-box">
          <div className="order-detail-row">
            <span>Mã đơn hàng:</span>
            <strong>#{order.id}</strong>
          </div>
          <div className="order-detail-row">
            <span>Tổng số tiền:</span>
            <strong className="order-total-highlight">
              {formatPrice(order.totalAmount)}
            </strong>
          </div>
          <div className="order-detail-row">
            <span>Trạng thái:</span>
            <span className="status-badge">PENDING (Chờ pha chế)</span>
          </div>
        </div>

        <div className="success-actions">
          <Link to="/products" className="btn-order-more">
            Tiếp tục gọi món
          </Link>
          <Link to="/" className="btn-home">
            Về trang chủ
          </Link>
        </div>
      </div>
    </div>
  );
}
