package cn.elb.entity;

import java.util.HashMap;

import com.alibaba.fastjson.JSON;

public class Order {
	private HashMap<CommodityInfo, Integer> order = new HashMap<>();

	public HashMap<CommodityInfo, Integer> getOrder() {
		return order;
	}

	public void setOrder(HashMap<CommodityInfo, Integer> order) {
		this.order = order;
	}

	public void putOrder(CommodityInfo commodityInfo, int count) {
		this.order.put(commodityInfo, new Integer(count));
	}

	public Order() {
	}

	public Order(HashMap<CommodityInfo, Integer> order) {
		super();
		this.order = order;
	}

	@Override
	public String toString() {
		String key = null;
		String value = null;
		for (HashMap.Entry<CommodityInfo, Integer> entry : order.entrySet()) {
			key = key + entry.getKey().toString();
			value = value + entry.getValue().toString();
		}
		return "Order [order_key=" + key + "order_value=" + value + "]";
	}

	public String toJson() {
		return JSON.toJSONString(this);
	}
}
