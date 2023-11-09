SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


DROP DATABASE IF EXISTS spring_commerce;
CREATE DATABASE spring_commerce;
USE spring_commerce;



-- Create tables ----------------------------------------------------------------------

CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL
);


CREATE TABLE `category` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL
);


CREATE TABLE `product` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `color` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL
);



CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL
);


CREATE TABLE `order` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `date` datetime DEFAULT NULL,
  `total` double DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL
);


CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL
);


CREATE TABLE `user` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) NOT NULL
);


CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL PRIMARY KEY AUTO_INCREMENT,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL
);


-- Insert data into tables ---------------------------------------------------------------------------------------------


INSERT INTO `brand` (`id`, `name`) VALUES
(1, 'Apple'),
(2, 'Dell'),
(3, 'Asus');
(4, 'SamSung'),
(5, 'NVIDIA'),
(6, 'Oppo');



INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Phone'),
(2, 'Laptop'),
(3, 'Accessory');
(4, 'Electronic Components');



INSERT INTO `customer` (`id`, `address`, `email`, `name`, `phone`) VALUES
(1, '164 Tan My, Tan Thuan Tay, Quan 7', 'nguyenhiep@gmail.com', 'Nguyen Hiep', '012345678'),
(2, '37/24 Tran Van Khanh, Tan Thuan Dong, Quan 7', 'ng.hiep0822@gmail.com', 'Hiep Dep Trai', '0987654321');



INSERT INTO `order` (`id`, `date`, `total`, `customer_id`) VALUES
(1, '2023-03-31 20:23:51', 133, 1),
(2, '2023-03-31 21:23:56', 14, 1),
(3, '2023-03-31 20:35:38', 5, 2),
(4, '2023-03-31 20:46:26', 90, 2),
(5, '2023-03-31 21:53:01', 712, 1),
(6, '2023-04-01 14:11:30', 90, 2),
(7, '2023-04-01 21:48:21', 394, 1),
(8, '2023-04-01 21:48:37', NULL, 2),
(9, '2023-04-01 21:50:14', 20, 1),
(10, '2023-04-01 21:52:55', 399, 1),




INSERT INTO `order_item` (`id`, `price`, `quantity`, `order_id`, `product_id`) VALUES
(7, 159.99, 2, 12, 2),
(8, 129.99, 1, 12, 1),
(9, 659.99, 2, 13, 2),
(10, 19.99, 1, 13, 1),
(11, 59.99, 2, 14, 2),
(12, 19.99, 1, 14, 1),
(13, 59.99, 10, 15, 2),
(14, 19.99, 6, 15, 1),
(15, 19.99, 5, 16, 1),
(16, 39.99, 1, 17, 3),
(17, 19.99, 1, 19, 1),
(18, 39.99, 1, 20, 3),
(19, 39.99, 1, 21, 6),
(20, 39.99, 2, 21, 3),
(21, 39.99, 1, 22, 3),
(22, 39.99, 1, 27, 6),
(23, 59.99, 5, 28, 2),
(24, 59.99, 6, 30, 2);



INSERT INTO `product` (`id`, `color`, `image`, `name`, `price`, `quantity`, `brand_id`, `category_id`) VALUES
(1, 'Red', 'product7.jpg', 'T-Shirt', 19.99, 26, 1, 1),
(2, 'Blue', 'product8.jpg', 'Sneakers', 59.99, 25, 2, 2),
(3, 'Black', 'product9.jpg', 'Jeans', 39.99, 55, 3, 1),
(4, 'Green', 'product10.jpg', 'Vallet', 39.99, 70, 2, 3),
(5, 'White', 'product11.jpg', 'Underwear', 39.99, 999, 2, 1),
(6, 'White', 'product12.jpg', 'Suite', 39.99, 999, 2, 1);




INSERT INTO `user` (`id`, `password`, `username`, `customer_id`) VALUES
(1, 'admin', 'admin', 1),
(2, 'deptrai', 'hiep', 2);



-- Add constraint foreign key ------------------------------------------------------------------------------------------------------

ALTER TABLE `product`
  ADD CONSTRAINT `product_category` FOREIGN KEY (`category_id`) REFERENCES `category` (`id`),
  ADD CONSTRAINT `product_brand` FOREIGN KEY (`brand_id`) REFERENCES `brand` (`id`);


ALTER TABLE `cart`
  ADD CONSTRAINT `cart_user` FOREIGN KEY (`user_id`) REFERENCES `user` (`id`);


ALTER TABLE `order`
  ADD CONSTRAINT `order_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`);


ALTER TABLE `order_item`
  ADD CONSTRAINT `order_item_product` FOREIGN KEY (`product_id`) REFERENCES `product` (`id`),
  ADD CONSTRAINT `order_item_order` FOREIGN KEY (`order_id`) REFERENCES `order` (`id`);


ALTER TABLE `user`
  ADD CONSTRAINT `user_customer` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`id`);



