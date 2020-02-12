/*
Navicat MySQL Data Transfer

Source Server         : lytw13
Source Server Version : 50726
Source Host           : localhost:3306
Source Database       : db_admin

Target Server Type    : MYSQL
Target Server Version : 50726
File Encoding         : 65001

Date: 2020-02-12 16:49:01
*/

SET FOREIGN_KEY_CHECKS=0;
CREATE Database db_admin;
USE db_admin;
-- ----------------------------
-- Table structure for tb_dept
-- ----------------------------
DROP TABLE IF EXISTS `tb_dept`;
CREATE TABLE `tb_dept` (
  `dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `dept_name` varchar(255) NOT NULL DEFAULT '' COMMENT '部门名称',
  `dept_sequence` int(11) NOT NULL COMMENT '部门排序',
  `dept_leader` varchar(255) NOT NULL DEFAULT '' COMMENT '部门负责人',
  `dept_status` int(11) NOT NULL COMMENT '部门状态',
  `dept_pid` int(11) NOT NULL,
  PRIMARY KEY (`dept_id`),
  UNIQUE KEY `dept_name` (`dept_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='部门表';

-- ----------------------------
-- Records of tb_dept
-- ----------------------------
INSERT INTO `tb_dept` VALUES ('1', '北京科技公司', '1', 'lytw1315', '1', '0');
INSERT INTO `tb_dept` VALUES ('2', '研发部门', '1', 'lytw1315', '1', '1');
INSERT INTO `tb_dept` VALUES ('3', '测试部门', '2', 'lytw1315', '1', '1');
INSERT INTO `tb_dept` VALUES ('4', '运维部门', '3', 'lytw1315', '1', '1');

-- ----------------------------
-- Table structure for tb_dict_data
-- ----------------------------
DROP TABLE IF EXISTS `tb_dict_data`;
CREATE TABLE `tb_dict_data` (
  `dict_data_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_data_type` varchar(255) DEFAULT NULL,
  `dict_data_label` varchar(255) DEFAULT NULL,
  `dict_data_value` int(11) DEFAULT NULL,
  PRIMARY KEY (`dict_data_id`),
  UNIQUE KEY `dict_data_label` (`dict_data_label`)
) ENGINE=InnoDB AUTO_INCREMENT=10 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_dict_data
-- ----------------------------
INSERT INTO `tb_dict_data` VALUES ('1', 'user_sex', '男', '0');
INSERT INTO `tb_dict_data` VALUES ('2', 'user_sex', '女', '1');
INSERT INTO `tb_dict_data` VALUES ('3', 'is_enabled', '正常', '1');
INSERT INTO `tb_dict_data` VALUES ('4', 'is_enabled', '停用', '0');
INSERT INTO `tb_dict_data` VALUES ('6', 'db_operType', '新增', '1');
INSERT INTO `tb_dict_data` VALUES ('7', 'db_operType', '删除', '2');
INSERT INTO `tb_dict_data` VALUES ('8', 'db_operType', '修改', '3');
INSERT INTO `tb_dict_data` VALUES ('9', 'db_operType', '查询', '4');

-- ----------------------------
-- Table structure for tb_dict_type
-- ----------------------------
DROP TABLE IF EXISTS `tb_dict_type`;
CREATE TABLE `tb_dict_type` (
  `dict_type_id` int(11) NOT NULL AUTO_INCREMENT,
  `dict_type_name` varchar(255) DEFAULT NULL,
  `dict_type_type` varchar(255) DEFAULT NULL,
  `dict_type_status` int(11) DEFAULT NULL,
  `dict_type_createDate` datetime DEFAULT CURRENT_TIMESTAMP ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`dict_type_id`),
  UNIQUE KEY `dict_type_name` (`dict_type_name`)
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_dict_type
-- ----------------------------
INSERT INTO `tb_dict_type` VALUES ('1', '用户性别', 'user_sex', '0', '2020-01-31 19:35:02');
INSERT INTO `tb_dict_type` VALUES ('2', '开启状态', 'is_enabled', '1', '2020-01-31 11:09:21');
INSERT INTO `tb_dict_type` VALUES ('3', '数据库操作类型', 'db_operType', '1', '2020-02-12 16:37:20');

-- ----------------------------
-- Table structure for tb_log
-- ----------------------------
DROP TABLE IF EXISTS `tb_log`;
CREATE TABLE `tb_log` (
  `log_id` int(11) NOT NULL AUTO_INCREMENT,
  `log_oper_request` varchar(255) DEFAULT NULL,
  `log_oper_method` varchar(255) DEFAULT NULL,
  `log_oper_type` int(11) DEFAULT NULL,
  `log_oper_comment` varchar(255) DEFAULT NULL,
  `log_oper_ip` varchar(255) DEFAULT NULL,
  `log_oper_consume` bigint(20) DEFAULT NULL,
  `log_oper_date` datetime DEFAULT NULL,
  PRIMARY KEY (`log_id`)
) ENGINE=InnoDB AUTO_INCREMENT=39 DEFAULT CHARSET=utf8 COMMENT='日志表';

-- ----------------------------
-- Records of tb_log
-- ----------------------------
INSERT INTO `tb_log` VALUES ('1', 'POST', 'add', '1', null, '127.0.0.1', '5107', null);
INSERT INTO `tb_log` VALUES ('2', 'GET', 'delete', '2', null, '127.0.0.1', '69', '2020-02-12 15:25:04');
INSERT INTO `tb_log` VALUES ('3', 'GET', 'delete', '2', null, '127.0.0.1', '57', '2020-02-12 15:26:09');
INSERT INTO `tb_log` VALUES ('4', 'GET', 'delete', '2', '删除用户', '127.0.0.1', '57', '2020-02-12 15:28:47');
INSERT INTO `tb_log` VALUES ('5', 'POST', 'updateUser', null, '修改用户', '127.0.0.1', '52', '2020-02-12 15:29:41');
INSERT INTO `tb_log` VALUES ('6', 'POST', 'update', '3', '修改用户', '127.0.0.1', '58', '2020-02-12 15:42:08');
INSERT INTO `tb_log` VALUES ('7', 'POST', 'add', '1', '添加角色', '127.0.0.1', '108', '2020-02-12 15:51:20');
INSERT INTO `tb_log` VALUES ('8', 'POST', 'update', '3', '修改角色', '127.0.0.1', '52', '2020-02-12 15:51:47');
INSERT INTO `tb_log` VALUES ('9', 'GET', 'delete', '2', '删除角色', '127.0.0.1', '64', '2020-02-12 15:51:55');
INSERT INTO `tb_log` VALUES ('10', 'POST', 'add', '1', '添加菜单', '127.0.0.1', '59', '2020-02-12 15:52:24');
INSERT INTO `tb_log` VALUES ('11', 'POST', 'add', '1', '添加菜单', '127.0.0.1', '56', '2020-02-12 15:52:46');
INSERT INTO `tb_log` VALUES ('12', 'POST', 'update', '3', '修改菜单', '127.0.0.1', '24', '2020-02-12 15:53:26');
INSERT INTO `tb_log` VALUES ('13', 'GET', 'delete', '2', '删除菜单', '127.0.0.1', '54', '2020-02-12 15:53:31');
INSERT INTO `tb_log` VALUES ('14', 'POST', 'add', '1', '添加公告', '127.0.0.1', '59', '2020-02-12 16:04:32');
INSERT INTO `tb_log` VALUES ('15', 'POST', 'update', '3', '修改公告', '127.0.0.1', '53', '2020-02-12 16:04:41');
INSERT INTO `tb_log` VALUES ('16', 'GET', 'delete', '2', '删除公告', '127.0.0.1', '52', '2020-02-12 16:04:45');
INSERT INTO `tb_log` VALUES ('17', 'POST', 'add', '1', '添加字典', '127.0.0.1', '59', '2020-02-12 16:05:08');
INSERT INTO `tb_log` VALUES ('18', 'GET', 'delete', '2', '删除字典', '127.0.0.1', '53', '2020-02-12 16:05:27');
INSERT INTO `tb_log` VALUES ('19', 'POST', 'addData', '1', '添加字典数据', '127.0.0.1', '51', '2020-02-12 16:06:00');
INSERT INTO `tb_log` VALUES ('20', 'POST', 'modifyData', null, '修改字典数据', '127.0.0.1', '2', '2020-02-12 16:06:08');
INSERT INTO `tb_log` VALUES ('21', 'GET', 'deleteData', '2', '删除字典数据', '127.0.0.1', '58', '2020-02-12 16:06:16');
INSERT INTO `tb_log` VALUES ('22', 'POST', 'update', '3', '修改字典', '127.0.0.1', '4270', '2020-02-12 16:14:59');
INSERT INTO `tb_log` VALUES ('23', 'POST', 'modifyData', null, '修改字典数据', '127.0.0.1', '35742', '2020-02-12 16:16:15');
INSERT INTO `tb_log` VALUES ('24', 'POST', 'modifyData', null, '修改字典数据', '127.0.0.1', '6348', '2020-02-12 16:16:31');
INSERT INTO `tb_log` VALUES ('25', 'POST', 'modifyData', null, '修改字典数据', '127.0.0.1', '10452', '2020-02-12 16:16:57');
INSERT INTO `tb_log` VALUES ('26', 'POST', 'modifyData', null, '修改字典数据', '127.0.0.1', '54949', '2020-02-12 16:21:10');
INSERT INTO `tb_log` VALUES ('27', 'POST', 'modifyData', null, '修改字典数据', '127.0.0.1', '32257', '2020-02-12 16:25:56');
INSERT INTO `tb_log` VALUES ('28', 'POST', 'modifyData', null, '修改字典数据', '127.0.0.1', '6468', '2020-02-12 16:26:30');
INSERT INTO `tb_log` VALUES ('29', 'POST', 'modifyData', '3', '修改字典数据', '127.0.0.1', '69', '2020-02-12 16:29:14');
INSERT INTO `tb_log` VALUES ('30', 'POST', 'add', '1', '添加字典', '127.0.0.1', '58', '2020-02-12 16:34:14');
INSERT INTO `tb_log` VALUES ('31', 'GET', 'delete', '2', '删除字典', '127.0.0.1', '57', '2020-02-12 16:36:30');
INSERT INTO `tb_log` VALUES ('32', 'POST', 'add', '1', '添加字典', '127.0.0.1', '56', '2020-02-12 16:37:20');
INSERT INTO `tb_log` VALUES ('33', 'GET', 'delete', '2', '删除字典', '127.0.0.1', '4', '2020-02-12 16:37:20');
INSERT INTO `tb_log` VALUES ('34', 'POST', 'addData', '1', '添加字典数据', '127.0.0.1', '53', '2020-02-12 16:37:55');
INSERT INTO `tb_log` VALUES ('35', 'POST', 'addData', '1', '添加字典数据', '127.0.0.1', '52', '2020-02-12 16:38:10');
INSERT INTO `tb_log` VALUES ('36', 'POST', 'addData', '1', '添加字典数据', '127.0.0.1', '52', '2020-02-12 16:38:20');
INSERT INTO `tb_log` VALUES ('37', 'POST', 'modifyData', '3', '修改字典数据', '127.0.0.1', '52', '2020-02-12 16:38:27');
INSERT INTO `tb_log` VALUES ('38', 'POST', 'addData', '1', '添加字典数据', '127.0.0.1', '52', '2020-02-12 16:38:38');

-- ----------------------------
-- Table structure for tb_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_menu`;
CREATE TABLE `tb_menu` (
  `menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `menu_name` varchar(255) DEFAULT NULL,
  `menu_icon` varchar(255) DEFAULT '',
  `menu_url` varchar(255) DEFAULT NULL,
  `menu_permission` varchar(255) DEFAULT NULL,
  `menu_sequence` int(11) DEFAULT NULL,
  `menu_status` int(11) DEFAULT '1',
  `menu_pid` int(11) DEFAULT '0',
  `menu_type` varchar(255) DEFAULT NULL COMMENT '菜单类型 （m菜单 b按钮）',
  PRIMARY KEY (`menu_id`),
  UNIQUE KEY `menu_name` (`menu_name`)
) ENGINE=InnoDB AUTO_INCREMENT=1414 DEFAULT CHARSET=utf8 COMMENT='菜单权限表';

