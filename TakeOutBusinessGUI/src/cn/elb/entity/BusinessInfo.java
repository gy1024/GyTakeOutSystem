package cn.elb.entity;

import java.io.Serializable;

/**
 * 商家信息表的实体类
 * 
 * @author Administrator
 *
 */
public class BusinessInfo implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1259992436081004014L;
	private int business_id;// 商家id
	private String business_name;// 商家名称
	private String business_picture;// 商家图片
	private String business_username;// 商家账号名
	private String business_password;// 商家密码
	public int getBusiness_id() {
		return business_id;
	}

	public void setBusiness_id(int business_id) {
		this.business_id = business_id;
	}

	public String getBusiness_name() {
		return business_name;
	}

	public void setBusiness_name(String business_name) {
		this.business_name = business_name;
	}

	public String getBusiness_picture() {
		return business_picture;
	}

	public void setBusiness_picture(String business_picture) {
		this.business_picture = business_picture;
	}

	public String getBusiness_username() {
		return business_username;
	}

	public void setBusiness_username(String business_username) {
		this.business_username = business_username;
	}

	public String getBusiness_password() {
		return business_password;
	}

	public void setBusiness_password(String business_password) {
		this.business_password = business_password;
	}

	public BusinessInfo(String business_username, String business_password) {
		super();
		this.business_username = business_username;
		this.business_password = business_password;
	}

	public BusinessInfo(String business_name, String business_picture, String business_username,
			String business_password) {
		super();
		this.business_name = business_name;
		this.business_picture = business_picture;
		this.business_username = business_username;
		this.business_password = business_password;
	}

	public BusinessInfo(int business_id, String business_name, String business_picture, String business_username,
			String business_password) {
		super();
		this.business_id = business_id;
		this.business_name = business_name;
		this.business_picture = business_picture;
		this.business_username = business_username;
		this.business_password = business_password;
	}

	public BusinessInfo() {
		super();
	}
	
	public BusinessInfo(int business_id, String business_name, String business_picture) {
		super();
		this.business_id = business_id;
		this.business_name = business_name;
		this.business_picture = business_picture;
	}

	@Override
	public String toString() {
		return business_name;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + business_id;
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
		BusinessInfo other = (BusinessInfo) obj;
		if (business_id != other.business_id)
			return false;
		return true;
	}
}
