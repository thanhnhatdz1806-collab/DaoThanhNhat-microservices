import { useState, useEffect } from "react";
import { useNavigate } from "react-router-dom";
import { productService } from "../../services/productService";
import { orderService } from "../../services/orderService";
import ProductCard from "../../components/ProductCard";
import Cart from "../../components/Cart";

export default function ProductListPage() {
  const [products, setProducts] = useState([]);
  const [selectedProducts, setSelectedProducts] = useState([]);
  const [loading, setLoading] = useState(false);
  const [fetching, setFetching] = useState(true);
  const [errorMsg, setErrorMsg] = useState("");
  const navigate = useNavigate();

  // 1. Lấy danh sách sản phẩm từ Menu Service
  useEffect(() => {
    fetchProducts();
  }, []);

  async function fetchProducts() {
    try {
      setFetching(true);
      setErrorMsg("");
      const data = await productService.getAll();
      setProducts(data);
    } catch (err) {
      console.error("Lỗi khi tải sản phẩm:", err);
      setErrorMsg("Không thể tải danh sách món. Vui lòng kiểm tra Menu Service (port 8081).");
    } finally {
      setFetching(false);
    }
  }

  // 2. Thêm món vào giỏ
  function handleAddToCart(product, qty) {
    setSelectedProducts((prev) => {
      const existing = prev.find((item) => item.id === product.id);
      if (existing) {
        return prev.map((item) =>
          item.id === product.id
            ? { ...item, quantity: item.quantity + qty }
            : item
        );
      }
      return [...prev, { ...product, quantity: qty }];
    });
  }

  // 3. Thay đổi số lượng món trong giỏ
  function handleUpdateQuantity(productId, newQty) {
    if (newQty <= 0) {
      handleRemoveItem(productId);
      return;
    }
    setSelectedProducts((prev) =>
      prev.map((item) =>
        item.id === productId ? { ...item, quantity: newQty } : item
      )
    );
  }

  // 4. Xóa món khỏi giỏ
  function handleRemoveItem(productId) {
    setSelectedProducts((prev) => prev.filter((item) => item.id !== productId));
  }

  // 5. Xử lý sự kiện đặt hàng (handleCreateOrder)
  async function handleCreateOrder() {
    if (selectedProducts.length === 0) {
      alert("Vui lòng chọn sản phẩm!");
      return;
    }

    const orderData = {
      items: selectedProducts.map((product) => ({
        productId: product.id,
        quantity: Number(product.quantity),
      })),
    };

    try {
      setLoading(true);
      const result = await orderService.create(orderData);
      console.log("Đặt hàng thành công:", result);
      setSelectedProducts([]);
      // Điều hướng tới trang OrderSuccess
      navigate("/order-success", { state: { order: result } });
    } catch (error) {
      console.error("Lỗi đặt hàng:", error);
      alert(
        error.response?.data?.message || "Không thể đặt hàng! Vui lòng thử lại."
      );
    } finally {
      setLoading(false);
    }
  }

  return (
    <div className="product-list-page">
      <div className="products-container">
        <div className="page-header">
          <h2>📋 Thực Đơn Quán Cà Phê</h2>
          <p>Chọn các món đồ uống và bánh ngọt yêu thích của bạn</p>
        </div>

        {fetching ? (
          <div className="loading-state">
            <p>⏳ Đang tải thực đơn...</p>
          </div>
        ) : errorMsg ? (
          <div className="error-state">
            <p>{errorMsg}</p>
            <button type="button" className="btn-retry" onClick={fetchProducts}>
              Thử lại
            </button>
          </div>
        ) : products.length === 0 ? (
          <div className="empty-state">
            <p>Chưa có món nào trong thực đơn.</p>
          </div>
        ) : (
          <div className="products-grid">
            {products.map((product) => (
              <ProductCard
                key={product.id}
                product={product}
                onAddToCart={handleAddToCart}
              />
            ))}
          </div>
        )}
      </div>

      <div className="cart-container">
        <Cart
          items={selectedProducts}
          onUpdateQuantity={handleUpdateQuantity}
          onRemoveItem={handleRemoveItem}
          onCheckout={handleCreateOrder}
          loading={loading}
        />
      </div>
    </div>
  );
}
