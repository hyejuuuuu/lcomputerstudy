-- --------------------------------------------------------
-- 호스트:                          127.0.0.1
-- 서버 버전:                        10.5.5-MariaDB - mariadb.org binary distribution
-- 서버 OS:                        Win64
-- HeidiSQL 버전:                  11.0.0.5919
-- --------------------------------------------------------

/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET NAMES utf8 */;
/*!50503 SET NAMES utf8mb4 */;
/*!40014 SET @OLD_FOREIGN_KEY_CHECKS=@@FOREIGN_KEY_CHECKS, FOREIGN_KEY_CHECKS=0 */;
/*!40101 SET @OLD_SQL_MODE=@@SQL_MODE, SQL_MODE='NO_AUTO_VALUE_ON_ZERO' */;


-- mor 데이터베이스 구조 내보내기
CREATE DATABASE IF NOT EXISTS `mor` /*!40100 DEFAULT CHARACTER SET utf8 */;
USE `mor`;

-- 테이블 mor.board 구조 내보내기
CREATE TABLE IF NOT EXISTS `board` (
  `b_idx` int(10) NOT NULL AUTO_INCREMENT,
  `b_title` varchar(255) NOT NULL,
  `b_content` varchar(255) NOT NULL,
  `b_date` varchar(255) NOT NULL,
  `b_writer` varchar(255) NOT NULL,
  `u_idx` int(10) NOT NULL,
  PRIMARY KEY (`b_idx`),
  KEY `board_ibfk_1` (`u_idx`),
  CONSTRAINT `board_ibfk_1` FOREIGN KEY (`u_idx`) REFERENCES `user` (`u_idx`) ON DELETE NO ACTION ON UPDATE NO ACTION
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- 테이블 데이터 mor.board:~0 rows (대략적) 내보내기
/*!40000 ALTER TABLE `board` DISABLE KEYS */;
/*!40000 ALTER TABLE `board` ENABLE KEYS */;

-- 테이블 mor.user 구조 내보내기
CREATE TABLE IF NOT EXISTS `user` (
  `u_idx` int(10) NOT NULL AUTO_INCREMENT,
  `u_id` varchar(255) NOT NULL,
  `u_pw` varchar(255) NOT NULL,
  `u_name` varchar(255) NOT NULL,
  `u_tel` varchar(255) NOT NULL,
  `u_age` int(11) DEFAULT NULL,
  PRIMARY KEY (`u_idx`) USING BTREE
) ENGINE=InnoDB AUTO_INCREMENT=35 DEFAULT CHARSET=utf8;

-- 테이블 데이터 mor.user:~31 rows (대략적) 내보내기
/*!40000 ALTER TABLE `user` DISABLE KEYS */;
INSERT INTO `user` (`u_idx`, `u_id`, `u_pw`, `u_name`, `u_tel`, `u_age`) VALUES
	(1, 'abc', '1234', 'name123', '010-0000-0000', 15),
	(3, 'a', '111', '김땡땡', '010-1111-1111', 20),
	(4, 'b', '222', '김2', '010-2222-2222', 27),
	(6, 'f', '666', '이6', '010-6666-6666', 66),
	(7, 'g', '688', '이8', '010-8888-8888', 66),
	(8, 'r', '202', '김이공', '010-2020-2020', 20),
	(9, 'h', '686', '육팔', '010-6868-6868', 68),
	(10, 'k', '252', '이오', '010-5252-2525', 52),
	(12, 'zzz', 'zzz', 'zzz', '1-1-1', 11),
	(13, 'cnrk', '1234', '추가', '010-8522-5565', 55),
	(14, 'f', '111', 'f', '111-111-111', 25),
	(15, 't', '111', 't', '222-222-2222', 88),
	(16, 'q', '111', 'q', '010-4564-4684', 16),
	(17, 'r', '111', 'r', '010-4553-4566', 75),
	(18, 'p', '111', 'p', '010-5555-8888', 46),
	(19, 'e', '111', 'e', '010-2587-5455', 43),
	(20, 'd', '111', 'd', '010-1556-4565', 95),
	(21, 'g', '111', 'g', '010-1568-5655', 35),
	(22, 'j', '111', 'j', '010-1565-5668', 15),
	(23, 'x', '111', 'x', '010-1556-4532', 65),
	(24, 'l', '111', 'l', '010-1556-5465', 22),
	(25, 'k', '111', 'k', '010-8557-6978', 75),
	(26, 'h', '111', 'h', '010-7165-5646', 17),
	(27, 'g', '111', 'g', '010-8888-5555', 21),
	(28, 'D', '111', 'D', '010-1111-1111', 23),
	(29, 'v', '111', 'v', '010-2555-5555', 20),
	(30, 'B', '111', 'B', '010-1555-6546', 27),
	(31, 'Z', '111', 'Z', '010-5523-5555', 12),
	(32, 'H', '111', 'H', '010-1556-5556', 25),
	(33, 'S', '111', 'S', '010-1155-5555', 26),
	(34, 'F', '111', 'F', '222-2222-2222', 58);
/*!40000 ALTER TABLE `user` ENABLE KEYS */;

/*!40101 SET SQL_MODE=IFNULL(@OLD_SQL_MODE, '') */;
/*!40014 SET FOREIGN_KEY_CHECKS=IF(@OLD_FOREIGN_KEY_CHECKS IS NULL, 1, @OLD_FOREIGN_KEY_CHECKS) */;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
