/*
Navicat MySQL Data Transfer

Source Server         : localhost
Source Server Version : 50528
Source Host           : localhost:3306
Source Database       : eb

Target Server Type    : MYSQL
Target Server Version : 50528
File Encoding         : 65001

Date: 2017-05-19 11:41:11
*/

SET FOREIGN_KEY_CHECKS=0;
DROP DATABASE IF EXISTS `eb`;
CREATE DATABASE `eb` /*!40100 DEFAULT CHARACTER SET gb2312 */;
USE `eb`;
-- ----------------------------
-- Table structure for demo_user
-- ----------------------------
DROP TABLE IF EXISTS `demo_user`;
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
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of demo_user
-- ----------------------------

-- ----------------------------
-- Table structure for enterprise
-- ----------------------------
DROP TABLE IF EXISTS `enterprise`;
CREATE TABLE `enterprise` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `ename` varchar(200) DEFAULT NULL,
  `userName` varchar(50) DEFAULT NULL,
  `personNumbers` int(11) DEFAULT NULL,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of enterprise
-- ----------------------------

-- ----------------------------
-- Table structure for sys_seq
-- ----------------------------
DROP TABLE IF EXISTS `sys_seq`;
CREATE TABLE `sys_seq` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(32) DEFAULT NULL COMMENT '序列名称',
  `raw_add_time` timestamp NULL DEFAULT CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of sys_seq
-- ----------------------------

