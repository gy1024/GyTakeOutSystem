package cn.elb.swing.refit;

import java.awt.Graphics;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

public class ImageJpanel extends JPanel {

	private static final long serialVersionUID = 20180705L;
	private Image img;
	/**
	 * 可以添加背景图片的Jpanel
	 * @param fileName
	 * img文件夹下图片的地址
	 */
	public ImageJpanel(String fileName) {
		fileName="img/"+fileName;
		img = new ImageIcon(fileName).getImage();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 0, 0, this.getWidth(), this.getHeight(), this);
	}
}