-- ----------------------------
-- Records of tb_menu
-- ----------------------------
INSERT INTO `tb_menu` VALUES ('1', '主页', 'glyphicon glyphicon-home', '#', 'all', '1', '1', '0', 'm');
INSERT INTO `tb_menu` VALUES ('2', '系统管理', 'glyphicon glyphicon-cog', '#', 'all', '2', '1', '0', 'm');
INSERT INTO `tb_menu` VALUES ('101', '用户管理', '', '/user', 'all', '1', '1', '2', 'm');
INSERT INTO `tb_menu` VALUES ('102', '角色管理', '', '/role', 'all', '2', '1', '2', 'm');
INSERT INTO `tb_menu` VALUES ('103', '菜单管理', '', '/menu', 'all', '3', '1', '2', 'm');
INSERT INTO `tb_menu` VALUES ('104', '公告管理', '', '/notice', 'all', '4', '1', '2', 'm');
INSERT INTO `tb_menu` VALUES ('105', '字典管理', '', '/dict', 'all', '5', '1', '2', 'm');
INSERT INTO `tb_menu` VALUES ('106', '日志管理', '', '/log', 'all', 6, '1', '2', 'm');
INSERT INTO `tb_menu` VALUES ('1001', '添加用户', '', '/user/add', 'user:add', '1', '1', '101', 'b');
INSERT INTO `tb_menu` VALUES ('1002', '删除用户', '', '/user/delete', 'user:delete', '2', '1', '101', 'b');
INSERT INTO `tb_menu` VALUES ('1003', '修改用户', '', '/user/updateUser', 'user:modify', '3', '1', '101', 'b');
INSERT INTO `tb_menu` VALUES ('1101', '添加角色', '', '/role/add', 'role:add', '1', '1', '102', 'b');
INSERT INTO `tb_menu` VALUES ('1102', '删除角色', '', '/role/delete', 'role:delete', '2', '1', '102', 'b');
INSERT INTO `tb_menu` VALUES ('1103', '修改角色', '', '/role/modify', 'role:modify', '3', '1', '102', 'b');
INSERT INTO `tb_menu` VALUES ('1201', '添加菜单', '', '/menu/add', 'menu:add', '1', '1', '103', 'b');
INSERT INTO `tb_menu` VALUES ('1202', '删除菜单', '', '/menu/delete', 'menu:delete', '2', '1', '103', 'b');
INSERT INTO `tb_menu` VALUES ('1203', '修改菜单', '', '/menu/modify', 'menu:modify', '3', '1', '103', 'b');
INSERT INTO `tb_menu` VALUES ('1301', '添加公告', '', '/notice/add', 'notice:add', '1', '1', '104', 'b');
INSERT INTO `tb_menu` VALUES ('1302', '删除公告', '', '/notice/delete', 'notice:delete', '2', '1', '104', 'b');
INSERT INTO `tb_menu` VALUES ('1303', '修改公告', '', '/notice/modify', 'notice:modify', '3', '1', '104', 'b');
INSERT INTO `tb_menu` VALUES ('1401', '添加字典', '', '/dict/add', 'dict:add', '1', '1', '105', 'b');
INSERT INTO `tb_menu` VALUES ('1402', '删除字典', '', '/dict/delete', 'dict:delete', '2', '1', '105', 'b');
INSERT INTO `tb_menu` VALUES ('1403', '修改字典', '', '/dict/modify', 'dict:modify', '3', '1', '105', 'b');
INSERT INTO `tb_menu` VALUES ('1411', '添加字典数据', '', '/dictData/add', 'dictData:add', '4', '1', '105', 'b');
INSERT INTO `tb_menu` VALUES ('1412', '删除字典数据', '', '/dictData/delete', 'dictData:delete', '5', '1', '105', 'b');
INSERT INTO `tb_menu` VALUES ('1413', '修改字典数据', '', '/dictData/modify', 'dictData:modify', '6', '1', '105', 'b');

