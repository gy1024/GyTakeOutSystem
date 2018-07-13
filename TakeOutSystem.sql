/*
Navicat MySQL Data Transfer

Source Server         : php
Source Server Version : 50553
Source Host           : localhost:3306
Source Database       : elb

Target Server Type    : MYSQL
Target Server Version : 50553
File Encoding         : 65001

Date: 2018-07-13 17:34:25
*/

SET FOREIGN_KEY_CHECKS=0;

-- ----------------------------
-- Table structure for `business`
-- ----------------------------
DROP TABLE IF EXISTS `business`;
CREATE TABLE `business` (
  `business_id` int(5) NOT NULL AUTO_INCREMENT COMMENT '商家id',
  `business_name` varchar(20) NOT NULL COMMENT '商家名称',
  `business_picture` varchar(2000) DEFAULT NULL COMMENT '商家图片',
  `business_username` varchar(20) NOT NULL COMMENT '商家账户名',
  `business_password` varchar(20) NOT NULL COMMENT '商家密码',
  PRIMARY KEY (`business_id`)
) ENGINE=InnoDB AUTO_INCREMENT=10008 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of business
-- ----------------------------
INSERT INTO `business` VALUES ('10001', '华莱士炸鸡汉堡', null, 'hls', '123123');
INSERT INTO `business` VALUES ('10002', '张亮麻辣烫', null, 'zlmlt', '123123');
INSERT INTO `business` VALUES ('10003', '蓉李记', null, 'rlj', '123123');
INSERT INTO `business` VALUES ('10004', '杨铭宇黄焖鸡米饭', '', 'ymyhmjmf', '123123');
INSERT INTO `business` VALUES ('10005', '麦当劳', null, 'mdl', '123123');
INSERT INTO `business` VALUES ('10006', '必胜客', null, 'bsk', '123123');
INSERT INTO `business` VALUES ('10007', '吉野家', null, 'jyj', '123123');

-- ----------------------------
-- Table structure for `commodity`
-- ----------------------------
DROP TABLE IF EXISTS `commodity`;
CREATE TABLE `commodity` (
  `commodity_id` int(9) NOT NULL AUTO_INCREMENT COMMENT '商品id',
  `commodity_name` varchar(20) NOT NULL COMMENT '商品名称',
  `commodity_price` double(6,2) NOT NULL COMMENT '商品价格',
  `commodity_picture` varchar(2000) DEFAULT NULL COMMENT '商品图片',
  `business_id` int(6) NOT NULL COMMENT '商家id',
  PRIMARY KEY (`commodity_id`),
  KEY `buid` (`business_id`) USING BTREE,
  CONSTRAINT `commodity_ibfk_1` FOREIGN KEY (`business_id`) REFERENCES `business` (`business_id`)
) ENGINE=InnoDB AUTO_INCREMENT=1000040 DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of commodity
-- ----------------------------
INSERT INTO `commodity` VALUES ('1000001', '香辣鸡腿堡', '10.00', null, '10001');
INSERT INTO `commodity` VALUES ('1000002', '鳕鱼堡', '13.00', null, '10001');
INSERT INTO `commodity` VALUES ('1000003', '鸡肉卷', '12.00', null, '10001');
INSERT INTO `commodity` VALUES ('1000004', '奥尔良烤翅', '15.00', null, '10001');
INSERT INTO `commodity` VALUES ('1000005', '鸡米花', '15.00', null, '10001');
INSERT INTO `commodity` VALUES ('1000006', '小杯可乐', '5.00', null, '10001');
INSERT INTO `commodity` VALUES ('1000007', '中杯可乐', '7.00', null, '10001');
INSERT INTO `commodity` VALUES ('1000008', '大杯可乐', '9.00', null, '10001');
INSERT INTO `commodity` VALUES ('1000009', '鱼豆腐', '2.00', null, '10002');
INSERT INTO `commodity` VALUES ('1000010', '娃娃菜', '1.80', null, '10002');
INSERT INTO `commodity` VALUES ('1000011', '西兰花', '1.80', null, '10002');
INSERT INTO `commodity` VALUES ('1000012', '金针菇', '2.00', null, '10002');
INSERT INTO `commodity` VALUES ('1000013', '撒尿牛丸', '2.00', null, '10002');
INSERT INTO `commodity` VALUES ('1000014', '培根', '2.00', null, '10002');
INSERT INTO `commodity` VALUES ('1000015', '午餐肉', '2.00', null, '10002');
INSERT INTO `commodity` VALUES ('1000016', '蟹排', '2.00', null, '10002');
INSERT INTO `commodity` VALUES ('1000017', '方便面', '2.00', null, '10002');
INSERT INTO `commodity` VALUES ('1000018', '粉丝', '2.00', null, '10002');
INSERT INTO `commodity` VALUES ('1000019', '麻辣', '0.50', null, '10002');
INSERT INTO `commodity` VALUES ('1000020', '微辣', '0.50', null, '10002');
INSERT INTO `commodity` VALUES ('1000021', '不辣', '0.50', null, '10002');
INSERT INTO `commodity` VALUES ('1000022', '蓉城担担面', '15.00', null, '10003');
INSERT INTO `commodity` VALUES ('1000023', '重庆小面', '15.00', null, '10003');
INSERT INTO `commodity` VALUES ('1000024', '鸡丝凉面', '12.00', null, '10003');
INSERT INTO `commodity` VALUES ('1000025', '老成都酸辣粉', '15.00', null, '10003');
INSERT INTO `commodity` VALUES ('1000026', '钵钵鸡', '20.00', null, '10003');
INSERT INTO `commodity` VALUES ('1000027', '黄焖鸡米饭', '21.00', '', '10004');
INSERT INTO `commodity` VALUES ('1000028', '黄焖鱼豆腐', '15.00', '', '10004');
INSERT INTO `commodity` VALUES ('1000029', '培根炒双蛋堡', '14.00', null, '10005');
INSERT INTO `commodity` VALUES ('1000030', '皮蛋鸡肉粥', '8.00', null, '10005');
INSERT INTO `commodity` VALUES ('1000031', '猪柳蛋堡', '15.00', null, '10005');
INSERT INTO `commodity` VALUES ('1000032', '脆香油条', '6.00', null, '10005');
INSERT INTO `commodity` VALUES ('1000033', '超级至尊披萨', '98.00', null, '10006');
INSERT INTO `commodity` VALUES ('1000034', '海鲜至尊披萨', '128.00', null, '10006');
INSERT INTO `commodity` VALUES ('1000035', '欧式培根炒饭', '30.00', null, '10006');
INSERT INTO `commodity` VALUES ('1000036', '吉味双拼饭', '20.00', null, '10007');
INSERT INTO `commodity` VALUES ('1000037', '招牌牛肉饭', '28.50', null, '10007');
INSERT INTO `commodity` VALUES ('1000039', 'asdf', '8.00', null, '10002');

