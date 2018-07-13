package cn.elb.test;


import cn.hjj.funnction.PlayMp3;

public class test {

	public static void main(String[] args) {
		for(int i =0;i<4;i++){
			new Thread(new PlayMp3()).start();
			try {
				Thread.sleep(5000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
		}
	}

}