-- ----------------------------
-- Table structure for tb_action
-- ----------------------------
DROP TABLE IF EXISTS `tb_action`;
CREATE TABLE `tb_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `url` varchar(50) DEFAULT NULL COMMENT '链接',
  `aid` int(11) NOT NULL COMMENT '所属',
  `aname` varchar(50) DEFAULT NULL COMMENT '所属功能名称',
  `remark` text COMMENT '描述',
  `creator` int(11)  COMMENT '创建人',
  `updater` int(11)  COMMENT '修改人',
  `create_time` datetime  COMMENT '创建时间',
  `update_time` datetime  COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=42 DEFAULT CHARSET=gb2312 COMMENT='功能';

-- ----------------------------
-- Records of tb_action
-- ----------------------------
INSERT INTO `tb_action` VALUES ('1', '知识库', null, '0', null, '1', '10000080', '10000080', '2017-05-04 11:01:48', '2017-05-04 11:01:53');
INSERT INTO `tb_action` VALUES ('2', '日志', null, '0', null, '1', '10000080', '10000080', '2017-05-04 11:02:21', '2017-05-04 11:02:23');
INSERT INTO `tb_action` VALUES ('3', '任务', null, '0', null, '1', '10000080', '10000080', '2017-05-04 11:02:40', '2017-05-04 11:02:43');
INSERT INTO `tb_action` VALUES ('4', '公告', null, '0', null, '1', '10000080', '10000080', '2017-05-04 11:03:20', '2017-05-04 11:03:22');
INSERT INTO `tb_action` VALUES ('5', '系统管理', null, '0', null, '1', '10000080', '10000080', '2017-05-04 11:03:36', '2017-05-04 11:03:38');
INSERT INTO `tb_action` VALUES ('6', '查看分享', 'jsp/seeShare.jsp', '1', '知识库', '2', '10000080', '10000080', '2017-05-04 11:04:50', '2017-05-04 11:04:53');
INSERT INTO `tb_action` VALUES ('7', '删除分享', null, '6', '知识库', '3', '10000080', '10000080', '2017-05-04 11:10:29', '2017-05-04 11:10:31');
INSERT INTO `tb_action` VALUES ('8', '添加分享', null, '6', '知识库', '3', '10000080', '10000080', '2017-05-04 11:11:23', '2017-05-04 11:11:27');
INSERT INTO `tb_action` VALUES ('9', '写日志', 'jsp/addLog.jsp', '2', '日志', '3', '10000080', '10000080', '2017-05-04 11:12:17', '2017-05-04 11:12:19');
INSERT INTO `tb_action` VALUES ('10', '历史日志', 'jsp/seeLog.jsp', '2', '日志', '2', '10000080', '10000080', '2017-05-04 11:12:52', '2017-05-04 11:12:55');
INSERT INTO `tb_action` VALUES ('11', '审核日志', 'jsp/auditLog.jsp', '2', '日志', '2', '10000080', '10000080', '2017-05-04 11:13:15', '2017-05-04 11:13:17');
INSERT INTO `tb_action` VALUES ('12', '发布任务', null, '14', '任务', '3', '10000080', '10000080', '2017-05-04 11:13:55', '2017-05-04 11:14:00');
INSERT INTO `tb_action` VALUES ('13', '审核任务', 'jsp/auditTask.jsp', '3', '任务', '2', '10000080', '10000080', '2017-05-04 11:14:41', '2017-05-04 11:14:44');
INSERT INTO `tb_action` VALUES ('14', '我的任务', 'jsp/seeTask.jsp', '3', '任务', '2', '10000080', '10000080', '2017-05-04 11:16:02', '2017-05-04 11:16:04');
INSERT INTO `tb_action` VALUES ('15', '发布公告', '/addNotice', '16', '公告', '3', '10000080', '10000080', '2017-05-04 11:16:41', '2017-05-04 11:16:43');
INSERT INTO `tb_action` VALUES ('16', '查看公告', 'jsp/seeNotice.jsp', '4', '公告', '2', '10000080', '10000080', '2017-05-04 11:17:13', '2017-05-04 11:17:15');
INSERT INTO `tb_action` VALUES ('17', '用户管理', 'jsp/manageUser.jsp', '5', '系统管理', '2', '10000080', '10000080', '2017-05-04 11:20:04', '2017-05-04 11:20:07');
INSERT INTO `tb_action` VALUES ('18', '角色管理', 'jsp/manageRole.jsp', '5', '系统管理', '2', '10000080', '10000080', '2017-05-04 11:20:26', '2017-05-04 11:20:28');
INSERT INTO `tb_action` VALUES ('19', '功能管理', 'jsp/manageAction.jsp', '5', '系统管理', '2', '10000080', '10000080', '2017-05-04 11:20:53', '2017-05-04 11:20:55');
INSERT INTO `tb_action` VALUES ('20', '部门管理', 'jsp/manageDepartment.jsp', '5', '系统管理', '2', '10000080', '10000080', '2017-05-04 11:21:30', '2017-05-04 11:21:31');
INSERT INTO `tb_action` VALUES ('21', '添加用户', '/addEmp', '17', '用户管理', '3', '10000080', '10000080', '2017-05-04 23:21:08', '2017-05-04 23:21:10');
INSERT INTO `tb_action` VALUES ('22', '删除用户', '/deleteEmp', '17', '用户管理', '3', '10000080', '10000080', '2017-05-04 23:21:40', '2017-05-04 23:21:42');
INSERT INTO `tb_action` VALUES ('23', '修改用户', '/updateEmp', '17', '用户管理', '3', '10000080', '10000080', '2017-05-04 23:22:27', '2017-05-04 23:22:29');
INSERT INTO `tb_action` VALUES ('24', '增加功能', '/addAction', '19', '功能管理', '3', '10000080', '10000080', '2017-05-04 23:22:52', '2017-05-04 23:22:54');
INSERT INTO `tb_action` VALUES ('25', '删除功能', '/deleteAction', '19', '功能管理', '3', '10000080', '10000080', '2017-05-04 23:23:13', '2017-05-04 23:23:15');
INSERT INTO `tb_action` VALUES ('26', '修改功能', '/updateAction', '19', '功能管理', '3', '10000080', '10000080', '2017-05-04 23:23:50', '2017-05-04 23:23:53');
INSERT INTO `tb_action` VALUES ('27', '增加角色', '/addRole', '18', '角色管理', '3', '10000080', '10000080', '2017-05-04 23:24:17', '2017-05-04 23:24:19');
INSERT INTO `tb_action` VALUES ('28', '删除角色', '/deleteRole', '18', '角色管理', '3', '10000080', '10000080', '2017-05-04 23:24:36', '2017-05-04 23:24:38');
INSERT INTO `tb_action` VALUES ('29', '修改角色', '/updateRole', '18', '角色管理', '3', '10000080', '10000080', '2017-05-04 23:24:59', '2017-05-04 23:25:01');
INSERT INTO `tb_action` VALUES ('30', '增加部门', '/addDepartment', '20', '部门管理', '3', '10000080', '10000080', '2017-05-04 23:25:26', '2017-05-04 23:25:28');
INSERT INTO `tb_action` VALUES ('31', '删除部门', '/deleteDepartment', '20', '部门管理', '3', '10000080', '10000080', '2017-05-04 23:25:44', '2017-05-04 23:25:47');
INSERT INTO `tb_action` VALUES ('32', '修改部门', '/updateDepartment', '20', '部门管理', '3', '10000080', '10000080', '2017-05-04 23:26:25', '2017-05-04 23:26:27');
INSERT INTO `tb_action` VALUES ('33', '个人资料', null, '0', '个人资料', '1', '10000080', '10000080', '2017-05-04 23:28:46', '2017-05-04 23:28:49');
INSERT INTO `tb_action` VALUES ('34', '查看个人资料', 'jsp/seeData.jsp', '33', '个人资料', '2', '10000080', '10000080', '2017-05-04 23:29:24', '2017-05-04 23:29:25');
INSERT INTO `tb_action` VALUES ('35', '修改个人资料', '/updateSelfPassword', '34', '查看个人资料', '3', '10000080', '10000080', '2017-05-04 23:29:52', '2017-05-04 23:29:54');
INSERT INTO `tb_action` VALUES ('36', '查看个人资料数据', '/getUserData', '34', '查看个人资料数据', '3', '10000080', '10000080', '2017-05-17 23:35:09', '2017-05-17 23:35:12');
INSERT INTO `tb_action` VALUES ('37', '发送验证码', '/sendSms', '35', '发送验证码', '3', '10000080', '10000080', '2017-05-18 02:40:34', '2017-05-18 02:40:37');
INSERT INTO `tb_action` VALUES ('38', '获取所有用户', '/getAllUserData', '17', null, '3', '10000080', '10000080', '2017-05-19 11:11:32', '2017-05-19 11:11:35');
INSERT INTO `tb_action` VALUES ('39', '获取功能列表', '/getActionData', '19', null, '3', '10000080', '10000080', '2017-05-19 11:12:56', '2017-05-19 11:12:59');
INSERT INTO `tb_action` VALUES ('40', '获取角色列表', '/getRoleData', '18', '获取角色列表', '3', '10000080', '10000080', '2017-05-19 11:14:39', '2017-05-19 11:14:41');
INSERT INTO `tb_action` VALUES ('41', '获取部门列表', '/getDepartmentData', '20', '获取部门列表', '3', '10000080', '10000080', '2017-05-19 11:16:02', '2017-05-19 11:16:04');

-- ----------------------------
-- Table structure for tb_department
-- ----------------------------
DROP TABLE IF EXISTS `tb_department`;
CREATE TABLE `tb_department` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) NOT NULL DEFAULT '..部门' COMMENT '名称',
  `did` int(11) NOT NULL COMMENT '所属部门id',
  `dname` varchar(50) NOT NULL DEFAULT '..部门' COMMENT '所属部门名称',
  `remark` text COMMENT '描述',
  `creator` int(11)  COMMENT '创建人',
  `updater` int(11)  COMMENT '修改人',
  `create_time` datetime  COMMENT '创建时间',
  `update_time` datetime  COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10012 DEFAULT CHARSET=gb2312 COMMENT='部门表';

-- ----------------------------
-- Records of tb_department
-- ----------------------------
INSERT INTO `tb_department` VALUES ('10001', '总裁办', '0', '单独部门', '股东部门', '10000080', '10000080', '2017-04-30 20:47:25', '2017-04-30 20:47:27');
INSERT INTO `tb_department` VALUES ('10002', '技术部', '0', '最高部门', '负责公司线上技术部分', '10000080', '10000080', '2017-04-30 20:51:09', '2017-04-30 20:51:12');
INSERT INTO `tb_department` VALUES ('10003', 'java组', '10002', '技术部', '负责后台借口开发', '10000080', '10000080', '2017-04-30 20:52:05', '2017-04-30 20:52:07');
INSERT INTO `tb_department` VALUES ('10004', 'IOS组', '10002', '技术部', '负责IOS前端APP开发', '10000080', '10000080', '2017-04-30 20:52:53', '2017-04-30 20:52:57');
INSERT INTO `tb_department` VALUES ('10005', 'Android组', '10002', '技术部', '负责Android前端APP开发', '10000080', '10000080', '2017-04-30 20:53:42', '2017-04-30 20:53:45');
INSERT INTO `tb_department` VALUES ('10006', '测试组', '10002', '技术部', '负责后期产品测试', '10000080', '10000080', '2017-04-30 20:54:19', '2017-04-30 20:54:22');
INSERT INTO `tb_department` VALUES ('10007', '产品设计组', '10002', '技术部', '负责前期产品原型设置', '10000080', '10000080', '2017-04-30 20:55:17', '2017-04-30 20:55:19');
INSERT INTO `tb_department` VALUES ('10008', '财务部', '0', '最高部门', '负责公司所有财务问题', '10000080', '10000080', '2017-04-30 20:56:09', '2017-04-30 20:56:12');
INSERT INTO `tb_department` VALUES ('10009', '人事部', '0', '最高部门', '负责所有认识调整安排', '10000080', '10000080', '2017-04-30 20:57:01', '2017-04-30 20:57:03');
INSERT INTO `tb_department` VALUES ('10010', '商城', '0', '最高部门', '负责联系客户，商品售后服务', '10000080', '10000080', '2017-04-30 20:58:08', '2017-04-30 20:58:11');
INSERT INTO `tb_department` VALUES ('10011', '长青', '0', '最高部门', '负责客户体验，以及高品质用户等级提升；商户洽谈等等', '10000080', '10000080', '2017-04-30 20:59:02', '2017-04-30 20:59:04');

-- ----------------------------
-- Table structure for tb_employee
-- ----------------------------
DROP TABLE IF EXISTS `tb_employee`;
CREATE TABLE `tb_employee` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `phone` varchar(20) NOT NULL COMMENT '电话',
  `address` varchar(50) DEFAULT NULL COMMENT '地址',
  `did` int(11) NOT NULL COMMENT '所属部门id',
  `dname` varchar(50) NOT NULL DEFAULT '..部门' COMMENT '所属部门名称',
  `remark` text COMMENT '描述',
  `creator` int(11)  COMMENT '创建人',
  `updater` int(11)  COMMENT '修改人',
  `create_time` datetime  COMMENT '创建时间',
  `update_time` datetime  COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10000087 DEFAULT CHARSET=gb2312 COMMENT='用户表';

-- ----------------------------
-- Records of tb_employee
-- ----------------------------
INSERT INTO `tb_employee` VALUES ('10000080', '刘小波', '18380805107', '四川广安', '10003', 'java组', 'java初级工程师', '10000080', '10000080', '2017-04-30 21:03:06', '2017-04-30 21:03:10');
INSERT INTO `tb_employee` VALUES ('10000081', 'Ben', '18989897890', '新加坡', '10002', '技术部', '部门经理', '10000080', '10000080', '2017-04-30 21:05:27', '2017-04-30 21:05:29');
INSERT INTO `tb_employee` VALUES ('10000082', '廖天宇', '13234567868', '重庆', '10003', 'java组', '项目组长', '10000080', '10000080', '2017-04-30 21:07:34', '2017-04-30 21:07:36');
INSERT INTO `tb_employee` VALUES ('10000083', '帮薪', '14356756789', '重庆', '10004', 'IOS组', '项目组长', '10000080', '10000080', '2017-04-30 21:08:57', '2017-04-30 21:08:59');
INSERT INTO `tb_employee` VALUES ('10000084', '胡定江', '18090989876', '重庆', '10005', 'Android组', '项目组长', '10000080', '10000080', '2017-04-30 21:10:21', '2017-04-30 21:10:23');
INSERT INTO `tb_employee` VALUES ('10000085', '杨鹤', '17345678909', '重庆', '10002', '技术部', '总监', '10000080', '10000080', '2017-04-30 21:13:53', '2017-04-30 21:13:56');
INSERT INTO `tb_employee` VALUES ('10000086', '汪BOSS', '14568677893', '陕西', '10001', '总裁办', '总裁', '10000080', '10000080', '2017-04-30 21:15:46', '2017-04-30 21:15:50');

-- ----------------------------
-- Table structure for tb_employee_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_employee_role`;
CREATE TABLE `tb_employee_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `eid` int(11) NOT NULL COMMENT '员工id',
  `ename` varchar(50) DEFAULT NULL COMMENT '员工名称',
  `rid` int(11) NOT NULL COMMENT '员工id',
  `rname` varchar(50) DEFAULT NULL COMMENT ' 角色名称',
  `remark` text COMMENT '描述',
  `creator` int(11)  COMMENT '创建人',
  `updater` int(11)  COMMENT '修改人',
  `create_time` datetime  COMMENT '创建时间',
  `update_time` datetime  COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10009 DEFAULT CHARSET=gb2312 COMMENT='员工角色';

-- ----------------------------
-- Records of tb_employee_role
-- ----------------------------
INSERT INTO `tb_employee_role` VALUES ('10001', '10000080', '刘小波', '10008', 'java初级工程师', null, '10000080', '10000080', '2017-05-04 10:51:03', '2017-05-04 10:51:06');
INSERT INTO `tb_employee_role` VALUES ('10002', '10000080', '刘小波', '10009', '系统超级管理员', null, '10000080', '10000080', '2017-05-04 10:52:04', '2017-05-04 10:52:06');
INSERT INTO `tb_employee_role` VALUES ('10003', '10000081', 'Ben', '10002', '技术经理', null, '10000080', '10000080', '2017-05-04 10:53:18', '2017-05-04 10:53:20');
INSERT INTO `tb_employee_role` VALUES ('10004', '10000082', '廖天宇', '10005', 'java组长', null, '10000080', '10000080', '2017-05-04 10:55:04', '2017-05-04 10:55:07');
INSERT INTO `tb_employee_role` VALUES ('10005', '10000083', '帮薪', '10006', 'IOS组长', null, '10000080', '10000080', '2017-05-04 10:56:25', '2017-05-04 10:56:27');
INSERT INTO `tb_employee_role` VALUES ('10006', '10000084', '胡定江', '10007', 'Androi组长', null, '10000080', '10000080', '2017-05-04 10:57:25', '2017-05-04 10:57:28');
INSERT INTO `tb_employee_role` VALUES ('10007', '10000085', '杨鹤', '10004', '技术总监', null, '10000080', '10000080', '2017-05-04 10:58:25', '2017-05-04 10:58:27');
INSERT INTO `tb_employee_role` VALUES ('10008', '10000086', '汪BOSS', '10001', '总裁', null, '10000080', '10000080', '2017-05-04 10:59:22', '2017-05-04 10:59:24');

-- ----------------------------
-- Table structure for tb_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_log`;
CREATE TABLE `tb_log` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `content` text NOT NULL COMMENT '内容',
  `log_number` varchar(50) NOT NULL COMMENT '日志编号，逗号分隔',
  `check_user` int(11) NOT NULL COMMENT '审核人',
  `check_username` varchar(50) NOT NULL COMMENT '审核人名称',
  `check_time` datetime DEFAULT NULL COMMENT '审核时间',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '审核通过与否-0：未通过，1：通过了',
  `remark` text COMMENT '描述',
  `creator` int(11)  COMMENT '创建人',
  `updater` int(11)  COMMENT '修改人',
  `create_time` datetime  COMMENT '创建时间',
  `update_time` datetime  COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='日志';

