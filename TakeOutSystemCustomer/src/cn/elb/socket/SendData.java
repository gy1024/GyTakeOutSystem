package cn.elb.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.elb.entity.BusinessInfo;
import cn.elb.entity.CommodityInfo;
import cn.elb.entity.DataPackage;
import cn.elb.entity.Request;

public class SendData {
	private static final String HOST="192.168.57.154";
	private static final int POST=8888;
	private static Socket socket;
	public static String sendData(DataPackage<?> dp){
		StringBuffer sb = null;
		try {
			socket = new Socket(HOST, POST);
			String str = JSON.toJSONString(dp);
			OutputStream os = socket.getOutputStream();
			InputStream is =socket.getInputStream();
			os.write(str.getBytes());
			socket.shutdownOutput();
			sb = new StringBuffer();
			BufferedReader br = new BufferedReader(new InputStreamReader(is,"UTF-8"));
			String s ="";
			while((s=br.readLine())!=null){	
				sb.append(s);
			}
			
		} catch (UnknownHostException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}finally{
			try {
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
		return sb.toString();
	}
	public static void main(String[] args) {
		DataPackage<Object> dp = new DataPackage<Object>(Request.初始化请求, null);
		String str = SendData.sendData(dp);
		System.out.println(str);
		HashMap<BusinessInfo, ArrayList<CommodityInfo>> map =JSON.parseObject(str, new TypeReference<HashMap<BusinessInfo, ArrayList<CommodityInfo>>>() {});
		Set<BusinessInfo> set =map.keySet();
		Iterator<BusinessInfo> ite = set.iterator();
		while(ite.hasNext()){
			BusinessInfo b = ite.next();
			System.out.println(b.getBusiness_name());
			System.out.println("==================================");
			ArrayList<CommodityInfo> list = map.get(b);
			for (int i = 0; i < list.size(); i++) {
				System.out.println(list.get(i).getCommodity_name());
			}
		}
	}
}
