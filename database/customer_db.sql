-- ===================================================
-- DATABASE FOR SERVICE 3 (Customer Service) - MySQL
-- Database Name: customer_db
-- ===================================================

CREATE DATABASE IF NOT EXISTS `customer_db` 
CHARACTER SET utf8mb4 
COLLATE utf8mb4_unicode_ci;

USE `customer_db`;

-- 1. Bảng Customers (Khách hàng)
CREATE TABLE IF NOT EXISTS `customers` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `name` VARCHAR(255) NOT NULL,
    `email` VARCHAR(255) NOT NULL UNIQUE,
    `phone` VARCHAR(20),
    `address` TEXT
);

-- 2. Bảng Customer Addresses (Địa chỉ giao hàng)
CREATE TABLE IF NOT EXISTS `customer_addresses` (
    `id` BIGINT AUTO_INCREMENT PRIMARY KEY,
    `customer_id` BIGINT NOT NULL,
    `recipient_name` VARCHAR(255) NOT NULL,
    `phone` VARCHAR(20) NOT NULL,
    `street_address` VARCHAR(500) NOT NULL,
    `city` VARCHAR(100),
    `is_default` BOOLEAN DEFAULT FALSE,
    CONSTRAINT `fk_address_customer` FOREIGN KEY (`customer_id`) REFERENCES `customers` (`id`) ON DELETE CASCADE
);

-- ===================================================
-- Dữ liệu mẫu (Sample Data)
-- ===================================================
INSERT INTO `customers` (`name`, `email`, `phone`, `address`) VALUES
('Đào Thành Nhật', 'thanhnhat@gmail.com', '0912345678', 'TP. Hồ Chí Minh'),
('Trần Văn B', 'tranvanb@gmail.com', '0987654321', 'Hà Nội');

INSERT INTO `customer_addresses` (`customer_id`, `recipient_name`, `phone`, `street_address`, `city`, `is_default`) VALUES
(1, 'Đào Thành Nhật', '0912345678', '123 Đường Lê Lợi, Phường Bến Nghé, Quận 1', 'TP. Hồ Chí Minh', TRUE),
(1, 'Văn phòng Nhật', '0912345678', '456 Đường Nguyễn Huệ, Phường Bến Nghé, Quận 1', 'TP. Hồ Chí Minh', FALSE),
(2, 'Trần Văn B', '0987654321', '78 Phố Huế, Hoàn Kiếm', 'Hà Nội', TRUE);
