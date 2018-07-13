package cn.elb.dao;

import java.util.ArrayList;

import cn.elb.entity.BusinessInfo;
import cn.elb.entity.CustomerInfo;
import cn.elb.entity.Orderform;
import cn.elb.entity.OrderformInfo;

public interface CustomerInfoDao {
	// -----------增---------
	/**
	 * 增加用户信息（用户注册：用户名，账号，密码,用户地址）
	 * 
	 * @param customer
	 * @return
	 */
	public int insert(CustomerInfo customer);

	/**
	 * 增加订单
	 * 
	 * @param orderform
	 * @return
	 */
	public int insert(OrderformInfo orderform);

	// -----------改---------
	/**
	 * 修改用户信息（用户名，密码，用户地址）
	 * 
	 * @param customer_id
	 * @param customer_name
	 * @param customer_username
	 * @param customer_password
	 * @return
	 */
	public int update(int customer_id, String customer_name, String customer_username, String customer_password,
			String customer_address);

	// /**
	// * 修改订单状态
	// *
	// * @param orderform
	// * @return
	// */
	// public int update(OrderformInfo orderform);

	// -----------查---------
	/**
	 * 查询所有商家
	 * 
	 * @return
	 */
	public ArrayList<BusinessInfo> selectAllBusinessInfo();

	// /**
	// * 根据商家id查询商家所有商品
	// *
	// * @param business_id
	// * @return
	// */
	// public ArrayList<CommodityInfo> selectCommodityInfo(int business_id);

	/**
	 * 根据用户id查询所有订单
	 * 
	 * @param customer_id
	 * @return
	 */
	public ArrayList<Orderform> selectOrderformInfo(int customer_id);

	/**
	 * 根据用户username查询用户所有信息
	 * 
	 * @param customer_username
	 * @return
	 */
	public CustomerInfo selectCustomerInfo(String customer_username);

	// /**
	// * 根据用户name查重
	// *
	// * @param customer_name
	// * @return
	// */
	// public int selectCustomerName(String customer_name);
}
