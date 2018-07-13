package pres.mao.business;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;

import cn.elb.entity.Order;

/**
 * 订单的列表
 * 
 * @author Mao_TP
 *
 */
public class OrderList {
	ArrayList<Order> list = new ArrayList<>();

	public OrderList(ArrayList<Order> list) {
		super();
		this.list = list;
	}

	public ArrayList<Order> getList() {
		return list;
	}

	public void setList(ArrayList<Order> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		String str = null;
		for (Order temp : list) {
			str = str + temp.toString();
		}
		return str;
	}

	public String toJson() {
		String str = JSON.toJSONString(this);
		return str;
	}
}