-- ----------------------------
-- Records of tb_log
-- ----------------------------

-- ----------------------------
-- Table structure for tb_login
-- ----------------------------
DROP TABLE IF EXISTS `tb_login`;
CREATE TABLE `tb_login` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `phone` varchar(20) NOT NULL COMMENT '电话',
  `password` varchar(20) NOT NULL COMMENT '密码',
  `last_login_time` datetime NOT NULL COMMENT '最后登录时间',
  `last_login_address` varchar(50) NOT NULL COMMENT '最后登录地址',
  `login_number` int(11) NOT NULL COMMENT '登录次数',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=100000003 DEFAULT CHARSET=gb2312 COMMENT='登录记录表';

-- ----------------------------
-- Records of tb_login
-- ----------------------------
INSERT INTO `tb_login` VALUES ('100000001', '18380805107', '123456', '2017-05-19 11:32:44', '192.168.1.100', '74');
INSERT INTO `tb_login` VALUES ('100000002', '18989897890', '23456', '2017-05-14 10:03:49', '192.168.1.136', '1');

-- ----------------------------
-- Table structure for tb_look_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_look_notice`;
CREATE TABLE `tb_look_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL COMMENT '查看的公告id',
  `remark` text COMMENT '描述',
  `creator` int(11)  COMMENT '创建人',
  `updater` int(11)  COMMENT '修改人',
  `create_time` datetime  COMMENT '创建时间',
  `update_time` datetime  COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='查看公告的记录';

