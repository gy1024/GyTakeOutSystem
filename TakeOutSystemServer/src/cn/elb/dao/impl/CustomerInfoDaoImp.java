package cn.elb.dao.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;

import cn.elb.dao.CustomerInfoDao;
import cn.elb.entity.BusinessInfo;
import cn.elb.entity.CustomerInfo;
import cn.elb.entity.Order;
import cn.elb.entity.Orderform;
import cn.elb.entity.OrderformInfo;
import cn.elb.util.JDBCutil;

public class CustomerInfoDaoImp implements CustomerInfoDao {
	/**
	 * 增加用户信息（用户注册：用户名，账号，密码，用户地址）
	 * 
	 * @param customer
	 * @return
	 */
	// @Override
	// public int insert(CustomerInfo customer) {
	// String sql = "insert into customer values ((select * from((select
	// MAX(customer_id)+1 from customer)a)),?,?,?,?)";
	// int result = JDBCutil.update(sql, customer.getCustomer_name(),
	// customer.getCustomer_username(),
	// customer.getCustomer_password(), customer.getCustomer_address());
	// if (result > 0) {
	// return 1;
	// } else {
	// return -1;
	// }
	// }
	@Override
	public int insert(CustomerInfo customer) {
		String sql = "insert into customer(customer_id,customer_username,customer_password) values ((select * from((select MAX(customer_id)+1 from customer)a)),?,?)";
		int result = JDBCutil.update(sql, customer.getCustomer_username(), customer.getCustomer_password());
		if (result > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * 增加订单
	 * 
	 * @param orderform
	 * @return
	 */
	@Override
	public int insert(OrderformInfo orderform) {
		String sql = "insert into orderform values ((select * from((select MAX(order_id)+1 from orderform)a)),?,?,?,?,?,?,?)";
		int result = JDBCutil.update(sql, orderform.getBusiness_id(), orderform.getCustomer_id(),
				orderform.getOrder().toJson(), orderform.getCustomer_address(), orderform.getStart_time(),
				orderform.getEnd_time(), orderform.getStatus());
		if (result > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	/**
	 * 修改用户信息（用户名，密码，用户地址）
	 * 
	 * @param customer_id
	 * @param customer_name
	 * @param customer_username
	 * @param customer_password
	 * @return
	 */
	@Override
	public int update(int customer_id, String customer_name, String customer_username, String customer_password,
			String customer_address) {
		String sql = "update customer set  customer_name=?, customer_username=? , customer_password=?,customer_address=? where customer_id=?";
		Object[] obj = { customer_name, customer_username, customer_password, customer_address, customer_id };
		int result = JDBCutil.update(sql, obj);
		if (result > 0) {
			return 1;
		} else {
			return -1;
		}
	}

	// @Override
	// public int update(OrderformInfo orderform) {
	// return 0;
	// }
	/**
	 * 查询所有商家
	 * 
	 * @return
	 */
	@Override
	public ArrayList<BusinessInfo> selectAllBusinessInfo() {
		Connection conn = JDBCutil.getConn();// 获取连接
		Statement stmt = null; // PreparedStatement是Statement的子接口
		ResultSet rs = null;
		BusinessInfo u = null;
		ArrayList<BusinessInfo> list = new ArrayList<>();
		try {
			String sql = "select * from business";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int business_id = rs.getInt("business_id");
				String business_name = rs.getString("business_name");
				String business_picture = rs.getString("business_picture");
				String business_username = rs.getString("business_username");
				String business_password = rs.getString("business_password");
				u = new BusinessInfo(business_id, business_name, business_picture, business_username,
						business_password);
				list.add(u);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.closeAll(stmt, rs, conn);
		}
		return list;
	}

	// @Override
	// public ArrayList<CommodityInfo> selectCommodityInfo(int business_id) {
	//
	// return null;
	// }
	/**
	 * 根据用户id查询所有订单
	 * 
	 * @param customer_id
	 * @return
	 */
	@Override
	public ArrayList<Orderform> selectOrderformInfo(int customer_id) {
		Connection conn = JDBCutil.getConn();// 获取连接
		Statement stmt = null; // PreparedStatement是Statement的子接口
		ResultSet rs = null;
		Orderform u = null;
		ArrayList<Orderform> list = new ArrayList<>();
		try {
			String sql = "SELECT o.order_id, b.business_name, c.customer_name, o.order, o.start_time, o.end_time, o.status FROM orderform o JOIN business b ON o.business_id = b.business_id JOIN customer c ON o.customer_id = c.customer_id WHERE c.customer_id ="
					+ customer_id;
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int order_id = rs.getInt("order_id");
				int business_id = rs.getInt("business_id");
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
	 * 根据用户username查询用户所有信息
	 * 
	 * @param customer_username
	 * @return
	 */
	@Override
	public CustomerInfo selectCustomerInfo(String customer_username) {
		Connection conn = JDBCutil.getConn();// 获取连接
		Statement stmt = null; // PreparedStatement是Statement的子接口
		ResultSet rs = null;
		CustomerInfo u = null;
		try {
			String sql = "select * from customer where customer_username='" + customer_username + "'";
			stmt = conn.createStatement();
			rs = stmt.executeQuery(sql);
			while (rs.next()) {
				int customer_id = rs.getInt("customer_id");
				String customer_name = rs.getString("customer_name");
				String customer_password = rs.getString("customer_password");
				String customer_address = rs.getString("customer_address");
				u = new CustomerInfo(customer_id, customer_name, customer_username, customer_password,
						customer_address);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCutil.closeAll(stmt, rs, conn);
		}
		return u;
	}

	// /**
	// * 根据用户username查重
	// *
	// * @param customer_name
	// * @return
	// */
	// @Override
	// public int selectCustomerName(String customer_username) {
	// String sql = "select * FROM customer WHERE customer_username ='" +
	// customer_username + "'";
	// Connection conn = JDBCutil.getConn();// 获取连接
	// Statement stmt = null; // PreparedStatement是Statement的子接口
	// ResultSet rs = null;
	// CustomerInfo ci = null;
	// try {
	// stmt = conn.createStatement();
	// rs = stmt.executeQuery(sql);
	// while (rs.next()) {
	// String username = rs.getString("customer_username");
	// String password = rs.getString("customer_password");
	// ci = new CustomerInfo(username, password);
	// }
	// } catch (SQLException e) {
	// e.printStackTrace();
	// }
	// if (ci != null) {
	// return 1;
	// } else {
	// return -1;
	// }
	// }

}
