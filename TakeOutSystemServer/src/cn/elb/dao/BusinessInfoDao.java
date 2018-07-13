package cn.elb.dao;

import java.util.ArrayList;
import cn.elb.entity.BusinessInfo;
import cn.elb.entity.CommodityInfo;
import cn.elb.entity.Orderform;

public interface BusinessInfoDao {
	// -----------增---------
	/**
	 * 增加商户信息（商家注册：用户名，图片，账号，密码）
	 * 
	 * @param business
	 *            商家对象
	 * @return
	 */
	public int insert(BusinessInfo business);

	/**
	 * 增加商品
	 * 
	 * @param commodity
	 * @return
	 */
	public int insert(CommodityInfo commodity);

	// -----------删---------
	/**
	 * 删除商品
	 * 
	 * @param commodity_id
	 *            商品ID
	 * @return
	 */
	public int delete(int commodity_id);

	// -----------改---------
	/**
	 * 修改商家信息（密码、图片）
	 * 
	 * @param business_id
	 * @param business_name
	 * @param business_picture
	 * @param business_username
	 * @param business_password
	 * @return
	 */
	public int update(int business_id, String business_name, String business_picture, String business_username,
			String business_password);

	/**
	 * 修改商品表
	 * 
	 * @param commodity_id
	 * @param commodity_name
	 * @param commodity_price
	 * @param commodity_picture
	 * @param business_id
	 * @return
	 */
	public int update(int commodity_id, String commodity_name, double commodity_price, String commodity_picture,
			int business_id);

	/**
	 * 修改订单状态
	 * 
	 * @param order_id
	 *            订单编号
	 * @param status
	 *            订单状态
	 * @return
	 */
	public int update(int order_id, int status);

	// -----------查---------
	/**
	 * 根据商家username查询商家所有信息
	 * 
	 * @param business
	 * @return
	 */
	public BusinessInfo selectBusinessInfo(String business_username);

	/**
	 * 根据商家id查询商家所有商品
	 * 
	 * @param business_id
	 * @return
	 */
	public ArrayList<CommodityInfo> selectCommodityInfo(int business_id);

	/**
	 * 根据商家id查询所有订单
	 * 
	 * @param business_id
	 * @return
	 */
	public ArrayList<Orderform> selectOrderformInfo(int business_id);

	/**
	 * 根据商家id查询所有订单，有时间限制
	 * 
	 * @param business_id
	 * @return
	 */
	public ArrayList<Orderform> selectOrderformInfoTime(int business_id);

}