-- ----------------------------
-- Records of tb_look_notice
-- ----------------------------

-- ----------------------------
-- Table structure for tb_look_repository
-- ----------------------------
DROP TABLE IF EXISTS `tb_look_repository`;
CREATE TABLE `tb_look_repository` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `rid` int(11) NOT NULL COMMENT '查看的知识库链接id',
  `remark` text COMMENT '描述',
  `creator` int(11)  COMMENT '创建人',
  `updater` int(11)  COMMENT '修改人',
  `create_time` datetime  COMMENT '创建时间',
  `update_time` datetime  COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='查看知识链接的记录';

-- ----------------------------
-- Records of tb_look_repository
-- ----------------------------

-- ----------------------------
-- Table structure for tb_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `content` text NOT NULL COMMENT '内容',
  `look_number` int(11) NOT NULL DEFAULT '0' COMMENT '查看次数',
  `category` int(11) NOT NULL DEFAULT '0' COMMENT '0:内部，1：外部',
  `remark` text COMMENT '描述',
  `creator` int(11)  COMMENT '创建人',
  `updater` int(11)  COMMENT '修改人',
  `create_time` datetime  COMMENT '创建时间',
  `update_time` datetime  COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=7 DEFAULT CHARSET=gb2312 COMMENT='公告';

