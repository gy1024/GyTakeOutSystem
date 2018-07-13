package cn.elb.entity;

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
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((commodityInfo == null) ? 0 : commodityInfo.hashCode());
		return result;
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Goods other = (Goods) obj;
		if (commodityInfo == null) {
			if (other.commodityInfo != null)
				return false;
		} else if (!commodityInfo.equals(other.commodityInfo))
			return false;
		return true;
	}
	@Override
	public String toString() {
		return "Goods [commodityInfo=" + commodityInfo + ", count=" + count + "]";
	}
	
}
