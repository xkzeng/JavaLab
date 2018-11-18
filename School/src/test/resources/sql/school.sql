
#MySQL
CREATE DATABASE IF NOT EXISTS `school`;

DROP TABLE IF EXISTS `publishers`;
CREATE TABLE IF NOT EXISTS `publishers` (
	`id` INT(10) UNSIGNED NOT NULL COMMENT '出版社编号',
	`name` VARCHAR(32) NOT NULL COMMENT '出版社名称',
	PRIMARY KEY `pk_publisherId` (`id`),
	FULLTEXT INDEX `ft_index_name` (`name`)
)
COMMENT='出版社信息表'
ENGINE=MyISAM;

DROP TABLE IF EXISTS `books`;
CREATE TABLE IF NOT EXISTS `books` (
	`id` INT(10) UNSIGNED NOT NULL COMMENT '书籍编号',
	`name` VARCHAR(128) NOT NULL COMMENT '书籍名称',
	`author` VARCHAR(64) NULL DEFAULT NULL COMMENT '书籍作者',
  `publisherId` INT(10) UNSIGNED NULL DEFAULT '0' COMMENT '出版社编号',
	PRIMARY KEY `pk_bookId` (`id`),
	FULLTEXT INDEX `ft_index_name` (`name`)
)
COMMENT='图书信息表'
COLLATE='utf8mb4_general_ci'
ENGINE=MyISAM;

DROP TABLE IF EXISTS `teachers`;
CREATE TABLE IF NOT EXISTS `teachers` (
	`id` INT(10) UNSIGNED NOT NULL COMMENT '教师编号',
	`name` VARCHAR(32) NOT NULL COMMENT '教师姓名',
	`entertime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入职时间',
	`leavetime` DATETIME NULL DEFAULT NULL COMMENT '离职时间',
	PRIMARY KEY `pk_id` (`id`),
	FULLTEXT INDEX `ft_index_name` (`name`)
)
COMMENT='教师信息表'
COLLATE='utf8mb4_general_ci'
ENGINE=MyISAM;

DROP TABLE IF EXISTS `master_teachers`;
CREATE TABLE IF NOT EXISTS `master_teachers` (
	`id` INT(10) UNSIGNED NOT NULL COMMENT '班主任编号',
	`name` VARCHAR(32) NOT NULL COMMENT '班主任姓名',
	`entertime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入职时间',
	`leavetime` DATETIME NULL DEFAULT NULL COMMENT '离职时间',
	PRIMARY KEY `pk_id` (`id`),
	FULLTEXT INDEX `ft_index_name` (`name`)
)
COMMENT='班主任信息表'
COLLATE='utf8mb4_general_ci'
ENGINE=MyISAM;

DROP TABLE IF EXISTS `subjects`;
CREATE TABLE IF NOT EXISTS `subjects` (
	`id` INT(10) UNSIGNED NOT NULL COMMENT '课程编号',
	`name` VARCHAR(32) NULL DEFAULT NULL COMMENT '课程名称',
	`entertime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开课时间',
	`leavetime` DATETIME NULL DEFAULT NULL COMMENT '停课时间',
	PRIMARY KEY `pk_id` (`id`),
	FULLTEXT INDEX `ft_index_name` (`name`)
)
COMMENT='课程信息表'
COLLATE='utf8mb4_general_ci'
ENGINE=MyISAM;

DROP TABLE IF EXISTS `classess`;
CREATE TABLE IF NOT EXISTS `classess` (
  `id` SMALLINT(3) UNSIGNED NOT NULL COMMENT '班级编号',
	`name` VARCHAR(32) NOT NULL COMMENT '班级名称',
  `masterteacherId` INT(10) UNSIGNED NULL DEFAULT '0' COMMENT '该班级的班主任编号',
	`entertime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '开设时间',
	`leavetime` DATETIME NULL DEFAULT NULL COMMENT '关停时间',
	PRIMARY KEY `pk_id` (`id`),
	FULLTEXT INDEX `ft_index_name` (`name`)
)
COMMENT='班级信息表'
COLLATE='utf8mb4_general_ci'
ENGINE=MyISAM;

