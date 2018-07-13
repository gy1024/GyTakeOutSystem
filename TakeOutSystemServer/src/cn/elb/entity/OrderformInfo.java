package cn.elb.entity;

/**
 * 订单信息表的实体类
 * 
 * @author Administrator
 *
 */
public class OrderformInfo {
	private int order_id;// 订单id
	private int business_id;// 商家id
	private int customer_id;// 用户id
	private Order order;// 订单详细信息
	private long start_time;// 订单开始时间
	private long end_time;// 订单结束时间
	private int status;// 订单状态
	private String customer_address;// 用户地址

	public String getCustomer_address() {
		return customer_address;
	}

	public void setCustomer_address(String customer_address) {
		this.customer_address = customer_address;
	}

	public int getOrder_id() {
		return order_id;
	}

	public void setOrder_id(int order_id) {
		this.order_id = order_id;
	}

	public int getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(int business_id) {
		this.business_id = business_id;
	}

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public Order getOrder() {
		return order;
	}

	public void setOrder(Order order) {
		this.order = order;
	}

	public long getStart_time() {
		return start_time;
	}

	public void setStart_time(long start_time) {
		this.start_time = start_time;
	}

	public long getEnd_time() {
		return end_time;
	}

	public void setEnd_time(long end_time) {
		this.end_time = end_time;
	}

	public int getStatus() {
		return status;
	}

	public void setStatus(int status) {
		this.status = status;
	}

	public OrderformInfo(int order_id, int business_id, int customer_id, Order order, long start_time, long end_time,
			int status) {
		super();
		this.order_id = order_id;
		this.business_id = business_id;
		this.order = order;
		this.start_time = start_time;
		this.end_time = end_time;
		this.status = status;
	}

	public OrderformInfo(int business_id, int customer_id, Order order, long start_time, long end_time, int status,
			String customer_address) {
		super();
		this.business_id = business_id;
		this.customer_id = customer_id;
		this.order = order;
		this.start_time = start_time;
		this.end_time = end_time;
		this.status = status;
		this.customer_address = customer_address;
	}

	public OrderformInfo(int order_id, int business_id, int customer_id, Order order, long start_time, long end_time,
			int status, String customer_address) {
		super();
		this.order_id = order_id;
		this.business_id = business_id;
		this.customer_id = customer_id;
		this.order = order;
		this.start_time = start_time;
		this.end_time = end_time;
		this.status = status;
		this.customer_address = customer_address;
	}

	public OrderformInfo() {
		super();
	}

	@Override
	public String toString() {
		return "OrderformInfo [order_id=" + order_id + ", business_id=" + business_id + ", customer_id=" + customer_id
				+ ", order=" + order.toString() + ", start_time=" + start_time + ", end_time=" + end_time + ", status="
				+ status + ", customer_address=" + customer_address + "]";
	}

}
