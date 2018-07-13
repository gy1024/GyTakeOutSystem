package cn.elb.entity;
/**
 * 绑定IP与ID
 * 
 * @author Mao_TP
 *
 */

import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import com.alibaba.fastjson.JSON;

public class BandIP {
	private Map<InetAddress, Integer> map = new HashMap<>();

	public BandIP(Map<InetAddress, Integer> map) {
		super();
		this.map = map;
	}

	public Map<InetAddress, Integer> getMap() {
		return map;
	}

	public void setMap(Map<InetAddress, Integer> map) {
		this.map = map;
	}

	@Override
	public String toString() {
		return "CustomerIP [map=" + map + "]";
	}

	public String toJson() {
		return JSON.toJSONString(this);
	}
}