DROP TABLE IF EXISTS `students`;
CREATE TABLE IF NOT EXISTS `students` (
	`id` INT(10) UNSIGNED NOT NULL COMMENT '学号',
	`name` VARCHAR(32) NOT NULL COMMENT '姓名',
	`sex` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0' COMMENT '性别;1-男;0-女;',
	`age` TINYINT(2) UNSIGNED NOT NULL DEFAULT '0' COMMENT '年龄',
  `classesId` SMALLINT(3) UNSIGNED NULL DEFAULT '0' COMMENT '所属班级的编号',
	`entertime` DATETIME NOT NULL DEFAULT CURRENT_TIMESTAMP COMMENT '入校时间',
	`leavetime` DATETIME NULL DEFAULT NULL COMMENT '毕业时间',
	PRIMARY KEY `pk_id` (`id`),
	FULLTEXT INDEX `ft_index_name` (`name`)
)
COMMENT='学生信息表'
COLLATE='utf8mb4_general_ci'
ENGINE=MyISAM;

DROP TABLE IF EXISTS`students_subjects`;
CREATE TABLE IF NOT EXISTS `students_subjects` (
  `id` INT(10) UNSIGNED NOT NULL AUTO_INCREMENT COMMENT '记录序号',
	`studentId` INT(10) UNSIGNED NOT NULL COMMENT '学生编号',
	`subjectId` INT(10) UNSIGNED NOT NULL COMMENT '课程编号',
  PRIMARY KEY `pk_id` (`id`),
	UNIQUE INDEX `uk_studentId_subjectId` (`studentId`, `subjectId`)
)
COMMENT='学生选课信息'
COLLATE='utf8mb4_general_ci'
ENGINE=MyISAM;


INSERT INTO students_subjects(studentId, subjectId) VALUES(18010001, 2001),(18010001, 2002),(18010001, 2003);
INSERT INTO students_subjects(studentId, subjectId) VALUES(18010002, 2007),(18010002, 2008),(18010002, 2009);
INSERT INTO students_subjects(studentId, subjectId) VALUES(18010003,2013),(18010003,2014),(18010003,2015),(18010003, 2016),(18010003, 2017),(18010003, 2018),(18010003,2022);


### 班级编号规则 ###
#幼儿园#
小班  : start = 010, step = 1; 11-小一班;12-小二班; ...
中班  : start = 020, step = 1; 21-中一班;22-中二班; ...
大班  : start = 030, step = 1; 31-大一班;32-大二班; ...

一年级: start = 100, step = 1; 101-1年级1班; 102-1年级2班; ...
二年级: start = 200, step = 1; 201-2年级1班; 202-2年级2班; ...
三年级: start = 300, step = 1; 301-3年级1班; 302-3年级2班; ...
四年级: start = 400, step = 1; 401-4年级1班; 402-4年级2班; ...
五年级: start = 500, step = 1; 501-5年级1班; 502-5年级2班; ...
六年级: start = 600, step = 1; 601-6年级1班; 602-6年级2班; ...
七年级: start = 700, step = 1; 701-7年级1班; 702-7年级2班; ...
八年级: start = 800, step = 1; 801-8年级1班; 802-8年级2班; ...
九年级: start = 900, step = 1; 901-9年级1班; 902-9年级2班; ...


### 教师编号规则 ###
YYYYMM + 4位序号
其中,
YYYY: 入职年份,四位数字,取值范围是[2018,9999]; 比如,2018年入职的,YYYY就取2018;
MM  : 入职月份,两位数字;取值范围是[01,12]
DD  : 入职日子,两位数字;取值范围是[01,31]
序号: 4位的序号;取值范围是[0000,9999]