-- ----------------------------
-- Table structure for `customer`
-- ----------------------------
DROP TABLE IF EXISTS `customer`;
CREATE TABLE `customer` (
  `customer_id` int(5) NOT NULL COMMENT '用户id',
  `customer_name` varchar(20) DEFAULT NULL COMMENT '用户昵称（姓名）',
  `customer_address` varchar(40) DEFAULT NULL COMMENT '收货地址',
  `customer_username` varchar(20) NOT NULL COMMENT '用户账号名',
  `customer_password` varchar(20) NOT NULL COMMENT '用户密码',
  PRIMARY KEY (`customer_id`),
  KEY `customer_address` (`customer_address`) USING BTREE
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of customer
-- ----------------------------
INSERT INTO `customer` VALUES ('100001', '小明', null, 'xiaoming', '123456');
INSERT INTO `customer` VALUES ('100002', '古月', null, 'hujiajun', 'hujiajun');
INSERT INTO `customer` VALUES ('100003', null, null, 'maomao', 'asd');
INSERT INTO `customer` VALUES ('100004', null, null, 'xuyuan', '123');
INSERT INTO `customer` VALUES ('100006', null, null, 'xyssb', '123');
INSERT INTO `customer` VALUES ('100007', null, null, 'aaa', 'aaa');
INSERT INTO `customer` VALUES ('100008', null, null, 'wanglei', '123123');

-- ----------------------------
-- Table structure for `orderform`
-- ----------------------------
DROP TABLE IF EXISTS `orderform`;
CREATE TABLE `orderform` (
  `order_id` int(8) NOT NULL COMMENT '订单id',
  `business_id` int(10) NOT NULL COMMENT '商家id',
  `customer_id` int(10) NOT NULL COMMENT '用户id',
  `order` varchar(5000) NOT NULL COMMENT '订单详情',
  `customer_address` varchar(40) DEFAULT NULL COMMENT '收货地址',
  `start_time` bigint(32) NOT NULL COMMENT '订单开始时间',
  `end_time` bigint(32) DEFAULT NULL COMMENT '订单完成时间',
  `status` int(1) NOT NULL COMMENT '订单状态',
  PRIMARY KEY (`order_id`),
  KEY `business_id` (`business_id`) USING BTREE,
  KEY `customer_id` (`customer_id`) USING BTREE,
  KEY `customer_address` (`customer_address`) USING BTREE,
  CONSTRAINT `orderform_ibfk_1` FOREIGN KEY (`business_id`) REFERENCES `business` (`business_id`),
  CONSTRAINT `orderform_ibfk_2` FOREIGN KEY (`customer_id`) REFERENCES `customer` (`customer_id`),
  CONSTRAINT `orderform_ibfk_3` FOREIGN KEY (`customer_address`) REFERENCES `customer` (`customer_address`)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;

-- ----------------------------
-- Records of orderform
-- ----------------------------
INSERT INTO `orderform` VALUES ('10000005', '10003', '100001', '{\"order\":{{\"business_id\":10003,\"commodity_id\":1000022,\"commodity_name\":\"蓉城担担面\",\"commodity_price\":15.0}:3,{\"business_id\":10003,\"commodity_id\":1000024,\"commodity_name\":\"鸡丝凉面\",\"commodity_price\":12.0}:4,{\"business_id\":10003,\"commodity_id\":1000023,\"commodity_name\":\"重庆小面\",\"commodity_price\":15.0}:3}}', null, '1531393106187', '0', '0');
INSERT INTO `orderform` VALUES ('10000006', '10001', '100001', '{\"order\":{{\"business_id\":10001,\"commodity_id\":1000002,\"commodity_name\":\"鳕鱼堡\",\"commodity_price\":13.0}:3,{\"business_id\":10001,\"commodity_id\":1000001,\"commodity_name\":\"香辣鸡腿堡\",\"commodity_price\":10.0}:3}}', null, '1531393106230', '0', '0');
INSERT INTO `orderform` VALUES ('10000007', '10004', '100001', '{\"order\":{{\"business_id\":10004,\"commodity_id\":1000028,\"commodity_name\":\"黄焖鱼豆腐\",\"commodity_picture\":\"\",\"commodity_price\":15.0}:4}}', null, '1531393106255', '0', '0');
INSERT INTO `orderform` VALUES ('10000008', '10001', '100001', '{\"order\":{{\"business_id\":10001,\"commodity_id\":1000001,\"commodity_name\":\"香辣鸡腿堡\",\"commodity_price\":10.0}:3,{\"business_id\":10001,\"commodity_id\":1000002,\"commodity_name\":\"鳕鱼堡\",\"commodity_price\":13.0}:1}}', null, '1531394674346', '0', '0');
INSERT INTO `orderform` VALUES ('10000009', '10003', '100001', '{\"order\":{{\"business_id\":10003,\"commodity_id\":1000022,\"commodity_name\":\"蓉城担担面\",\"commodity_price\":15.0}:5}}', null, '1531394674378', '0', '0');
INSERT INTO `orderform` VALUES ('10000010', '10001', '100001', '{\"order\":{{\"business_id\":10001,\"commodity_id\":1000001,\"commodity_name\":\"香辣鸡腿堡\",\"commodity_price\":10.0}:8}}', null, '1531443874112', '0', '0');
INSERT INTO `orderform` VALUES ('10000011', '10001', '100001', '{\"order\":{{\"business_id\":10001,\"commodity_id\":1000001,\"commodity_name\":\"香辣鸡腿堡\",\"commodity_price\":10.0}:1}}', null, '1531444064597', '0', '0');
INSERT INTO `orderform` VALUES ('10000012', '10001', '100001', '{\"order\":{{\"business_id\":10001,\"commodity_id\":1000001,\"commodity_name\":\"香辣鸡腿堡\",\"commodity_price\":10.0}:1}}', null, '1531444253425', '0', '0');
INSERT INTO `orderform` VALUES ('10000013', '10001', '100001', '{\"order\":{{\"business_id\":10001,\"commodity_id\":1000002,\"commodity_name\":\"鳕鱼堡\",\"commodity_price\":13.0}:3,{\"business_id\":10001,\"commodity_id\":1000001,\"commodity_name\":\"香辣鸡腿堡\",\"commodity_price\":10.0}:1,{\"business_id\":10001,\"commodity_id\":1000003,\"commodity_name\":\"鸡肉卷\",\"commodity_price\":12.0}:1}}', null, '1531444324955', '0', '0');
INSERT INTO `orderform` VALUES ('10000014', '10001', '100001', '{\"order\":{{\"business_id\":10001,\"commodity_id\":1000007,\"commodity_name\":\"中杯可乐\",\"commodity_price\":7.0}:4,{\"business_id\":10001,\"commodity_id\":1000006,\"commodity_name\":\"小杯可乐\",\"commodity_price\":5.0}:4,{\"business_id\":10001,\"commodity_id\":1000004,\"commodity_name\":\"奥尔良烤翅\",\"commodity_price\":15.0}:2}}', null, '1531444357300', '0', '0');
INSERT INTO `orderform` VALUES ('10000015', '10001', '100001', '{\"order\":{{\"business_id\":10001,\"commodity_id\":1000007,\"commodity_name\":\"中杯可乐\",\"commodity_price\":7.0}:4,{\"business_id\":10001,\"commodity_id\":1000005,\"commodity_name\":\"鸡米花\",\"commodity_price\":15.0}:5,{\"business_id\":10001,\"commodity_id\":1000002,\"commodity_name\":\"鳕鱼堡\",\"commodity_price\":13.0}:4,{\"business_id\":10001,\"commodity_id\":1000003,\"commodity_name\":\"鸡肉卷\",\"commodity_price\":12.0}:3,{\"business_id\":10001,\"commodity_id\":1000001,\"commodity_name\":\"香辣鸡腿堡\",\"commodity_price\":10.0}:17,{\"business_id\":10001,\"commodity_id\":1000008,\"commodity_name\":\"大杯可乐\",\"commodity_price\":9.0}:4,{\"business_id\":10001,\"commodity_id\":1000006,\"commodity_name\":\"小杯可乐\",\"commodity_price\":5.0}:6,{\"business_id\":10001,\"commodity_id\":1000004,\"commodity_name\":\"奥尔良烤翅\",\"commodity_price\":15.0}:3}}', null, '1531444389971', '0', '0');
INSERT INTO `orderform` VALUES ('10000016', '10001', '100001', '{\"order\":{{\"business_id\":10001,\"commodity_id\":1000008,\"commodity_name\":\"大杯可乐\",\"commodity_price\":9.0}:10,{\"business_id\":10001,\"commodity_id\":1000006,\"commodity_name\":\"小杯可乐\",\"commodity_price\":5.0}:17,{\"business_id\":10001,\"commodity_id\":1000007,\"commodity_name\":\"中杯可乐\",\"commodity_price\":7.0}:9}}', null, '1531444411307', '0', '0');
INSERT INTO `orderform` VALUES ('10000017', '10001', '100001', '{\"order\":{{\"business_id\":10001,\"commodity_id\":1000002,\"commodity_name\":\"鳕鱼堡\",\"commodity_price\":13.0}:4,{\"business_id\":10001,\"commodity_id\":1000001,\"commodity_name\":\"香辣鸡腿堡\",\"commodity_price\":10.0}:4}}', null, '1531444568033', '0', '0');
INSERT INTO `orderform` VALUES ('10000018', '10002', '100001', '{\"order\":{{\"business_id\":10002,\"commodity_id\":1000011,\"commodity_name\":\"西兰花\",\"commodity_price\":1.8}:3,{\"business_id\":10002,\"commodity_id\":1000009,\"commodity_name\":\"鱼豆腐\",\"commodity_price\":2.0}:3,{\"business_id\":10002,\"commodity_id\":1000010,\"commodity_name\":\"娃娃菜\",\"commodity_price\":1.8}:3,{\"business_id\":10002,\"commodity_id\":1000012,\"commodity_name\":\"金针菇\",\"commodity_price\":2.0}:3}}', null, '1531444568047', '0', '0');
INSERT INTO `orderform` VALUES ('10000019', '10001', '100001', '{\"order\":{{\"business_id\":10001,\"commodity_id\":1000007,\"commodity_name\":\"中杯可乐\",\"commodity_price\":7.0}:3,{\"business_id\":10001,\"commodity_id\":1000004,\"commodity_name\":\"奥尔良烤翅\",\"commodity_price\":15.0}:4,{\"business_id\":10001,\"commodity_id\":1000003,\"commodity_name\":\"鸡肉卷\",\"commodity_price\":12.0}:11,{\"business_id\":10001,\"commodity_id\":1000008,\"commodity_name\":\"大杯可乐\",\"commodity_price\":9.0}:3,{\"business_id\":10001,\"commodity_id\":1000002,\"commodity_name\":\"鳕鱼堡\",\"commodity_price\":13.0}:7,{\"business_id\":10001,\"commodity_id\":1000006,\"commodity_name\":\"小杯可乐\",\"commodity_price\":5.0}:3,{\"business_id\":10001,\"commodity_id\":1000005,\"commodity_name\":\"鸡米花\",\"commodity_price\":15.0}:3}}', null, '1531444639719', '0', '0');
INSERT INTO `orderform` VALUES ('10000020', '10002', '100001', '{\"order\":{{\"business_id\":10002,\"commodity_id\":1000013,\"commodity_name\":\"撒尿牛丸\",\"commodity_price\":2.0}:5,{\"business_id\":10002,\"commodity_id\":1000012,\"commodity_name\":\"金针菇\",\"commodity_price\":2.0}:5,{\"business_id\":10002,\"commodity_id\":1000011,\"commodity_name\":\"西兰花\",\"commodity_price\":1.8}:5,{\"business_id\":10002,\"commodity_id\":1000014,\"commodity_name\":\"培根\",\"commodity_price\":2.0}:3}}', null, '1531444639736', '0', '0');
INSERT INTO `orderform` VALUES ('10000021', '10001', '100001', '{\"order\":{{\"business_id\":10001,\"commodity_id\":1000003,\"commodity_name\":\"鸡肉卷\",\"commodity_price\":12.0}:1,{\"business_id\":10001,\"commodity_id\":1000001,\"commodity_name\":\"香辣鸡腿堡\",\"commodity_price\":10.0}:1}}', null, '1531444758273', '0', '0');
INSERT INTO `orderform` VALUES ('10000022', '10001', '100001', '{\"order\":{{\"business_id\":10001,\"commodity_id\":1000001,\"commodity_name\":\"香辣鸡腿堡\",\"commodity_price\":10.0}:1}}', null, '1531444783821', '0', '0');
INSERT INTO `orderform` VALUES ('10000023', '10001', '100001', '{\"order\":{{\"business_id\":10001,\"commodity_id\":1000001,\"commodity_name\":\"香辣鸡腿堡\",\"commodity_price\":10.0}:1}}', null, '1531444823071', '0', '0');
INSERT INTO `orderform` VALUES ('10000024', '10002', '100001', '{\"order\":{{\"business_id\":10002,\"commodity_id\":1000012,\"commodity_name\":\"金针菇\",\"commodity_price\":2.0}:3,{\"business_id\":10002,\"commodity_id\":1000010,\"commodity_name\":\"娃娃菜\",\"commodity_price\":1.8}:5,{\"business_id\":10002,\"commodity_id\":1000009,\"commodity_name\":\"鱼豆腐\",\"commodity_price\":2.0}:4,{\"business_id\":10002,\"commodity_id\":1000011,\"commodity_name\":\"西兰花\",\"commodity_price\":1.8}:4}}', null, '1531444937271', '0', '0');
INSERT INTO `orderform` VALUES ('10000025', '10005', '100001', '{\"order\":{{\"business_id\":10005,\"commodity_id\":1000030,\"commodity_name\":\"皮蛋鸡肉粥\",\"commodity_price\":8.0}:4}}', null, '1531447623355', '0', '0');
INSERT INTO `orderform` VALUES ('10000026', '10001', '100001', '{\"order\":{{\"business_id\":10001,\"commodity_id\":1000001,\"commodity_name\":\"香辣鸡腿堡\",\"commodity_price\":10.0}:3}}', null, '1531470749406', '0', '0');
INSERT INTO `orderform` VALUES ('10000027', '10001', '100001', '{\"order\":{{\"business_id\":10001,\"commodity_id\":1000001,\"commodity_name\":\"香辣鸡腿堡\",\"commodity_price\":10.0}:6}}', null, '1531470789137', '0', '0');
INSERT INTO `orderform` VALUES ('10000028', '10002', '100001', '{\"order\":{{\"business_id\":10002,\"commodity_id\":1000010,\"commodity_name\":\"娃娃菜\",\"commodity_price\":1.8}:3,{\"business_id\":10002,\"commodity_id\":1000011,\"commodity_name\":\"西兰花\",\"commodity_price\":1.8}:3,{\"business_id\":10002,\"commodity_id\":1000009,\"commodity_name\":\"鱼豆腐\",\"commodity_price\":2.0}:3,{\"business_id\":10002,\"commodity_id\":1000012,\"commodity_name\":\"金针菇\",\"commodity_price\":2.0}:2}}', null, '1531470789158', '0', '0');
INSERT INTO `orderform` VALUES ('10000029', '10007', '100001', '{\"order\":{{\"business_id\":10007,\"commodity_id\":1000036,\"commodity_name\":\"吉味双拼饭\",\"commodity_price\":20.0}:5}}', null, '1531470789175', '0', '0');
