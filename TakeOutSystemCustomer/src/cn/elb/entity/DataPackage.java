package cn.elb.entity;

import com.alibaba.fastjson.JSON;

/**
 * 传输的数据包定义
 * 
 * @author Mao_TP
 * @param <E>
 *
 */
public class DataPackage<E> {
	private Request request;
	private E e;
	private CustomerInfo customer;
	public DataPackage(Request request, E e) {
		super();
		this.request = request;
		this.e = e;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public Object getO() {
		return e;
	}

	public void setE(E e) {
		this.e = e;
	}

	public String toJson() {
		return JSON.toJSONString(this);
	}

	public CustomerInfo getCustomer() {
		return customer;
	}

	public void setCustomer(CustomerInfo customer) {
		this.customer = customer;
	}

	public E getE() {
		return e;
	}

	public DataPackage(Request request, E e, CustomerInfo customer) {
		super();
		this.request = request;
		this.e = e;
		this.customer = customer;
	}
}
