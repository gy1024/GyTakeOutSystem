package cn.elb.entity;

/**
 * 订单信息表的实体类
 * 
 * @author Administrator
 *
 */
public class Orderform extends OrderformInfo {
	private String business_name;// 商家名称
	private String customer_name;// 用户名称

	public Orderform(int order_id, int business_id, int customer_id, Order order, long start_time, long end_time,
			int status, String business_name, String customer_name) {
		super(order_id, business_id, customer_id, order, start_time, end_time, status);
		this.business_name = business_name;
		this.customer_name = customer_name;
	}

	public Orderform(int order_id, int business_id, int customer_id, Order order, long start_time, long end_time,
			int status, String customer_address, String business_name, String customer_name) {
		super(order_id, business_id, customer_id, order, start_time, end_time, status, customer_address);
		this.business_name = business_name;
		this.customer_name = customer_name;
	}

	@Override
	public String toString() {
		return "Orderform [order_id=" + super.getOrder_id() + ", business_id=" + super.getBusiness_id()
				+ ", customer_id=" + super.getCustomer_id() + ", business_name=" + business_name + ", customer_name="
				+ customer_name + ", order=" + super.getOrder() + ", start_time=" + super.getStart_time()
				+ ", end_time=" + super.getEnd_time() + ", status=" + super.getStatus() + ", customer_address="
				+ super.getCustomer_address() + "]";
	}

	public String getBusiness_name() {
		return business_name;
	}

	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public Orderform() {
		super();
	}

}
