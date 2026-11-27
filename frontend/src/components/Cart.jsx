export default function Cart({ items, onUpdateQuantity, onRemoveItem, onCheckout, loading }) {
  const totalAmount = items.reduce(
    (sum, item) => sum + Number(item.price) * item.quantity,
    0
  );

  const formatPrice = (price) => {
    return Number(price).toLocaleString("vi-VN") + " đ";
  };

  return (
    <div className="cart-sidebar">
      <h2 className="cart-title">
        🛒 Giỏ hàng ({items.length})
      </h2>

      {items.length === 0 ? (
        <div className="cart-empty">
          <p>Giỏ hàng chưa có sản phẩm nào.</p>
          <small>Vui lòng chọn món từ thực đơn bên trái.</small>
        </div>
      ) : (
        <>
          <div className="cart-items-list">
            {items.map((item) => (
              <div key={item.id} className="cart-item-row">
                <div className="item-info">
                  <strong className="item-name">{item.name}</strong>
                  <span className="item-price">{formatPrice(item.price)}</span>
                </div>
                <div className="item-actions">
                  <div className="mini-qty">
                    <button
                      type="button"
                      className="btn-mini-qty"
                      onClick={() => onUpdateQuantity(item.id, item.quantity - 1)}
                    >
                      -
                    </button>
                    <span className="mini-qty-val">{item.quantity}</span>
                    <button
                      type="button"
                      className="btn-mini-qty"
                      onClick={() => onUpdateQuantity(item.id, item.quantity + 1)}
                    >
                      +
                    </button>
                  </div>
                  <button
                    type="button"
                    className="btn-remove"
                    title="Xóa món này"
                    onClick={() => onRemoveItem(item.id)}
                  >
                    🗑️
                  </button>
                </div>
              </div>
            ))}
          </div>

          <div className="cart-summary">
            <div className="summary-row">
              <span>Tổng tiền:</span>
              <strong className="summary-total">{formatPrice(totalAmount)}</strong>
            </div>

            <button
              type="button"
              className="btn-checkout"
              disabled={loading || items.length === 0}
              onClick={onCheckout}
            >
              {loading ? "Đang xử lý đặt hàng..." : "💳 Đặt hàng ngay"}
            </button>
          </div>
        </>
      )}
    </div>
  );
}
