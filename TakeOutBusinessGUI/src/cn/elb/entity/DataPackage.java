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
	private E o;

	public DataPackage(Request request, E o) {
		super();
		this.request = request;
		this.o = o;
	}

	public Request getRequest() {
		return request;
	}

	public void setRequest(Request request) {
		this.request = request;
	}

	public E getO() {
		return o;
	}

	public void setO(E o) {
		this.o = o;
	}

	public String toJson() {
		return JSON.toJSONString(this);
	}
}
