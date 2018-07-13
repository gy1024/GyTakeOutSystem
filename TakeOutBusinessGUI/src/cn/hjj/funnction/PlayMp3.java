package cn.hjj.funnction;


import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javazoom.jl.decoder.JavaLayerException;
import javazoom.jl.player.Player;

public class PlayMp3 implements Runnable{
	private Player player;
	public PlayMp3() {
		try {
			player = new Player(new FileInputStream("mp3/ts.mp3"));
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
	}
	@Override
	public void run() {
		try {
			player.play();
		} catch (JavaLayerException e) {
			e.printStackTrace();
		}
		
	}

}
