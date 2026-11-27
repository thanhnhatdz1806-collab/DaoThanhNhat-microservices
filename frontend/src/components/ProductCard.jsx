import { useState } from "react";

export default function ProductCard({ product, onAddToCart }) {
  const [quantity, setQuantity] = useState(1);

  const handleDecrease = () => {
    if (quantity > 1) setQuantity(quantity - 1);
  };

  const handleIncrease = () => {
    setQuantity(quantity + 1);
  };

  const formatPrice = (price) => {
    return Number(price).toLocaleString("vi-VN") + " đ";
  };

  return (
    <div className="product-card">
      <div className="product-icon">☕</div>
      <h3 className="product-title">{product.name}</h3>
      <p className="product-price">{formatPrice(product.price)}</p>

      <div className="quantity-control">
        <button type="button" className="btn-qty" onClick={handleDecrease}>-</button>
        <span className="qty-value">{quantity}</span>
        <button type="button" className="btn-qty" onClick={handleIncrease}>+</button>
      </div>

      <button
        type="button"
        className="btn-add-cart"
        onClick={() => {
          onAddToCart(product, quantity);
          setQuantity(1);
        }}
      >
        Thêm vào giỏ
      </button>
    </div>
  );
}
