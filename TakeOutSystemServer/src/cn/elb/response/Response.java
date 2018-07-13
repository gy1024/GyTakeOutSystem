package cn.elb.response;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import cn.elb.dao.impl.BusinessInfoDaoImpl;
import cn.elb.dao.impl.CustomerInfoDaoImp;
import cn.elb.entity.BusinessInfo;
import cn.elb.entity.CommodityInfo;
import cn.elb.entity.CustomerInfo;
import cn.elb.entity.DataPackage;
import cn.elb.entity.Goods;
import cn.elb.entity.Order;
import cn.elb.entity.Orderform;
import cn.elb.entity.OrderformInfo;

/**
 * 请求响应 请求类型 请求代码 涉及数据表</br>
 * 初始化请求 00 BusinessList,CommodityList——商家对象，包含商家对应的商品链表</br>
 * 商家登录 01 BusinessInfo</br>
 * 用户登录 02 CustomerInfo</br>
 * 商家信息修改 03 BusinessInfo</br>
 * 用户信息修改 04 CustomerInfo</br>
 * 订单提交 05 OrderformInfo</br>
 * 订单修改 06 OrderformInfo</br>
 * 商家查询订单 07 OrderformInfoList</br>
 * 用户查询订单 08 OrderformInfoList</br>
 * 商家,用户注册</br>
 * 
 * @author Mao_TP
 *
 */
// TODO 改英文
public class Response {
	private static CustomerInfoDaoImp cimp = new CustomerInfoDaoImp();
	private static BusinessInfoDaoImpl bimp = new BusinessInfoDaoImpl();

	/**
	 * 
	 * @param request
	 * @param o
	 * @return
	 */
	public static String responseQuest(String json) {
		String str = null;
		// 解包
		DataPackage data = JSON.parseObject(json, DataPackage.class);
		DataPackage data2 = null;
		JSONObject jo = null;
		JSONObject jsonObj = null;
		switch (data.getRequest()) {
		case 初始化请求:
			ArrayList<BusinessInfo> list = cimp.selectAllBusinessInfo();
			HashMap<BusinessInfo, ArrayList<CommodityInfo>> map = new HashMap<>();
			for (BusinessInfo temp : list) {
				int business_id = temp.getBusiness_id();
				map.put(temp, bimp.selectCommodityInfo(business_id));
			}
			str = JSON.toJSONString(map);
			map = JSON.parseObject(str, new TypeReference<HashMap<BusinessInfo, ArrayList<CommodityInfo>>>() {
			});
			break;
		case 用户注册:
			jsonObj = JSONObject.parseObject(json);
			jo = jsonObj.getJSONObject("o");
			CustomerInfo c = JSONObject.parseObject(jo.toJSONString(), CustomerInfo.class);
			CustomerInfo flag = cimp.selectCustomerInfo(c.getCustomer_username());
			if (flag != null) {
				str = "Haved";// 用户已存在
			} else {
				if (cimp.insert(c) == 1) {
					str = "Success";// 注册成功
				} else {
					str = "Failed";// 注册失败
				}
			}
			break;
		case 商家登录:
			jsonObj = null;
			jsonObj = JSONObject.parseObject(json);
			JSONObject json_BusinessInfo = jsonObj.getJSONObject("o");
			BusinessInfo bi_log = JSON.parseObject(json_BusinessInfo.toJSONString(), BusinessInfo.class);
			// BusinessInfo bi_log = JSONObject
			bi_log = bimp.selectBusinessInfo(bi_log.getBusiness_username());
			if (bi_log == null) {
				data2 = new DataPackage(null, null);
			} else {
				data2 = new DataPackage(null, bi_log);
			}
			str = JSON.toJSONString(data2);
			break;
		case 用户登录:
			// 绑定IP与ID
			// BandIP ip = new BandIP();
			jsonObj = null;
			jsonObj = JSONObject.parseObject(json);
			JSONObject json_CustomerInfo = jsonObj.getJSONObject("o");
			CustomerInfo ci_log = JSON.parseObject(json_CustomerInfo.toJSONString(), CustomerInfo.class);

			// BusinessInfo bi_log = JSONObject
			ci_log = cimp.selectCustomerInfo(ci_log.getCustomer_username());
			data2 = null;
			if (ci_log == null) {
				data2 = new DataPackage(null, null);
			} else {
				data2 = new DataPackage(null, ci_log);
			}
			str = JSON.toJSONString(data2);
			break;
		case 商家信息修改:

			break;
		case 用户信息修改:

			break;
		case 订单提交:
			// HashMap<CommodityInfo,Integer>
			// HashMap<BusinessInfo,ArrayList<Goods>>
			int i = -1;
			// 拆包
			jsonObj = JSONObject.parseObject(json);
			jo = jsonObj.getJSONObject("o");
			JSONObject json_customer = jsonObj.getJSONObject("customer");
			CustomerInfo customerInfo = JSONObject.parseObject(json_customer.toJSONString(), CustomerInfo.class);
			String customer_username = customerInfo.getCustomer_username();
			customerInfo = cimp.selectCustomerInfo(customer_username);
			int customer_id = customerInfo.getCustomer_id();
			String customer_address = customerInfo.getCustomer_address();
			HashMap<BusinessInfo, ArrayList<Goods>> orderMap = JSONObject.parseObject(jo.toJSONString(),
					new TypeReference<HashMap<BusinessInfo, ArrayList<Goods>>>() {
					});
			for (Entry<BusinessInfo, ArrayList<Goods>> entry : orderMap.entrySet()) {
				BusinessInfo bi = entry.getKey();
				int business_id = bi.getBusiness_id();
				long start_time = System.currentTimeMillis();
				Long end_time = 0L;
				int status = 0;
				ArrayList<Goods> glist = entry.getValue();
				Order order = new Order();
				for (Goods temp : glist) {
					order.putOrder(temp.getCommodityInfo(), temp.getCount());
				}
				OrderformInfo orderform = new OrderformInfo(business_id, customer_id, order, start_time, end_time,
						status, customer_address);
				i = cimp.insert(orderform);
			}
			if (i == 1) {
				str = "Success";
			} else {
				str = "Failed";
			}

			break;
		case 订单修改:

			break;
		case 商家查询订单:

			break;
		case 心跳包:
			// 拆包
			jsonObj = JSONObject.parseObject(json);
			jo = jsonObj.getJSONObject("o");
			BusinessInfo bi = JSON.parseObject(jo.toJSONString(), BusinessInfo.class);
			// 获取商家信息8
			bi = bimp.selectBusinessInfo(bi.getBusiness_username());
			// 根据商家id查询订单
			ArrayList<Orderform> list1 = bimp.selectOrderformInfoTime(bi.getBusiness_id());
			Order order = new Order();
			// 查询最近五秒的订单orderform.getOrder().getOrder()
			if (list1.size() != 0) {
				order.setOrder(list1.get(0).getOrder().getOrder());
				str = JSON.toJSONString(order);
			}
			break;

		case 用户订单查询:

			break;
		default:
			break;
		}
		return str;
	}
}
