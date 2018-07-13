package cn.elb.entity;

import java.io.Serializable;

/**
 * 用户信息表的实体类
 * 
 * @author Administrator
 *
 */
public class CustomerInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = -4423788838737484776L;
	private int customer_id;// 用户id
	private String customer_name;// 用户名称
	private String customer_username;// 用户账号名
	private String customer_password;// 用户密码

	public int getCustomer_id() {
		return customer_id;
	}

	public void setCustomer_id(int customer_id) {
		this.customer_id = customer_id;
	}

	public String getCustomer_name() {
		return customer_name;
	}

	public void setCustomer_name(String customer_name) {
		this.customer_name = customer_name;
	}

	public String getCustomer_username() {
		return customer_username;
	}

	public void setCustomer_username(String customer_username) {
		this.customer_username = customer_username;
	}

	public String getCustomer_password() {
		return customer_password;
	}

	public void setCustomer_password(String customer_password) {
		this.customer_password = customer_password;
	}

	public CustomerInfo(String customer_username, String customer_password) {
		super();
		this.customer_username = customer_username;
		this.customer_password = customer_password;
	}

	public CustomerInfo(String customer_name, String customer_username, String customer_password) {
		super();
		this.customer_name = customer_name;
		this.customer_username = customer_username;
		this.customer_password = customer_password;
	}

	public CustomerInfo(int customer_id, String customer_name, String customer_username, String customer_password) {
		super();
		this.customer_id = customer_id;
		this.customer_name = customer_name;
		this.customer_username = customer_username;
		this.customer_password = customer_password;
	}

	public CustomerInfo() {
		super();
	}

	@Override
	public String toString() {
		return "CustomerInfo [customer_id=" + customer_id + ", customer_name=" + customer_name + ", customer_username="
				+ customer_username + ", customer_password=" + customer_password + "]";
	}

}
