-- ===================================================
-- DATABASE FOR SERVICE 2 (Order Service) - SQL Server
-- Database Name: order_db
-- ===================================================

IF NOT EXISTS (SELECT * FROM sys.databases WHERE name = 'order_db')
BEGIN
    CREATE DATABASE order_db;
END
GO

USE order_db;
GO

-- 1. Bảng Carts (Giỏ hàng)
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'carts')
BEGIN
    CREATE TABLE carts (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        customer_id BIGINT NOT NULL,
        created_at DATETIME DEFAULT GETDATE()
    );
END
GO

-- 2. Bảng Cart Items (Chi tiết giỏ hàng)
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'cart_items')
BEGIN
    CREATE TABLE cart_items (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        cart_id BIGINT NOT NULL,
        product_id BIGINT NOT NULL,
        quantity INT NOT NULL DEFAULT 1,
        price DECIMAL(19, 2) NOT NULL,
        CONSTRAINT fk_cart_items_cart FOREIGN KEY (cart_id) REFERENCES carts(id) ON DELETE CASCADE
    );
END
GO

-- 3. Bảng Orders (Đơn hàng quán cà phê)
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'orders')
BEGIN
    CREATE TABLE orders (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        customer_id BIGINT NOT NULL,
        total_amount DECIMAL(19, 2) NOT NULL,
        status NVARCHAR(50) DEFAULT 'PENDING',
        shipping_address NVARCHAR(500),
        created_at DATETIME DEFAULT GETDATE()
    );
END
GO

-- 4. Bảng Order Items (Chi tiết món trong đơn)
IF NOT EXISTS (SELECT * FROM sys.tables WHERE name = 'order_items')
BEGIN
    CREATE TABLE order_items (
        id BIGINT IDENTITY(1,1) PRIMARY KEY,
        order_id BIGINT NOT NULL,
        product_id BIGINT NOT NULL,
        quantity INT NOT NULL,
        unit_price DECIMAL(19, 2) NOT NULL,
        CONSTRAINT fk_order_items_order FOREIGN KEY (order_id) REFERENCES orders(id) ON DELETE CASCADE
    );
END
GO

-- ===================================================
-- Dữ liệu mẫu Quán Cà Phê (Sample Data Coffee Shop)
-- ===================================================
-- Xóa dữ liệu cũ và reset IDENTITY để dữ liệu luôn chuẩn xác
DELETE FROM order_items;
DELETE FROM orders;
DELETE FROM cart_items;
DELETE FROM carts;
DBCC CHECKIDENT ('orders', RESEED, 0);
DBCC CHECKIDENT ('order_items', RESEED, 0);
DBCC CHECKIDENT ('carts', RESEED, 0);
DBCC CHECKIDENT ('cart_items', RESEED, 0);
GO

-- Giỏ hàng hiện tại của khách hàng 1 (đang chọn 1 Cà Phê Muối)
INSERT INTO carts (customer_id) VALUES (1);
INSERT INTO cart_items (cart_id, product_id, quantity, price) VALUES (1, 4, 1, 35000.00);

-- Đơn hàng đã đặt trước đó của khách hàng 1:
-- Gồm: 2 Cà Phê Sữa Đá (29.000 x 2 = 58.000) + 1 Bánh Croissant (35.000) = 93.000 VND
INSERT INTO orders (customer_id, total_amount, status, shipping_address) 
VALUES (1, 93000.00, 'COMPLETED', N'Bàn số 05 - Tầng 1 (Uống tại quán)');

INSERT INTO order_items (order_id, product_id, quantity, unit_price) 
VALUES 
(1, 2, 2, 29000.00),
(1, 14, 1, 35000.00);
GO
