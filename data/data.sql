SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


DROP DATABASE IF EXISTS spring_ecommerce;
CREATE DATABASE spring_ecommerce;
USE spring_ecommerce;



-- Create tables ----------------------------------------------------------------------

CREATE TABLE `brand` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,

  PRIMARY KEY (`id`)
);


CREATE TABLE `category` (
  `id` bigint(20) NOT NULL,
  `name` varchar(255) DEFAULT NULL,

  PRIMARY KEY (`id`)
);


CREATE TABLE `product` (
  `id` bigint(20) NOT NULL,
  `color` varchar(255) DEFAULT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `brand_id` bigint(20) DEFAULT NULL,
  `category_id` bigint(20) DEFAULT NULL,

  PRIMARY KEY (`id`)
);



CREATE TABLE `customer` (
  `id` bigint(20) NOT NULL,
  `address` varchar(255) DEFAULT NULL,
  `email` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `phone` varchar(255) DEFAULT NULL,

  PRIMARY KEY (`id`)
);


CREATE TABLE `order` (
  `id` bigint(20) NOT NULL,
  `date` datetime DEFAULT NULL,
  `total` double DEFAULT NULL,
  `customer_id` bigint(20) DEFAULT NULL,

  PRIMARY KEY (`id`)
);


CREATE TABLE `order_item` (
  `id` bigint(20) NOT NULL,
  `price` double DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `order_id` bigint(20) DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,

  PRIMARY KEY (`id`)
);


CREATE TABLE `user` (
  `id` bigint(20) NOT NULL,
  `password` varchar(255) DEFAULT NULL,
  `username` varchar(255) DEFAULT NULL,
  `customer_id` bigint(20) NOT NULL,

  PRIMARY KEY (`id`)
);


CREATE TABLE `cart` (
  `id` bigint(20) NOT NULL,
  `image` varchar(255) DEFAULT NULL,
  `name` varchar(255) DEFAULT NULL,
  `price` double DEFAULT NULL,
  `product_id` bigint(20) DEFAULT NULL,
  `quantity` int(11) DEFAULT NULL,
  `total` double DEFAULT NULL,
  `user_id` bigint(20) DEFAULT NULL,

  PRIMARY KEY (`id`)
);


-- Insert data into tables ---------------------------------------------------------------------------------------------


INSERT INTO `brand` (`id`, `name`) VALUES
(1, 'Adidas'),
(2, 'Channel'),
(3, 'Puma');



INSERT INTO `category` (`id`, `name`) VALUES
(1, 'Clothing'),
(2, 'Shoes'),
(3, 'HandBag');



INSERT INTO `customer` (`id`, `address`, `email`, `name`, `phone`) VALUES
(1, '1241 Tan Phong, Quan 7', 'sa@gmail.com', 'king lord', '124214144'),
(3, '88 Tan Phong, Quan 7', 'user2@gmail.com', 'ho hads adsf', '0942211156');



INSERT INTO `order` (`id`, `date`, `total`, `customer_id`) VALUES
(12, '2023-03-31 20:23:51', 139.97, 3),
(13, '2023-03-31 20:35:38', 139.97, 3),
(14, '2023-03-31 20:46:26', 139.97, 3),
(15, '2023-03-31 21:53:01', 719.8399999999999, 3),
(16, '2023-04-01 14:11:30', 99.94999999999999, 3),
(17, '2023-04-01 21:48:21', 39.99, 3),
(18, '2023-04-01 21:48:37', NULL, 3),
(19, '2023-04-01 21:50:14', 19.99, 3),
(20, '2023-04-01 21:52:55', 39.99, 3),
(21, '2023-04-01 21:59:15', 119.97, 3),
(22, '2023-04-01 22:12:10', 39.99, 3),
(23, '2023-04-01 22:12:16', NULL, 3),
(24, '2023-04-01 22:12:18', NULL, 3),
(25, '2023-04-01 22:12:21', NULL, 3),
(26, '2023-04-01 22:16:01', NULL, 3),
(27, '2023-04-01 22:23:36', 39.99, 3),
(28, '2023-04-02 18:45:44', 299.95, 3),
(29, '2023-04-02 19:22:20', NULL, 3),
(30, '2023-04-02 19:23:33', 359.94, 3);



INSERT INTO `order_item` (`id`, `price`, `quantity`, `order_id`, `product_id`) VALUES
(7, 59.99, 2, 12, 2),
(8, 19.99, 1, 12, 1),
(9, 59.99, 2, 13, 2),
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
(1, '123456', 'user1', 1),
(3, '123456', 'admin', 3);




-- Add auto-increment to tables's id -----------------------------------------------------------------------------------------------------------


ALTER TABLE `brand`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;


ALTER TABLE `cart`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;


ALTER TABLE `category`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;


ALTER TABLE `customer`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;


ALTER TABLE `order`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=31;


ALTER TABLE `order_item`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=25;


ALTER TABLE `product`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=7;


ALTER TABLE `user`
  MODIFY `id` bigint(20) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=17;



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





