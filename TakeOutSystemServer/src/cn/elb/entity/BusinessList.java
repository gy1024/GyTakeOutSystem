package cn.elb.entity;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;

/**
 * business表对应的实体类，BusinessInfo为business表中的一条记录
 * 
 * @author Administrator
 *
 */
public class BusinessList {

	private ArrayList<BusinessInfo> list;

	public BusinessList(ArrayList<BusinessInfo> list) {
		super();
		this.list = list;
	}

	public ArrayList<BusinessInfo> getList() {
		return list;
	}

	public void setList(ArrayList<BusinessInfo> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		String str = null;
		for (BusinessInfo temp : list) {
			str = str + temp.toString();
		}
		return str;
	}

	public String toJson() {
		return JSON.toJSONString(this);
	}
}
