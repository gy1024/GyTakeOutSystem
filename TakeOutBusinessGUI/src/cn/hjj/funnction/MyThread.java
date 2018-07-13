package cn.hjj.funnction;

import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.JTextArea;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.elb.entity.BusinessInfo;
import cn.elb.entity.CommodityInfo;
import cn.elb.entity.DataPackage;
import cn.elb.entity.Order;
import cn.elb.entity.Request;
import cn.elb.socket.SendData;

public class MyThread implements Runnable{
	private JTextArea textArea;
	private BusinessInfo businessInfo;
	public MyThread(JTextArea textArea,BusinessInfo businessInfo) {
		this.textArea = textArea;
		this.businessInfo = businessInfo;
	}
	@Override
	public void run() {
		DataPackage<BusinessInfo> dp = new DataPackage<BusinessInfo>(Request.心跳包,businessInfo);
		
		while(true){
			String str =SendData.sendData(dp);
			System.out.println(str);
			Order order = JSON.parseObject(str,new TypeReference<Order>(){});
			if(order!=null){
//				Order order = res.getO();
				HashMap<CommodityInfo, Integer> map = order.getOrder();
				Set<CommodityInfo> set = map.keySet();
				StringBuffer sb = new StringBuffer();
				sb.append("新的订单!\n");
				sb.append("商品/数量\n").append("-------------------------------\n");
				Iterator<CommodityInfo> ite = set.iterator();
				while (ite.hasNext()) {
					CommodityInfo c = ite.next();
					sb.append(c.toString()).append("---->").append(map.get(c)).append("份\n");
				}
				sb.append("\n").append("-------------------------------\n");
				textArea.append(sb.toString());
				new Thread(new PlayMp3()).start();
			}
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