-- ----------------------------
-- Table structure for tb_notice
-- ----------------------------
DROP TABLE IF EXISTS `tb_notice`;
CREATE TABLE `tb_notice` (
  `id` int(11) NOT NULL AUTO_INCREMENT,
  `name` varchar(255) DEFAULT NULL,
  `content` varchar(3000) DEFAULT NULL,
  `status` varchar(255) DEFAULT NULL,
  `createUser` varchar(255) DEFAULT NULL,
  `createDate` datetime DEFAULT NULL ON UPDATE CURRENT_TIMESTAMP,
  PRIMARY KEY (`id`),
  UNIQUE KEY `name` (`name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_notice
-- ----------------------------
INSERT INTO `tb_notice` VALUES ('1', '测试', '测试文件', '1', 'lytw1315', '2019-11-23 14:24:27');
INSERT INTO `tb_notice` VALUES ('2', 'TW LY', '<p>1355555556</p>', '0', 'admin', '2020-01-28 14:06:15');

-- ----------------------------
-- Table structure for tb_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_role`;
CREATE TABLE `tb_role` (
  `role_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_name` varchar(255) DEFAULT NULL,
  `role_sequence` int(11) DEFAULT NULL,
  `role_status` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`role_id`),
  UNIQUE KEY `role_name` (`role_name`)
) ENGINE=InnoDB AUTO_INCREMENT=3 DEFAULT CHARSET=utf8 COMMENT='角色表';

-- ----------------------------
-- Records of tb_role
-- ----------------------------
INSERT INTO `tb_role` VALUES ('1', '管理员', '1', '1');
INSERT INTO `tb_role` VALUES ('2', '普通用户', '2', '1');

-- ----------------------------
-- Table structure for tb_role_menu
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu` (
  `role_menu_id` int(11) NOT NULL AUTO_INCREMENT,
  `role_id` int(11) NOT NULL,
  `menu_id` int(11) NOT NULL,
  PRIMARY KEY (`role_menu_id`),
  KEY `menu_id` (`menu_id`),
  KEY `roleid` (`role_id`),
  CONSTRAINT `menu_id` FOREIGN KEY (`menu_id`) REFERENCES `tb_menu` (`menu_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `roleid` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=25 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
INSERT INTO `tb_role_menu` VALUES ('1', '1', '1');
INSERT INTO `tb_role_menu` VALUES ('2', '1', '2');
INSERT INTO `tb_role_menu` VALUES ('3', '1', '101');
INSERT INTO `tb_role_menu` VALUES ('4', '1', '102');
INSERT INTO `tb_role_menu` VALUES ('5', '1', '103');
INSERT INTO `tb_role_menu` VALUES ('6', '1', '1001');
INSERT INTO `tb_role_menu` VALUES ('7', '1', '1002');
INSERT INTO `tb_role_menu` VALUES ('8', '1', '1003');
INSERT INTO `tb_role_menu` VALUES ('9', '1', '1101');
INSERT INTO `tb_role_menu` VALUES ('10', '1', '1102');
INSERT INTO `tb_role_menu` VALUES ('11', '1', '1103');
INSERT INTO `tb_role_menu` VALUES ('12', '1', '1201');
INSERT INTO `tb_role_menu` VALUES ('13', '1', '1202');
INSERT INTO `tb_role_menu` VALUES ('14', '1', '1203');
INSERT INTO `tb_role_menu` VALUES ('15', '2', '1003');
INSERT INTO `tb_role_menu` VALUES ('16', '1', '1301');
INSERT INTO `tb_role_menu` VALUES ('17', '1', '1302');
INSERT INTO `tb_role_menu` VALUES ('18', '1', '1303');
INSERT INTO `tb_role_menu` VALUES ('19', '1', '1401');
INSERT INTO `tb_role_menu` VALUES ('20', '1', '1402');
INSERT INTO `tb_role_menu` VALUES ('21', '1', '1403');
INSERT INTO `tb_role_menu` VALUES ('22', '1', '1411');
INSERT INTO `tb_role_menu` VALUES ('23', '1', '1412');
INSERT INTO `tb_role_menu` VALUES ('24', '1', '1413');

-- ----------------------------
-- Table structure for tb_user
-- ----------------------------
DROP TABLE IF EXISTS `tb_user`;
CREATE TABLE `tb_user` (
  `user_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_name` varchar(255) DEFAULT NULL,
  `user_password` varchar(255) DEFAULT NULL,
  `user_sex` int(11) DEFAULT '0' COMMENT '0男性 1女性',
  `user_icon` varchar(255) DEFAULT '/img/defaultUser.jpg',
  `user_phone` varchar(255) DEFAULT '',
  `user_email` varchar(255) DEFAULT NULL,
  `user_status` int(11) DEFAULT '0' COMMENT '激活状态',
  `user_createDate` timestamp NULL DEFAULT NULL COMMENT '/创建时间',
  PRIMARY KEY (`user_id`),
  UNIQUE KEY `name` (`user_name`)
) ENGINE=InnoDB AUTO_INCREMENT=5 DEFAULT CHARSET=utf8 COMMENT='用户表';

-- ----------------------------
-- Records of tb_user
-- ----------------------------
INSERT INTO `tb_user` VALUES ('1', 'admin', '000000', '1', '/img/defaultUser.jpg', '16666666666', '333333333@qq.com', '1', '2019-11-09 13:33:29');
INSERT INTO `tb_user` VALUES ('2', 'lytw', '000000', '1', '/img/defaultUser.jpg', '17777555577', '333335555@qq.com', '1', '2019-11-09 22:42:31');
INSERT INTO `tb_user` VALUES ('3', 'ceshi00', '000000', '0', '/img/defaultUser.jpg', '17777777776', '33335313751@qq.com', '1', '2020-02-11 23:15:20');
INSERT INTO `tb_user` VALUES ('4', 'admin123', '000000', '0', '/img/defaultUser.jpg', '17777777776', '33335313751@qq.com', '1', '2020-02-12 11:17:31');
-- ----------------------------
-- Table structure for tb_user_dept
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_dept`;
CREATE TABLE `tb_user_dept` (
  `user_dept_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) NOT NULL,
  `dept_id` int(11) NOT NULL,
  PRIMARY KEY (`user_dept_id`),
  KEY `dept_id` (`dept_id`),
  KEY `userid` (`user_id`),
  CONSTRAINT `dept_id` FOREIGN KEY (`dept_id`) REFERENCES `tb_dept` (`dept_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `userid` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=4 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_dept
-- ----------------------------
INSERT INTO `tb_user_dept` VALUES ('1', '1', '1');
INSERT INTO `tb_user_dept` VALUES ('2', '3', '2');
INSERT INTO `tb_user_dept` VALUES ('3', '4', '3');

-- ----------------------------
-- Table structure for tb_user_role
-- ----------------------------
DROP TABLE IF EXISTS `tb_user_role`;
CREATE TABLE `tb_user_role` (
  `user_role_id` int(11) NOT NULL AUTO_INCREMENT,
  `user_id` int(11) DEFAULT NULL,
  `role_id` int(11) DEFAULT NULL,
  PRIMARY KEY (`user_role_id`),
  KEY `role_id` (`role_id`),
  KEY `user_id` (`user_id`),
  CONSTRAINT `role_id` FOREIGN KEY (`role_id`) REFERENCES `tb_role` (`role_id`) ON DELETE CASCADE ON UPDATE CASCADE,
  CONSTRAINT `user_id` FOREIGN KEY (`user_id`) REFERENCES `tb_user` (`user_id`) ON DELETE CASCADE ON UPDATE CASCADE
) ENGINE=InnoDB AUTO_INCREMENT=6 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_user_role
-- ----------------------------
INSERT INTO `tb_user_role` VALUES ('1', '1', '1');
INSERT INTO `tb_user_role` VALUES ('2', '1', '2');
INSERT INTO `tb_user_role` VALUES ('3', '3', '1');
INSERT INTO `tb_user_role` VALUES ('4', '3', '2');
INSERT INTO `tb_user_role` VALUES ('5', '4', '1');
SET FOREIGN_KEY_CHECKS=1;