-- ----------------------------
-- Records of tb_notice
-- ----------------------------
INSERT INTO `tb_notice` VALUES ('1', '公告', '体检', '11', '0', null, '10000080', '10000080', '2017-05-14 07:54:55', '2017-05-14 07:54:55');
INSERT INTO `tb_notice` VALUES ('2', '公告', '体检', '10', '0', null, '10000080', '10000080', '2017-05-14 07:57:42', '2017-05-14 07:57:42');
INSERT INTO `tb_notice` VALUES ('3', '公告', '24-26号大坪医院全体员工集体体检', '7', '0', null, '10000080', '10000080', '2017-05-14 08:04:27', '2017-05-14 08:04:27');
INSERT INTO `tb_notice` VALUES ('4', '公告', '老板是傻逼', '5', '0', null, '10000080', '10000080', '2017-05-14 08:44:45', '2017-05-14 08:44:45');
INSERT INTO `tb_notice` VALUES ('5', '公告', '昨天老板是傻逼', '4', '0', null, '10000080', '10000080', '2017-05-14 08:47:55', '2017-05-14 08:47:55');
INSERT INTO `tb_notice` VALUES ('6', '公司', '今天群友', '1', '0', null, '10000080', '10000080', '2017-05-14 10:26:29', '2017-05-14 10:26:29');

