package cn.elb.entity;

import java.util.ArrayList;

public class Company {
	private BusinessInfo bi;
	private ArrayList<CommodityInfo> list;

	public BusinessInfo getBi() {
		return bi;
	}

	public void setBi(BusinessInfo bi) {
		this.bi = bi;
	}

	public ArrayList<CommodityInfo> getList() {
		return list;
	}

	public void setList(ArrayList<CommodityInfo> list) {
		this.list = list;
	}

	public Company(BusinessInfo bi, ArrayList<CommodityInfo> list) {
		super();
		this.bi = bi;
		this.list = list;
	}

}