### 班主任编号规则 ###
1YYYYMM + 3位序号
其中,
YYYY: 入职年份,四位数字,取值范围是[2018,9999]; 比如,2018年入职的,YYYY就取2018;
MM  : 入职月份,两位数字;取值范围是[01,12]
序号: 3位的序号;取值范围是[000,999]

### 出版社编号规则 ###
1000 - 1999

### 课程编号规则 ###
2000 - 2999

### 图书编号规则 ###
1000000 - 9999999

### 学号规则 ###
18(两位年份) 01(秋季) 1234(四位报名序号) #2018届秋季第1234位学生
18(两位年份) 02(春季) 1234(四位报名序号) #2018届春季第1234位学生

SELECT
  stu.id AS studentId, stu.name AS studentName, stu.sex AS studentSex, stu.age AS studentAge,
	sub.id AS subjectId, sub.name AS subjectName
FROM students AS stu
LEFT OUTER JOIN students_subjects AS ss ON ss.studentId = stu.id
LEFT OUTER JOIN subjects AS sub ON ss.subjectId = sub.id;



---------------------------------------------------------------------------------------------------------------------------------------
SELECT
  stu.id AS studentId, stu.name AS studentName, stu.sex AS studentSex, stu.age AS studentAge,
	sub.id AS subjectId, sub.name AS subjectName
FROM students AS stu
LEFT OUTER JOIN students_subjects AS ss ON ss.studentId = stu.id
LEFT OUTER JOIN subjects AS sub ON ss.subjectId = sub.id;

DELETE FROM students_subjects;

INSERT INTO students_subjects(studentId, subjectId) VALUES(18010001, 2001),(18010001, 2002),(18010001, 2003);
INSERT INTO students_subjects(studentId, subjectId) VALUES(18010002, 2007),(18010002, 2008),(18010002, 2009);
INSERT INTO students_subjects(studentId, subjectId) VALUES(18010003,2013),(18010003,2014),(18010003,2015),(18010003, 2016),(18010003, 2017),(18010003, 2018),(18010003,2022);


SELECT
  stu.id AS studentId, stu.name AS studentName, stu.sex AS studentSex, stu.age AS studentAge,
	sub.id AS subjectId, sub.name AS subjectName
FROM subjects AS sub
LEFT OUTER JOIN students_subjects AS ss ON ss.subjectId = sub.id
LEFT OUTER JOIN students AS stu ON ss.studentId = stu.id;

SELECT
  stu.id AS studentId, stu.name AS studentName, stu.sex AS studentSex, stu.age AS studentAge,
	sub.id AS subjectId, sub.name AS subjectName
FROM students AS stu
RIGHT OUTER JOIN students_subjects AS ss ON ss.studentId = stu.id
RIGHT OUTER JOIN subjects AS sub ON ss.subjectId = sub.id;



INSERT INTO books(bookId, bookName, author) VALUES(1000001, 'C++高级编程', '钱学森');
INSERT INTO books(bookId, bookName, author) VALUES(1000002, 'C++游戏编程', '毛泽东');
INSERT INTO books(bookId, bookName, author) VALUES(1000003, 'JavaWeb开发', '杨开慧');
INSERT INTO books(bookId, bookName, author) VALUES(1000004, '软件工程师', '朱元璋');
INSERT INTO books(bookId, bookName, author) VALUES(1000005, '大数据工程师', '李时珍');
INSERT INTO books(bookId, bookName, author) VALUES(1000006, 'Python工程师', '乾隆皇帝');

[mysqld]
validate_password.check_user_name = OFF
validate_password.length = 1
validate_password.mixed_case_count = 0
validate_password.number_count = 0
validate_password.policy = LOW
validate_password.special_char_count = 0

#SQLite
CREATE TABLE IF NOT EXISTS `books` (
	`bookId` INT(10) NOT NULL,
	`bookName` VARCHAR(128) NOT NULL,
	`author` VARCHAR(64) NULL DEFAULT NULL,
	PRIMARY KEY (`bookId`)
);
