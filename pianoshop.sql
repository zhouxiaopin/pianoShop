/*
Navicat MySQL Data Transfer

Source Server         : local_root
Source Server Version : 50540
Source Host           : localhost:3306
Source Database       : pianoshop

Target Server Type    : MYSQL
Target Server Version : 50540
File Encoding         : 65001

Date: 2018-07-13 15:13:22
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `tb_admin_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_admin_role`;
CREATE TABLE `tb_admin_role` (
  `adminId` varchar(10) NOT NULL,
  `roleNo` varchar(10) NOT NULL,
  PRIMARY KEY (`adminId`,`roleNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_admin_role
-- ----------------------------
INSERT INTO `tb_admin_role` VALUES ('1', '00000001');

-- ----------------------------
-- Table structure for `tb_role_menu`
-- ----------------------------
DROP TABLE IF EXISTS `tb_role_menu`;
CREATE TABLE `tb_role_menu` (
  `sysMenuNo` varchar(20) NOT NULL,
  `sysRoleNo` varchar(20) NOT NULL,
  PRIMARY KEY (`sysMenuNo`,`sysRoleNo`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_role_menu
-- ----------------------------
INSERT INTO `tb_role_menu` VALUES ('10', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-10', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-10-10', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-10-11', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-10-12', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-10-13', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-12', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-12-10', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-12-11', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-12-12', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-12-13', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-12-14', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-13', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-13-10', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-13-11', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-13-12', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-13-13', '00000001');
INSERT INTO `tb_role_menu` VALUES ('10-13-14', '00000001');

-- ----------------------------
-- Table structure for `tb_sys_admin`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_admin`;
CREATE TABLE `tb_sys_admin` (
  `id` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `adminName` varchar(10) DEFAULT NULL COMMENT '管理员的名字',
  `account` varchar(20) DEFAULT NULL COMMENT '账号',
  `password` varchar(50) DEFAULT NULL COMMENT '密码',
  `useStatus` char(2) DEFAULT NULL COMMENT '使用的状态(00禁用,01可用)',
  `createtime` datetime DEFAULT NULL COMMENT '创建日期',
  PRIMARY KEY (`id`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sys_admin
-- ----------------------------
INSERT INTO `tb_sys_admin` VALUES ('0000000001', 'admin', 'admin', '7a4937e7f8ea7793e48034396a6e8e6d', '01', '2018-07-13 14:51:23');

-- ----------------------------
-- Table structure for `tb_sys_menu`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_menu`;
CREATE TABLE `tb_sys_menu` (
  `sysMenuId` int(10) unsigned zerofill NOT NULL AUTO_INCREMENT,
  `sysMenuNo` varchar(20) DEFAULT NULL,
  `sysMenuName` varchar(20) DEFAULT NULL,
  `sysMenuUrl` varchar(200) DEFAULT NULL,
  `parentNo` varchar(20) DEFAULT NULL,
  `levelCode` char(2) DEFAULT NULL,
  `useStatus` char(2) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `sysMenuIconCls` varchar(50) DEFAULT NULL,
  `sysMenuExecuteJs` varchar(255) DEFAULT NULL,
  `sysMenuOpenStyle` char(2) DEFAULT NULL,
  PRIMARY KEY (`sysMenuId`)
) ENGINE=InnoDB AUTO_INCREMENT=19 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sys_menu
-- ----------------------------
INSERT INTO `tb_sys_menu` VALUES ('0000000001', '10', '系统管理', null, '0', '00', '01', '2018-07-13 15:13:09', null, null, null);
INSERT INTO `tb_sys_menu` VALUES ('0000000002', '10-10', '系统菜单管理', '/sysMenu/initQuery', '10', '01', '01', '2018-07-13 15:13:09', null, null, '01');
INSERT INTO `tb_sys_menu` VALUES ('0000000003', '10-12', '系统用户管理', '/admin/initQuery', '10', '01', '01', '2018-07-13 15:13:09', null, null, '01');
INSERT INTO `tb_sys_menu` VALUES ('0000000004', '10-13', '系统角色管理', '/sysRole/initQuery', '10', '01', '01', '2018-07-13 15:13:09', null, null, '01');
INSERT INTO `tb_sys_menu` VALUES ('0000000005', '10-10-10', '添加', '/sysMenu/initAdd', '10-10', '04', '01', '2018-07-13 15:13:09', 'icon-add', null, '01');
INSERT INTO `tb_sys_menu` VALUES ('0000000006', '10-10-11', '修改', '/sysMenu/initUpdate', '10-10', '04', '01', '2018-07-13 15:13:09', 'icon-edit', '01', '01');
INSERT INTO `tb_sys_menu` VALUES ('0000000007', '10-10-12', '删除', '/sysMenu/delete', '10-10', '04', '01', '2018-07-13 15:13:09', 'icon-remove', '02', '01');
INSERT INTO `tb_sys_menu` VALUES ('0000000008', '10-10-13', '查看', '/sysMenu/initQueryDetail', '10-10', '04', '01', '2018-07-13 15:13:09', 'icon-search', '01', '01');
INSERT INTO `tb_sys_menu` VALUES ('0000000009', '10-12-10', '添加', '/admin/initAdd', '10-12', '04', '01', '2018-07-13 15:13:09', 'icon-add', null, '01');
INSERT INTO `tb_sys_menu` VALUES ('0000000010', '10-12-11', '修改', '/admin/initUpdate', '10-12', '04', '01', '2018-07-13 15:13:09', 'icon-edit', '01', '01');
INSERT INTO `tb_sys_menu` VALUES ('0000000011', '10-12-12', '删除', '/admin/delete', '10-12', '04', '01', '2018-07-13 15:13:09', 'icon-remove', '02', '01');
INSERT INTO `tb_sys_menu` VALUES ('0000000012', '10-12-13', '查看', '/admin/initQueryDetail', '10-12', '04', '01', '2018-07-13 15:13:09', 'icon-search', '01', '01');
INSERT INTO `tb_sys_menu` VALUES ('0000000013', '10-12-14', '设置角色', '/admin/initSetRole', '10-12', '04', '01', '2018-07-13 15:13:09', 'icon-edit', '01', '01');
INSERT INTO `tb_sys_menu` VALUES ('0000000014', '10-13-10', '添加', '/sysRole/initAdd', '10-13', '04', '01', '2018-07-13 15:13:09', 'icon-add', null, '01');
INSERT INTO `tb_sys_menu` VALUES ('0000000015', '10-13-11', '修改', '/sysRole/initUpdate', '10-13', '04', '01', '2018-07-13 15:13:09', 'icon-edit', '01', '01');
INSERT INTO `tb_sys_menu` VALUES ('0000000016', '10-13-12', '删除', '/sysRole/delete', '10-13', '04', '01', '2018-07-13 15:13:09', 'icon-remove', '02', '01');
INSERT INTO `tb_sys_menu` VALUES ('0000000017', '10-13-13', '查看', '/sysRole /initQueryDetail', '10-13', '04', '01', '2018-07-13 15:13:09', 'icon-search', '01', '01');
INSERT INTO `tb_sys_menu` VALUES ('0000000018', '10-13-14', '设置权限', '/sysRole /initSetAuthority', '10-13', '04', '01', '2018-07-13 15:13:09', 'icon-edit', '01', '01');

-- ----------------------------
-- Table structure for `tb_sys_role`
-- ----------------------------
DROP TABLE IF EXISTS `tb_sys_role`;
CREATE TABLE `tb_sys_role` (
  `roleId` int(10) NOT NULL AUTO_INCREMENT COMMENT '主键id',
  `roleNo` varchar(10) DEFAULT NULL COMMENT '角色编号',
  `roleName` varchar(50) DEFAULT NULL COMMENT '角色名',
  `useStatus` char(2) DEFAULT NULL,
  `createTime` datetime DEFAULT NULL,
  `remark` varchar(200) DEFAULT NULL,
  PRIMARY KEY (`roleId`)
) ENGINE=InnoDB AUTO_INCREMENT=2 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of tb_sys_role
-- ----------------------------
INSERT INTO `tb_sys_role` VALUES ('1', '00000001', '系统管理员', '01', '2018-07-13 14:51:23', null);
