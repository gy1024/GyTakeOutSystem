package cn.elb.test;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map.Entry;

import com.alibaba.fastjson.JSONObject;
import com.alibaba.fastjson.TypeReference;

import cn.elb.dao.impl.CustomerInfoDaoImp;
import cn.elb.entity.BusinessInfo;
import cn.elb.entity.CustomerInfo;
import cn.elb.entity.Goods;
import cn.elb.entity.Order;
import cn.elb.entity.OrderformInfo;
import cn.elb.entity.Request;
import cn.elb.response.Response;

/**
 * 测试json
 * 
 * @author Mao_TP8745
 * 
 */
public class Test {
	public static void main(String[] args) throws IOException {
		// CustomerInfo ci = new CustomerInfo("maomao", "asd");
		long start = System.currentTimeMillis();
		BufferedReader bi = new BufferedReader(new FileReader("TestData.txt"));
		StringBuffer sb = new StringBuffer();
		String str = bi.readLine();
		while (str != null) {
			sb.append(str);
			str = bi.readLine();
		}

		insert(sb.toString());
		long end = System.currentTimeMillis();
		System.out.println(end - start);
	}

	public static void insert(String json) {
		// HashMap<CommodityInfo,Integer>
		// HashMap<BusinessInfo,ArrayList<Goods>>
		CustomerInfoDaoImp cimp = new CustomerInfoDaoImp();
		JSONObject jsonObj1 = JSONObject.parseObject(json);
		JSONObject json_o = jsonObj1.getJSONObject("o");
		JSONObject json_customer = jsonObj1.getJSONObject("customer");
		CustomerInfo customerInfo = JSONObject.parseObject(json_customer.toJSONString(), CustomerInfo.class);
		String customer_username = customerInfo.getCustomer_username();
		customerInfo = cimp.selectCustomerInfo(customer_username);
		int customer_id = customerInfo.getCustomer_id();
		String customer_address = customerInfo.getCustomer_address();
		HashMap<BusinessInfo, ArrayList<Goods>> orderMap = JSONObject.parseObject(json_o.toJSONString(),
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
			OrderformInfo orderform = new OrderformInfo(business_id, customer_id, order, start_time, end_time, status,
					customer_address);
			cimp.insert(orderform);
		}
	}
}
