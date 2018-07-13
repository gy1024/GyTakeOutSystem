package cn.elb.entity;

/**
 * 商品信息表的实体类
 * 
 * @author Administrator
 *
 */
public class CommodityInfo {
	private int commodity_id;// 商品id
	private String commodity_name;// 商品名称
	private double commodity_price;// 商品价格
	private String commodity_picture;// 商品图片
	private int business_id;// 商家id

	public int getCommodity_id() {
		return commodity_id;
	}

	public void setCommodity_id(int commodity_id) {
		this.commodity_id = commodity_id;
	}

	public String getCommodity_name() {
		return commodity_name;
	}

	public void setCommodity_name(String commodity_name) {
		this.commodity_name = commodity_name;
	}

	public double getCommodity_price() {
		return commodity_price;
	}

	public void setCommodity_price(double commodity_price) {
		this.commodity_price = commodity_price;
	}

	public String getCommodity_picture() {
		return commodity_picture;
	}

	public void setCommodity_picture(String commodity_picture) {
		this.commodity_picture = commodity_picture;
	}

	public int getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(int business_id) {
		this.business_id = business_id;
	}

	public CommodityInfo(String commodity_name, double commodity_price, String commodity_picture, int business_id) {
		super();
		this.commodity_name = commodity_name;
		this.commodity_price = commodity_price;
		this.commodity_picture = commodity_picture;
		this.business_id = business_id;
	}

	public CommodityInfo(int commodity_id, String commodity_name, double commodity_price, String commodity_picture,
			int business_id) {
		super();
		this.commodity_id = commodity_id;
		this.commodity_name = commodity_name;
		this.commodity_price = commodity_price;
		this.commodity_picture = commodity_picture;
		this.business_id = business_id;
	}

	public CommodityInfo() {
		super();
	}

	@Override
	public String toString() {
		return "CommodityInfo [commodity_name=" + commodity_name + "]";
	}

}
