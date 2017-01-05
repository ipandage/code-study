-- --------------------------------------------------------
-- 主机:                           127.0.0.1
-- 服务器版本:                        5.5.33 - MySQL Community Server (GPL)
-- 服务器操作系统:                      Win64
-- HeidiSQL 版本:                  7.0.0.4390
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;

-- 导出 tatabangcms 的数据库结构
CREATE DATABASE IF NOT EXISTS `tatabangcms` /*!40100 DEFAULT CHARACTER SET utf8 COLLATE utf8_bin */;
USE `tatabangcms`;


-- 导出  表 tatabangcms.test 结构
CREATE TABLE IF NOT EXISTS `test` (
  `orderId` int(10) DEFAULT NULL,
  `region` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `sales` varchar(50) COLLATE utf8_bin DEFAULT NULL,
  `total` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;

-- 正在导出表  tatabangcms.test 的数据：~0 rows (大约)
DELETE FROM `test`;
/*!40000 ALTER TABLE `test` DISABLE KEYS */;
INSERT INTO `test` (`orderId`, `region`, `sales`, `total`) VALUES
	(9, 'B', '王六', 900000),
	(8, 'B', '钱七', 800000),
	(7, 'B', '王六', 1000000),
	(6, 'A', '张三', 500000),
	(5, 'C', '赵五', 1500000),
	(4, 'A', '张三', 1500000),
	(3, 'C', '赵五', 2000000),
	(2, 'A', '李四', 900000),
	(1, 'A', '张三', 1000000);
/*!40000 ALTER TABLE `test` ENABLE KEYS */;
/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
