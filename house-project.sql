-- MySQL schema + seed data for House-Project
SET NAMES utf8mb4;

CREATE DATABASE IF NOT EXISTS `house-project`
  DEFAULT CHARACTER SET utf8mb4
  COLLATE utf8mb4_general_ci;

USE `house-project`;

CREATE TABLE IF NOT EXISTS `user` (
  `id` INT AUTO_INCREMENT PRIMARY KEY,
  `username` VARCHAR(50) NOT NULL UNIQUE,
  `password` VARCHAR(100) NOT NULL,
  `dengji` INT NOT NULL DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

CREATE TABLE IF NOT EXISTS `fangzhi` (
  `hid` INT AUTO_INCREMENT PRIMARY KEY,
  `dizhi` VARCHAR(255) NOT NULL,
  `mianji` INT NOT NULL,
  `jiage` INT NOT NULL,
  `tupian` VARCHAR(255),
  `zhuangtai` VARCHAR(50)
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4;

-- Seed users (admin + demo)
INSERT INTO `user` (`username`, `password`, `dengji`) VALUES
  ('admin', '123456', 1),
  ('demo', '123456', 0)
ON DUPLICATE KEY UPDATE
  `password` = VALUES(`password`),
  `dengji` = VALUES(`dengji`);

-- Seed houses
INSERT INTO `fangzhi` (`dizhi`, `mianji`, `jiage`, `tupian`, `zhuangtai`) VALUES
  ('No.1 River Rd', 88, 12000, '/images/tu.jpg', '售卖中'),
  ('No.12 Lake Ave', 120, 15500, '/images/tu.jpg', '售卖中'),
  ('No.8 Hill St', 75, 9800, '/images/tu.jpg', '出租中'),
  ('No.66 Garden Blvd', 142, 21000, '/images/tu.jpg', '售卖中'),
  ('No.23 Pine Ln', 60, 7200, '/images/tu.jpg', '出租中'),
  ('No.9 Downtown Rd', 95, 13500, '/images/tu.jpg', '已售出');

