package cn.elb.entity;

import com.alibaba.fastjson.JSON;

/**
 * 传输的数据包定义
 * 
 * @author Mao_TP
 * @param
 *
 */
public class DataPackage {
	private Request request;
	private Object o;
	private CustomerInfo customer;

	public DataPackage(Request request, Object o) {
		super();
		this.request = request;
		this.o = o;
	}

	public DataPackage(Request request, Object o, CustomerInfo customer) {
		super();
		this.request = request;
		this.o = o;
		this.customer = customer;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Object getO() {
		return o;
	}

	public void setO(Object o) {
		this.o = o;
	}

	public CustomerInfo getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerInfo customer) {
		this.customer = customer;
	}

	public String toJson() {
		return JSON.toJSONString(this);
	}
}
