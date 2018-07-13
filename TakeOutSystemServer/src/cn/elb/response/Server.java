package cn.elb.response;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Properties;

public class Server {
	private final int PORT;
	private ServerSocket serverSocket;

	public Server() {
		Properties p = new Properties();
		try {
			p.load(new FileReader("config.properties"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		PORT = Integer.parseInt(p.getProperty("PORT"));
	}

	public void start() {
		try {
			serverSocket = new ServerSocket(PORT);
			System.out.println("服务器已启动！正在监听" + PORT + "端口....");
			while (true) {
				Socket socket = serverSocket.accept();
				// 线程启动
				new Thread(new MySocket(socket)).start();

				System.out.println(socket.getInetAddress().getHostAddress() + "连接到服务器！");
			}
		} catch (IOException e) {
			e.printStackTrace();
		} finally {
			try {
				if (serverSocket != null) {
					serverSocket.close();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	public static void main(String[] args) {
		new Server().start();
	}
}
