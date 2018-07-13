package cn.elb.socket;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.net.Socket;
import java.net.UnknownHostException;


import com.alibaba.fastjson.JSON;

import cn.elb.entity.DataPackage;

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
}
