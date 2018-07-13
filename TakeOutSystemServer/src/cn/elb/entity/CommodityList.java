package cn.elb.entity;

import java.util.ArrayList;

import com.alibaba.fastjson.JSON;

/**
 * commodity表对应的实体类，CommodityInfo为commodity表中的一条信息
 * 
 * @author Mao_TP
 *
 */
public class CommodityList {
	private ArrayList<CommodityInfo> list;

	public CommodityList(ArrayList<CommodityInfo> list) {
		super();
		this.list = list;
	}

	public ArrayList<CommodityInfo> getList() {
		return list;
	}

	public void setList(ArrayList<CommodityInfo> list) {
		this.list = list;
	}

	@Override
	public String toString() {
		String str = null;
		for (CommodityInfo temp : list) {
			str = str + temp.toString();
		}
		return str;
	}

	public String toJson() {
		return JSON.toJSONString(this);
	}
}
