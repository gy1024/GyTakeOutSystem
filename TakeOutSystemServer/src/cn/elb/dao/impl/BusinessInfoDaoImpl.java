package cn.elb.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;

import cn.elb.dao.BusinessInfoDao;
import cn.elb.entity.BusinessInfo;
import cn.elb.entity.CommodityInfo;
import cn.elb.entity.Order;
import cn.elb.entity.Orderform;
import cn.elb.util.JDBCutil;

public class BusinessInfoDaoImpl implements BusinessInfoDao {
	/**
	 * 增加商户信息（商家注册：用户名，图片，账号，密码）
	 */
	@Override
	public int insert(BusinessInfo business) {
		String sql = "insert into business values ((select * from((select MAX(business_id)+1 from business)a)),?,?,?,?)";
		int result = JDBCutil.update(sql, business.getBusiness_name(), business.getBusiness_picture(),
				business.getBusiness_username(), business.getBusiness_password());
		if (result > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * 增加商品(商品id，商品名称，商品价格，商品图片，商家id)
	 */
	@Override
	public int insert(CommodityInfo commodity) {
		String sql = "insert into commodity values ((select * from((select MAX(commodity_id)+1 from commodity)a)),?,?,?,?)";
		int result = JDBCutil.update(sql, commodity.getCommodity_name(), commodity.getCommodity_price(),
				commodity.getCommodity_picture(), commodity.getBusiness_id());
		if (result > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * 删除商品
	 */
	@Override
	public int delete(int commodity_id) {
		String sql = "delete from commodity where commodity_id=?";
		int result = JDBCutil.update(sql, commodity_id);
		if (result > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * 修改商家信息（密码、图片）
	 */
	@Override
	public int update(int business_id, String business_name, String business_picture, String business_username,
			String business_password) {
		String sql = "update business set business_name=?, business_picture=?, business_username=? , business_password=? where business_id=?";
		Object[] obj = { business_name, business_picture, business_username, business_password, business_id };
		int result = JDBCutil.update(sql, obj);
		if (result > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * 修改商品表
	 */
	@Override
	public int update(int commodity_id, String commodity_name, double commodity_price, String commodity_picture,
			int business_id) {
		String sql = "update commodity set commodity_name=?,commodity_price=?,commodity_picture=?,business_id=? where commodity_id=?";
		Object[] obj = { commodity_name, commodity_price, commodity_picture, business_id, commodity_id };
		int result = JDBCutil.update(sql, obj);
		if (result > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * 修改订单状态
	 */
	@Override
	public int update(int order_id, int status) {
		String sql = "update userinfo set status=? where order_id=?";
		Object[] obj = { status, order_id };
		int result = JDBCutil.update(sql, obj);
		if (result > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * 根据商家username查询商家所有的信息
	 */
	@Override
	public BusinessInfo selectBusinessInfo(String business_username) {
		Connection conn = JDBCutil.getConn();// 获取连接
		Statement stmt = null; // PreparedStatement是Statement的子接口
		ResultSet rs = null;
		BusinessInfo u = null;
		try {
			String sql = "select * from business where business_username='" + business_username + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int business_id = rs.getInt("business_id");
				String business_name = rs.getString("business_name");
				String business_picture = rs.getString("business_picture");
				String business_password = rs.getString("business_password");
				u = new BusinessInfo(business_id, business_name, business_picture, business_username,
						business_password);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.closeAll(stmt, rs, conn);
		}
		return u;
	}

	/**
	 * 根据商家id查询商家所有商品
	 */
	@Override
	public ArrayList<CommodityInfo> selectCommodityInfo(int business_id) {
		Connection conn = JDBCutil.getConn();// 获取连接
		Statement stmt = null; // PreparedStatement是Statement的子接口
		ResultSet rs = null;
		CommodityInfo u = null;
		ArrayList<CommodityInfo> list = new ArrayList<>();
		try {
			String sql = "select * from commodity where business_id=" + business_id;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int commodity_id = rs.getInt("commodity_id");
				String commodity_name = rs.getString("commodity_name");
				double commodity_price = rs.getDouble("commodity_price");
				String commodity_picture = rs.getString("commodity_picture");
				u = new CommodityInfo(commodity_id, commodity_name, commodity_price, commodity_picture, business_id);
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.closeAll(stmt, rs, conn);
		}
		return list;
	}

	/**
	 * 根据商家id查询所有订单
	 */
	@Override
	public ArrayList<Orderform> selectOrderformInfo(int business_id) {
		Connection conn = JDBCutil.getConn();// 获取连接
		Statement stmt = null; // PreparedStatement是Statement的子接口
		ResultSet rs = null;
		Orderform u = null;
		ArrayList<Orderform> list = new ArrayList<>();
		try {
			String sql = "SELECT o.order_id, b.business_name, c.customer_name, o.order, o.start_time, o.end_time, o.status FROM orderform o JOIN business b ON o.business_id = b.business_id JOIN customer c ON o.customer_id = c.customer_id WHERE b.business_id ="
					+ business_id;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int order_id = rs.getInt("order_id");
				int customer_id = rs.getInt("customer_id");
				String business_name = rs.getString("business_name");
				String customer_name = rs.getString("customer_name");
				String str = rs.getString("order");
				Long start_time = (Long) rs.getObject("start_time");
				Long end_time = (Long) rs.getObject("end_time");
				int status = rs.getInt("status");
				String customer_address = rs.getString("customer_address");
				Order order = JSON.parseObject(str, Order.class);
				u = new Orderform(order_id, business_id, customer_id, order, start_time, end_time, status,
						customer_address, business_name, customer_name);
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.closeAll(stmt, rs, conn);
		}
		return list;
	}

	/**
	 * 根据商家id查询所有订单,有时间限制
	 */
	@Override
	public ArrayList<Orderform> selectOrderformInfoTime(int business_id) {
		Connection conn = JDBCutil.getConn();// 获取连接
		Statement stmt = null; // PreparedStatement是Statement的子接口
		ResultSet rs = null;
		Orderform u = null;
		ArrayList<Orderform> list = new ArrayList<>();
		Long time = System.currentTimeMillis();
		try {
			String sql = "SELECT o.order_id,o.customer_id, b.business_name, c.customer_name, o.order, o.start_time, o.end_time, o.status FROM orderform o JOIN business b ON o.business_id = b.business_id JOIN customer c ON o.customer_id = c.customer_id WHERE b.business_id ="
					+ business_id + " and start_time >" + (time - 5000);
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int order_id = rs.getInt("order_id");
				int customer_id = rs.getInt("customer_id");
				String business_name = rs.getString("business_name");
				String customer_name = rs.getString("customer_name");
				String str = rs.getString("order");
				Long start_time = (Long) rs.getObject("start_time");
				Long end_time = (Long) rs.getObject("end_time");
				int status = rs.getInt("status");
				// String customer_address = rs.getString("customer_address");
				Order order = null;
				if (str != null) {
					order = JSON.parseObject(str, Order.class);
				}
				u = new Orderform(order_id, business_id, customer_id, order, start_time, end_time, status,
						business_name, customer_name);
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.closeAll(stmt, rs, conn);
		}
		return list;
	}
}
