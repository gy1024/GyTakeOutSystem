package cn.elb.response;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.Socket;

public class MySocket implements Runnable {
	private Socket socket;
	private BufferedReader reader;
	private BufferedWriter writer;

	public MySocket(Socket socket) {
		this.socket = socket;
	}

	@Override
	public void run() {
		try {
			reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
			writer = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
			String str = "";
			str = reader.readLine();
			System.out.print("服务端收到" + socket.getInetAddress().getHostAddress() + "发来的请求!\n");
			/**
			 * 在此对传入内容进行处理</br>
			 * 1、对传入的JSON字符串进行解包</br>
			 * 2、对请求进行分派</br>
			 */
			// 分派
			String response = Response.responseQuest(str);
			if (response == null) {
				response = "";
			}
			writer.write(response);
			writer.flush();
			System.out.print("服务端将" + socket.getInetAddress().getHostAddress() + "的请求处理完毕\n");
		} catch (

		IOException e) {
			e.printStackTrace();
		} finally {
			try {
				System.out.println(socket.getInetAddress().getHostAddress() + "断开连接！");
				socket.close();
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
