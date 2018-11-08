
CREATE DATABASE IF NOT EXISTS `book`;

CREATE TABLE IF NOT EXISTS `books` (
	`bookId` INT(10) UNSIGNED NOT NULL COMMENT '书籍编号',
	`bookName` VARCHAR(128) NOT NULL COMMENT '书籍名称',
	`author` VARCHAR(64) NULL DEFAULT NULL COMMENT '书籍的作者',
	PRIMARY KEY (`bookId`),
	UNIQUE INDEX `index_bookId` (`bookId`) USING BTREE
)
COLLATE='utf8mb4_general_ci'
ENGINE=InnoDB;


INSERT INTO books(bookId, bookName, author) VALUES(1, 'C++高级编程', '钱学森');
INSERT INTO books(bookId, bookName, author) VALUES(2, 'C++游戏编程', '毛泽东');
INSERT INTO books(bookId, bookName, author) VALUES(3, 'JavaWeb开发', '杨开慧');
INSERT INTO books(bookId, bookName, author) VALUES(4, '软件工程师', '朱元璋');
INSERT INTO books(bookId, bookName, author) VALUES(5, '大数据工程师', '李时珍');