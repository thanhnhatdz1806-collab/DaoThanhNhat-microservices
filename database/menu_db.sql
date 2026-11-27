-- ===================================================
-- DATABASE FOR SERVICE 1 (Menu Service) - MySQL
-- Database Name: menu_db
-- ===================================================

CREATE DATABASE IF NOT EXISTS `menu_db` 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE `menu_db`;

-- 1. Bảng Categories (Danh mục đồ uống & bánh)
CREATE TABLE IF NOT EXISTS `categories` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `description` TEXT
);

-- 2. Bảng Products (Món / Sản phẩm cà phê)
CREATE TABLE IF NOT EXISTS `products` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `price` DECIMAL(19, 2) NOT NULL,
    `quantity` INT NOT NULL DEFAULT 0,
    `category_id` BIGINT,
    CONSTRAINT `fk_product_category` FOREIGN KEY (`category_id`) REFERENCES `categories` (`id`) ON DELETE SET NULL
);

-- 3. Bảng Product Images (Hình ảnh món)
CREATE TABLE IF NOT EXISTS `product_images` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `product_id` BIGINT NOT NULL,
    `image_url` VARCHAR(500) NOT NULL,
    CONSTRAINT `fk_image_product` FOREIGN KEY (`product_id`) REFERENCES `products` (`id`) ON DELETE CASCADE
);

-- ===================================================
-- Dữ liệu mẫu Quán Cà Phê (Sample Data Coffee Shop)
-- ===================================================
SET FOREIGN_KEY_CHECKS = 0;
TRUNCATE TABLE `product_images`;
TRUNCATE TABLE `products`;
TRUNCATE TABLE `categories`;
SET FOREIGN_KEY_CHECKS = 1;

INSERT INTO `categories` (`name`, `description`) VALUES
('Cà Phê Truyền Thống', 'Cà phê phin truyền thống Việt Nam thơm ngon đậm đà'),
('Cà Phê Pha Máy', 'Espresso, Americano, Cappuccino từ hạt Arabica nguyên chất'),
('Trà & Trà Trái Cây', 'Trà hoa quả giải nhiệt, thanh mát'),
('Đá Xay & Sinh Tố', 'Thức uống đá xay mát lạnh, béo ngậy'),
('Bánh Ngọt & Ăn Kèm', 'Bánh ngọt nướng tươi dùng kèm đồ uống');

INSERT INTO `products` (`name`, `price`, `quantity`, `category_id`) VALUES
-- Cà phê truyền thống
('Cà Phê Đen Đá', 25000.00, 100, 1),
('Cà Phê Sữa Đá', 29000.00, 100, 1),
('Bạc Xỉu Sữa Tươi', 32000.00, 100, 1),
('Cà Phê Muối Huế', 35000.00, 80, 1),

-- Cà phê pha máy
('Espresso Single Shot', 30000.00, 100, 2),
('Americano Đá', 35000.00, 100, 2),
('Cappuccino Nóng', 45000.00, 50, 2),
('Caramel Macchiato', 49000.00, 60, 2),

-- Trà & Trà trái cây
('Trà Đào Cam Sả', 42000.00, 80, 3),
('Trà Vải Hoa Hồng', 42000.00, 70, 3),
('Trà Sen Vàng Macchiato', 45000.00, 80, 3),

-- Đá xay
('Matcha Đá Xay', 52000.00, 50, 4),
('Cà Phê Dừa Đá Xay', 49000.00, 50, 4),

-- Bánh ngọt
('Bánh Croissant Bơ Tỏi', 35000.00, 30, 5),
('Bánh Tiramisu Truyền Thống', 45000.00, 25, 5);

INSERT INTO `product_images` (`product_id`, `image_url`) VALUES
(1, 'https://images.unsplash.com/photo-1514432324607-a09d9b4aefdd?w=600'),
(2, 'https://images.unsplash.com/photo-1517701550927-30cf4ba1dba5?w=600'),
(3, 'https://images.unsplash.com/photo-1582293041079-7814c2f12063?w=600'),
(4, 'https://images.unsplash.com/photo-1541167760496-1628856ab772?w=600'),
(5, 'https://images.unsplash.com/photo-1510591509098-f4fdc6d0ff04?w=600'),
(6, 'https://images.unsplash.com/photo-1551030173-122aabc4489c?w=600'),
(7, 'https://images.unsplash.com/photo-1534778101976-62847782c213?w=600'),
(8, 'https://images.unsplash.com/photo-1485808191679-5f86510681a2?w=600'),
(9, 'https://images.unsplash.com/photo-1556679343-c7306c1976bc?w=600'),
(10, 'https://images.unsplash.com/photo-1576092768241-dec231879fc3?w=600'),
(11, 'https://images.unsplash.com/photo-1597481499750-3e6b22637e12?w=600'),
(12, 'https://images.unsplash.com/photo-1536256263959-770b48d82b0a?w=600'),
(13, 'https://images.unsplash.com/photo-1572490122747-3968b75cc699?w=600'),
(14, 'https://images.unsplash.com/photo-1555507036-ab1f4038808a?w=600'),
(15, 'https://images.unsplash.com/photo-1571877227200-a0d98ea607e9?w=600');
