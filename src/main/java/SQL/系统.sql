-- 系统分类
-- 知识库   我分享的   阅读过  未读过的
-- 日志   写日志   （通过   审核中   未通过   还未审核）过去   审核
-- 任务   发布   审核   查看
-- 公告  发布   查看
-- 登录注册、个人用户   部门   级别    电话

-- 系统管理  管理员  管理系统--用户、部门、角色、功能、

-- DROP DATABASE IF EXISTS `db_affairmanage`;
-- CREATE DATABASE `db_affairmanage` /*!40100 DEFAULT CHARACTER SET gb2312 */;
-- USE `db_affairmanage`;

DROP DATABASE IF EXISTS `eb`;
CREATE DATABASE `eb` /*!40100 DEFAULT CHARACTER SET gb2312 */;
USE `eb`;

CREATE TABLE `demo_user` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` varchar(20) DEFAULT NULL COMMENT '用户ID',
  `username` varchar(64) DEFAULT NULL COMMENT '用户名',
  `status` varchar(32) DEFAULT NULL COMMENT '商户状态:\r\nNORMAL:正常\r\nCHECKING:审核中 \r\nFROZEN:冻结\r\nEXPIRED:已到期\r\nDELIST:已解约',
  `phone` varchar(20) DEFAULT NULL COMMENT '联系人电话',
  `email` varchar(64) DEFAULT NULL COMMENT '联系人邮箱',
  `raw_add_time` datetime DEFAULT NULL,
  `raw_update_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `uk_username` (`username`),
  UNIQUE KEY `uk_user_id` (`user_id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

CREATE TABLE `sys_seq` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '序列名称',
  `raw_add_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=41 DEFAULT CHARSET=utf8;

-- CREATE TABLE tb_enterprise(
-- id INT(11) PRIMARY KEY AUTO_INCREMENT,
-- name VARCHAR(50) NOT NULL DEFAULT '..公司' COMMENT '名称',
-- market INT(1) NOT NULL DEFAULT '0' COMMENT '上市-0：未;1：上市',
-- market_value FLOAT NOT NULL DEFAULT '0.0' COMMENT '市值-单位：万元',
-- address VARCHAR(50) NOT NULL DEFAULT '重庆' COMMENT '地址',
-- phone VARCHAR(50) NOT NULL DEFAULT '18380805107' COMMENT '电话',
-- eid INT(11) NOT NULL COMMENT '所属',
-- ename VARCHAR(50) NOT NULL DEFAULT '..公司' COMMENT '所属公司名称',
-- remark text DEFAULT NULL COMMENT '描述',
-- creator INT(11) NOT NULL COMMENT '创建人',
-- updater INT(11) NOT NULL COMMENT '修改人',
-- create_time datetime NOT NULL COMMENT '创建时间',
-- update_time datetime NOT NULL COMMENT '更新时间'
-- )ENGINE=InnoDB AUTO_INCREMENT=101 DEFAULT CHARSET=gb2312 COMMENT='公司表';



CREATE TABLE tb_department(
id INT(11) PRIMARY KEY auto_increment,
name VARCHAR(50) NOT NULL DEFAULT '..部门' COMMENT '名称',
-- eid INT(11) NOT NULL COMMENT '所属公司',
-- ename VARCHAR(50) NOT NULL DEFAULT '..公司' COMMENT '所属公司名称',
did INT(11) NOT NULL COMMENT '所属部门id',
dname VARCHAR(50) NOT NULL DEFAULT '..部门' COMMENT '所属部门名称',
remark text DEFAULT NULL COMMENT '描述',
creator INT(11) NOT NULL COMMENT '创建人',
updater INT(11) NOT NULL COMMENT '修改人',
create_time datetime NOT NULL COMMENT '创建时间',
update_time datetime NOT NULL COMMENT '更新时间'
)ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=gb2312 COMMENT='部门表';



CREATE TABLE tb_employee(
id INT(11) PRIMARY KEY auto_increment,
name VARCHAR(50)   COMMENT '名称',
phone varchar(20) NOT NULL COMMENT '电话',
address VARCHAR(50) COMMENT '地址',
-- eid INT(11) NOT NULL COMMENT '所属公司',
-- ename VARCHAR(50) NOT NULL DEFAULT '..公司' COMMENT '所属公司名称',
did INT(11) NOT NULL COMMENT '所属部门id',
dname VARCHAR(50) NOT NULL DEFAULT '..部门' COMMENT '所属部门名称',
remark text DEFAULT NULL COMMENT '描述',
creator INT(11) NOT NULL COMMENT '创建人',
updater INT(11) NOT NULL COMMENT '修改人',
create_time datetime NOT NULL COMMENT '创建时间',
update_time datetime NOT NULL COMMENT '更新时间'
)ENGINE=InnoDB AUTO_INCREMENT=1000001 DEFAULT CHARSET=gb2312 COMMENT='用户表';


CREATE TABLE tb_login(
id INT(11) PRIMARY KEY auto_increment,
phone varchar(20) NOT NULL COMMENT '电话',
password VARCHAR(20) NOT NULL COMMENT '密码',
last_login_time datetime NOT NULL COMMENT '最后登录时间',
last_login_address VARCHAR(50) NOT NULL COMMENT '最后登录地址',
login_number INT NOT NULL COMMENT '登录次数'
)ENGINE=InnoDB AUTO_INCREMENT=100000001 DEFAULT CHARSET=gb2312 COMMENT='登录记录表';

CREATE TABLE tb_employee_role(
id INT(11) PRIMARY KEY auto_increment,
-- eid INT(11) NOT NULL COMMENT '员工id',
-- ename VARCHAR(50)   COMMENT '员工名称',
rid INT(11) NOT NULL COMMENT '员工id',
rname VARCHAR(50)   COMMENT ' 角色名称',
remark text DEFAULT NULL COMMENT '描述',
creator INT(11) NOT NULL COMMENT '创建人',
updater INT(11) NOT NULL COMMENT '修改人',
create_time datetime NOT NULL COMMENT '创建时间',
update_time datetime NOT NULL COMMENT '更新时间'
)ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=gb2312 COMMENT='员工角色';

CREATE TABLE tb_role(
id INT(11) PRIMARY KEY auto_increment,
name VARCHAR(50)   COMMENT '名称',
func VARCHAR(50) COMMENT '职能',
level INT NOT NULL COMMENT '角色的级别0-1-2-3....',
-- eid INT(11) NOT NULL COMMENT '所属公司',
-- ename VARCHAR(50) NOT NULL DEFAULT '..公司' COMMENT '所属公司名称',
did INT(11) NOT NULL COMMENT '所属部门id',
dname VARCHAR(50) NOT NULL DEFAULT '..部门' COMMENT '所属部门名称',
remark text DEFAULT NULL COMMENT '描述',
creator INT(11) NOT NULL COMMENT '创建人',
updater INT(11) NOT NULL COMMENT '修改人',
create_time datetime NOT NULL COMMENT '创建时间',
update_time datetime NOT NULL COMMENT '更新时间'
)ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=gb2312 COMMENT='角色';


CREATE TABLE tb_role_action(
id INT(11) PRIMARY KEY auto_increment,
aid INT(11) NOT NULL COMMENT '功能id',
aname VARCHAR(50)   COMMENT '功能名称',
rid INT(11) NOT NULL COMMENT '员工id',
rname VARCHAR(50)   COMMENT ' 角色名称',
remark text DEFAULT NULL COMMENT '描述',
creator INT(11) NOT NULL COMMENT '创建人',
updater INT(11) NOT NULL COMMENT '修改人',
create_time datetime NOT NULL COMMENT '创建时间',
update_time datetime NOT NULL COMMENT '更新时间'
)ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=gb2312 COMMENT='角色功能';


CREATE TABLE tb_action(
id INT(11) PRIMARY KEY auto_increment,
name VARCHAR(50)   COMMENT '名称',
url VARCHAR(50) COMMENT '链接',
aid INT(11) NOT NULL COMMENT '所属',
aname VARCHAR(50)   COMMENT '所属功能名称',
remark text DEFAULT NULL COMMENT '描述',
creator INT(11) NOT NULL COMMENT '创建人',
updater INT(11) NOT NULL COMMENT '修改人',
create_time datetime NOT NULL COMMENT '创建时间',
update_time datetime NOT NULL COMMENT '更新时间'
)ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=gb2312 COMMENT='功能';


CREATE TABLE tb_repository(
id INT(11) PRIMARY KEY auto_increment,
name VARCHAR(50)   COMMENT '名称',
url VARCHAR(50) COMMENT '链接',
look_number INT NOT NULL DEFAULT '0' COMMENT '查看次数',
remark text DEFAULT NULL COMMENT '描述',
creator INT(11) NOT NULL COMMENT '创建人',
updater INT(11) NOT NULL COMMENT '修改人',
create_time datetime NOT NULL COMMENT '创建时间',
update_time datetime NOT NULL COMMENT '更新时间'
)ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=gb2312 COMMENT='知识库';


CREATE TABLE tb_look_repository(
id INT(11) PRIMARY KEY auto_increment,
rid INT(11) NOT NULL COMMENT '查看的知识库链接id',
-- eid INT(11) NOT NULL COMMENT '查看人id',
-- ename VARCHAR(50) NOT NULL COMMENT '查看人名字',
remark text DEFAULT NULL COMMENT '描述',
creator INT(11) NOT NULL COMMENT '创建人',
updater INT(11) NOT NULL COMMENT '修改人',
create_time datetime NOT NULL COMMENT '创建时间',
update_time datetime NOT NULL COMMENT '更新时间'
)ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=gb2312 COMMENT='查看知识链接的记录';


CREATE TABLE tb_notice(
id INT(11) PRIMARY KEY auto_increment,
name VARCHAR(50)   COMMENT '名称',
content text not NULL COMMENT '内容',
look_number INT NOT NULL DEFAULT '0' COMMENT '查看次数',
category int NOT NULL DEFAULT '0' COMMENT '0:内部，1：外部',
remark text DEFAULT NULL COMMENT '描述',
creator INT(11) NOT NULL COMMENT '创建人',
updater INT(11) NOT NULL COMMENT '修改人',
create_time datetime NOT NULL COMMENT '创建时间',
update_time datetime NOT NULL COMMENT '更新时间'
)ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=gb2312 COMMENT='公告';


CREATE TABLE tb_look_notice(
id INT(11) PRIMARY KEY auto_increment,
rid INT(11) NOT NULL COMMENT '查看的公告id',
-- eid INT(11) NOT NULL COMMENT '查看人id',
-- ename VARCHAR(50) NOT NULL COMMENT '查看人名字',
remark text DEFAULT NULL COMMENT '描述',
creator INT(11) NOT NULL COMMENT '创建人',
updater INT(11) NOT NULL COMMENT '修改人',
create_time datetime NOT NULL  COMMENT '创建时间',
update_time datetime NOT NULL  COMMENT '更新时间'
)ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=gb2312 COMMENT='查看公告的记录';


CREATE TABLE tb_task(
id INT(11) PRIMARY KEY auto_increment,
name VARCHAR(50)   COMMENT '名称',
content text not NULL COMMENT '内容',
-- eid INT(11) NOT NULL COMMENT '完成任务人id',
-- ename VARCHAR(50) NOT NULL COMMENT '完成任务人名字',
begin_time datetime NOT NULL  COMMENT '开始时间',
end_time datetime NOT NULL  COMMENT '完成时间',
state INT NOT NULL DEFAULT '0' COMMENT '状态-0：未开始，1：进行中，2：结束，3：关闭',
expect_time INT NOT NULL DEFAULT '0' COMMENT '预计需要时间',
actual_time INT NOT NULL DEFAULT '0' COMMENT '实际花费时间',
category int NOT NULL DEFAULT '0' COMMENT '是需要上级审核，还是下级完成；0：完成，1：审核',
remark text DEFAULT NULL COMMENT '描述',
creator INT(11) NOT NULL COMMENT '创建人',
updater INT(11) NOT NULL COMMENT '修改人',
create_time datetime NOT NULL  COMMENT '创建时间',
update_time datetime NOT NULL  COMMENT '更新时间'
)ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=gb2312 COMMENT='任务';


CREATE TABLE tb_log(
id INT(11) PRIMARY KEY auto_increment,
name VARCHAR(50)   COMMENT '名称',
content text not NULL COMMENT '内容',
log_number varchar(50) NOT NULL COMMENT '日志编号，逗号分隔',
check_user INT(11) NOT NULL COMMENT '审核人',
check_username VARCHAR(50) NOT NULL COMMENT '审核人名称',
check_time datetime COMMENT '审核时间',
state INT NOT NULL DEFAULT '0' COMMENT '审核通过与否-0：未通过，1：通过了',
remark text DEFAULT NULL COMMENT '描述',
creator INT(11) NOT NULL COMMENT '创建人',
updater INT(11) NOT NULL COMMENT '修改人',
create_time datetime NOT NULL  COMMENT '创建时间',
update_time datetime NOT NULL  COMMENT '更新时间'
)ENGINE=InnoDB AUTO_INCREMENT=10001 DEFAULT CHARSET=gb2312 COMMENT='日志';