-- ----------------------------
-- Table structure for tb_repository
-- ----------------------------
DROP TABLE IF EXISTS `tb_repository`;
CREATE TABLE `tb_repository` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `url` varchar(50) DEFAULT NULL COMMENT '链接',
  `look_number` int(11) NOT NULL DEFAULT '0' COMMENT '查看次数',
  `remark` text COMMENT '描述',
  `creator` int(11)  COMMENT '创建人',
  `updater` int(11)  COMMENT '修改人',
  `create_time` datetime  COMMENT '创建时间',
  `update_time` datetime  COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='知识库';

-- ----------------------------
-- Records of tb_repository
-- ----------------------------

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `func` varchar(50) DEFAULT NULL COMMENT '职能',
  `level` int(11) NOT NULL COMMENT '角色的级别0-1-2-3....',
  `did` int(11) NOT NULL COMMENT '所属部门id',
  `dname` varchar(50) NOT NULL DEFAULT '..部门' COMMENT '所属部门名称',
  `remark` text COMMENT '描述',
  `creator` int(11)  COMMENT '创建人',
  `updater` int(11)  COMMENT '修改人',
  `create_time` datetime  COMMENT '创建时间',
  `update_time` datetime  COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=10010 DEFAULT CHARSET=gb2312 COMMENT='角色';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('10001', '总裁', 'BOSS', '10', '10001', '总裁办', 'Boss', '10000080', '10000080', '2017-04-30 22:51:24', '2017-04-30 22:51:26');
INSERT INTO `tb_role` VALUES ('10002', '技术经理', '技术部经理', '101', '10002', '技术部', '技术部经理', '10000080', '10000080', '2017-04-30 22:53:06', '2017-04-30 22:53:08');
INSERT INTO `tb_role` VALUES ('10003', '商城经理', '管理商城', '102', '100010', '商城', '商城经理', '10000080', '10000080', '2017-04-30 22:54:01', '2017-04-30 22:54:03');
INSERT INTO `tb_role` VALUES ('10004', '技术总监', '技术支持', '1001', '1002', '技术部', '技术总监', '10000080', '10000080', '2017-05-04 10:35:33', '2017-05-04 10:35:35');
INSERT INTO `tb_role` VALUES ('10005', 'java组长', '技术开发', '10001', '1003', 'java组', 'java开发组长', '10000080', '10000080', '2017-05-04 10:36:30', '2017-05-04 10:36:32');
INSERT INTO `tb_role` VALUES ('10006', 'IOS组长', '技术开发', '10002', '1004', 'IOS组', 'IOS开发组长', '10000080', '10000080', '2017-05-04 10:38:09', '2017-05-04 10:38:11');
INSERT INTO `tb_role` VALUES ('10007', 'Android组长', '技术开发', '10003', '1005', 'Android组', 'Android开发组长', '10000080', '10000080', '2017-05-04 10:39:03', '2017-05-04 10:39:06');
INSERT INTO `tb_role` VALUES ('10008', 'java初级工程师', '开发', '100001', '1003', 'java组', 'java开发', '10000080', '10000080', '2017-05-04 10:40:28', '2017-05-04 10:40:31');
INSERT INTO `tb_role` VALUES ('10009', '系统超级管理员', '系统管理员', '1', '1003', 'java组', '系统管理', '10000080', '10000080', '2017-05-04 10:50:24', '2017-05-04 10:50:27');

