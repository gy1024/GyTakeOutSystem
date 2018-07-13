package cn.elb.entity;

/**
 * 单个订单
 * 
 * @author hu
 *
 */
public class Goods {
	private CommodityInfo commodityInfo;
	private int count;

	public CommodityInfo getCommodityInfo() {
		return commodityInfo;
	}

	public void setCommodityInfo(CommodityInfo commodityInfo) {
		this.commodityInfo = commodityInfo;
	}

	public int getCount() {
		return count;
	}

	public void setCount(int count) {
		this.count = count;
	}

	public Goods(CommodityInfo commodityInfo, int count) {
		super();
		this.commodityInfo = commodityInfo;
		this.count = count;
	}
}