-- ----------------------------
-- Table structure for tb_role_action
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_action`;
CREATE TABLE `tb_role_action` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `aid` int(11) NOT NULL COMMENT '功能id',
  `aname` varchar(50) DEFAULT NULL COMMENT '功能名称',
  `rid` int(11) NOT NULL COMMENT '员工id',
  `rname` varchar(50) DEFAULT NULL COMMENT ' 角色名称',
  `remark` text COMMENT '描述',
  `creator` int(11)  COMMENT '创建人',
  `updater` int(11)  COMMENT '修改人',
  `create_time` datetime  COMMENT '创建时间',
  `update_time` datetime  COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=38 DEFAULT CHARSET=gb2312 COMMENT='角色功能';

-- ----------------------------
-- Records of tb_role_action
-- ----------------------------
INSERT INTO `tb_role_action` VALUES ('1', '1', '知识库', '10008', 'java初级工程师', null, '10000080', '10000080', '2017-05-04 23:32:31', '2017-05-04 23:32:33');
INSERT INTO `tb_role_action` VALUES ('2', '2', '日志', '10008', 'java初级工程师', null, '10000080', '10000080', '2017-05-04 23:42:21', '2017-05-04 23:42:24');
INSERT INTO `tb_role_action` VALUES ('3', '3', '任务', '10008', 'java初级工程师', null, '10000080', '10000080', '2017-05-04 23:43:16', '2017-05-04 23:43:20');
INSERT INTO `tb_role_action` VALUES ('4', '4', '公告', '10008', 'Java初级工程师', null, '10000080', '10000080', '2017-05-04 23:43:50', '2017-05-04 23:43:52');
INSERT INTO `tb_role_action` VALUES ('5', '5', '系统管理', '10009', '系统超级管理员', null, '10000080', '10000080', '2017-05-04 23:44:36', '2017-05-04 23:44:39');
INSERT INTO `tb_role_action` VALUES ('6', '33', '个人资料', '10008', 'java初级工程师', null, '10000080', '10000080', '2017-05-04 23:46:53', '2017-05-04 23:46:55');
INSERT INTO `tb_role_action` VALUES ('7', '34', '查看个人资料', '10008', 'java初级工程师', null, '10000080', '10000080', '2017-05-04 23:47:39', '2017-05-04 23:47:41');
INSERT INTO `tb_role_action` VALUES ('8', '35', '修改个人资料', '10008', 'java初级工程师', null, '10000080', '10000080', '2017-05-04 23:48:14', '2017-05-04 23:48:17');
INSERT INTO `tb_role_action` VALUES ('9', '6', '查看分享', '10008', 'java初级工程师', null, '10000080', '10000080', '2017-05-04 23:50:01', '2017-05-04 23:50:05');
INSERT INTO `tb_role_action` VALUES ('10', '7', '删除分享', '10008', 'java初级工程师', null, '10000080', '10000080', '2017-05-04 23:50:47', '2017-05-04 23:50:49');
INSERT INTO `tb_role_action` VALUES ('11', '8', '添加分享', '10008', 'java初级工程师', null, '10000080', '10000080', '2017-05-04 23:51:36', '2017-05-04 23:51:38');
INSERT INTO `tb_role_action` VALUES ('12', '9', '写日志', '10008', 'java初级工程师', null, '10000080', '10000080', '2017-05-04 23:52:41', '2017-05-04 23:52:43');
INSERT INTO `tb_role_action` VALUES ('13', '10', '历史日志', '10008', 'java初级工程师', null, '10000080', '10000080', '2017-05-04 23:53:43', '2017-05-04 23:53:45');
INSERT INTO `tb_role_action` VALUES ('14', '14', '我的任务', '10008', 'java初级工程师', null, '10000080', '10000080', '2017-05-04 23:55:39', '2017-05-04 23:55:42');
INSERT INTO `tb_role_action` VALUES ('15', '16', '查看公告', '10008', 'java初级工程师', null, '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:57');
INSERT INTO `tb_role_action` VALUES ('16', '17', '用户管理', '10009', '系统超级管理员', '管理员功能', '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:55');
INSERT INTO `tb_role_action` VALUES ('17', '18', '角色管理', '10009', '系统超级管理员', '管理员功能', '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:55');
INSERT INTO `tb_role_action` VALUES ('18', '19', '功能管理', '10009', '系统超级管理员', '管理员功能', '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:55');
INSERT INTO `tb_role_action` VALUES ('19', '20', '部门管理', '10009', '系统超级管理员', '管理员功能', '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:55');
INSERT INTO `tb_role_action` VALUES ('20', '21', '添加用户', '10009', '系统超级管理员', '管理员功能', '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:55');
INSERT INTO `tb_role_action` VALUES ('21', '22', '删除用户', '10009', '系统超级管理员', '管理员功能', '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:55');
INSERT INTO `tb_role_action` VALUES ('22', '23', '修改用户', '10009', '系统超级管理员', '管理员功能', '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:55');
INSERT INTO `tb_role_action` VALUES ('23', '24', '增加功能', '10009', '系统超级管理员', '管理员功能', '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:55');
INSERT INTO `tb_role_action` VALUES ('24', '25', '删除功能', '10009', '系统超级管理员', '管理员功能', '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:55');
INSERT INTO `tb_role_action` VALUES ('25', '26', '修改功能', '10009', '系统超级管理员', '管理员功能', '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:55');
INSERT INTO `tb_role_action` VALUES ('26', '27', '增加角色', '10009', '系统超级管理员', '管理员功能', '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:55');
INSERT INTO `tb_role_action` VALUES ('27', '28', '删除角色', '10009', '系统超级管理员', '管理员功能', '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:55');
INSERT INTO `tb_role_action` VALUES ('28', '29', '修改角色', '10009', '系统超级管理员', '管理员功能', '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:55');
INSERT INTO `tb_role_action` VALUES ('29', '30', '增加部门', '10009', '系统超级管理员', '管理员功能', '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:55');
INSERT INTO `tb_role_action` VALUES ('30', '31', '删除部门', '10009', '系统超级管理员', '管理员功能', '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:55');
INSERT INTO `tb_role_action` VALUES ('31', '32', '修改部门', '10009', '系统超级管理员', '管理员功能', '10000080', '10000080', '2017-05-04 23:56:55', '2017-05-04 23:56:55');
INSERT INTO `tb_role_action` VALUES ('32', '36', '查看个人资料数据', '10008', 'java初级工程师', null, '10000080', '10000080', '2017-05-17 23:37:28', '2017-05-17 23:37:32');
INSERT INTO `tb_role_action` VALUES ('33', '37', '发送验证码', '10008', 'java初级工程师', null, '10000080', '10000080', '2017-05-18 02:41:29', '2017-05-18 02:41:32');
INSERT INTO `tb_role_action` VALUES ('34', '38', null, '10009', null, '管理员功能', '10000080', '10000080', '2017-05-19 11:20:02', '2017-05-19 11:20:05');
INSERT INTO `tb_role_action` VALUES ('35', '39', null, '10009', null, '管理员功能', '10000080', '10000080', '2017-05-19 11:20:50', '2017-05-19 11:20:52');
INSERT INTO `tb_role_action` VALUES ('36', '40', null, '10009', null, '管理员功能', '10000080', '10000080', '2017-05-19 11:21:25', '2017-05-19 11:21:27');
INSERT INTO `tb_role_action` VALUES ('37', '41', null, '10009', null, '管理员功能', '10000080', '10000080', '2017-05-19 11:22:07', '2017-05-19 11:22:09');

-- ----------------------------
-- Table structure for tb_task
-- ----------------------------
DROP TABLE IF EXISTS `tb_task`;
CREATE TABLE `tb_task` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(50) DEFAULT NULL COMMENT '名称',
  `content` text NOT NULL COMMENT '内容',
  `begin_time` datetime NOT NULL COMMENT '开始时间',
  `end_time` datetime NOT NULL COMMENT '完成时间',
  `state` int(11) NOT NULL DEFAULT '0' COMMENT '状态-0：未开始，1：进行中，2：结束，3：关闭',
  `expect_time` int(11) NOT NULL DEFAULT '0' COMMENT '预计需要时间',
  `actual_time` int(11) NOT NULL DEFAULT '0' COMMENT '实际花费时间',
  `category` int(11) NOT NULL DEFAULT '0' COMMENT '是需要上级审核，还是下级完成；0：完成，1：审核',
  `remark` text COMMENT '描述',
  `creator` int(11)  COMMENT '创建人',
  `updater` int(11)  COMMENT '修改人',
  `create_time` datetime  COMMENT '创建时间',
  `update_time` datetime  COMMENT '更新时间',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB DEFAULT CHARSET=gb2312 COMMENT='任务';

-- ----------------------------
-- Records of tb_task
-- ----------------------------

-- ----------------------------
-- Table structure for t_user
-- ----------------------------
DROP TABLE IF EXISTS `t_user`;
CREATE TABLE `t_user` (
  `id` bigint(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL COMMENT '用户名',
  `user_phone` varchar(20) DEFAULT NULL COMMENT '手机号',
  `user_email` varchar(255) DEFAULT NULL COMMENT '邮箱地址',
  `user_pwd` varchar(32) DEFAULT NULL COMMENT '加盐后用户密码',
  `pwd_salt` varchar(6) DEFAULT NULL COMMENT 'MD5盐',
  `create_time` datetime DEFAULT NULL COMMENT '创建时间',
  `modify_time` datetime DEFAULT NULL COMMENT '最后修改时间',
  `is_delete` tinyint(4) DEFAULT NULL COMMENT '是否删除，0-未删除；1-已删除',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8 COMMENT='用户登录表';

-- ----------------------------
-- Records of t_user
-- ----------------------------
INSERT INTO `t_user` VALUES ('1', '赵大宝', '13285250574', '1045221654@qq.com', '05126a423a9379d529e4ee61a212fa55', 'KJUYT5', '2016-07-15 23:38:56', '2016-07-15 23:39:09', '0');
INSERT INTO `t_user` VALUES ('2', '张三丰', '15985250574', '1198224554@qq.com', '98bd3a1bebde01ad363d3c5a0d1e56da', '656JHU', '2016-07-15 23:39:01', '2016-07-15 23:39:13', '0');
INSERT INTO `t_user` VALUES ('3', '王尼玛', '13685250574', '1256221654@qq.com', '5470db9b63c354f6c8d628b80ae2f3c3', '89UIKQ', '2016-07-15 23:39:05', '2016-07-15 23:39:16', '0');
