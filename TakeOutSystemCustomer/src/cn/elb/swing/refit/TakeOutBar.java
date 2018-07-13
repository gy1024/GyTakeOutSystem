package cn.elb.swing.refit;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.net.MalformedURLException;
import java.net.URL;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;

import cn.elb.entity.BusinessInfo;

public class TakeOutBar extends JPanel{

	private static final long serialVersionUID = 20180705L;
	private Image img;
	private JButton intoStore;//进入店铺按钮
	private JLabel title;
	private BusinessInfo businessInfo;
	/**
	 * 外卖菜单栏
	 * @param fileName
	 */
	public TakeOutBar(BusinessInfo businessInfo) {
		this.businessInfo = businessInfo;
		setLayout(null);
		try {
			img = new ImageIcon(new URL("http://127.0.0.1/img/"+businessInfo.getBusiness_name()+".png")).getImage();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		title = new JLabel(businessInfo.getBusiness_name());
		title.setBounds(120, 10, 200, 30);
		title.setFont(new Font("黑体", 0, 20));
		title.setForeground(Color.RED);
		add(title);
		intoStore = new JButton("进入店铺");
		intoStore.setBounds(120, 45, 200, 60);
		intoStore.setFont(new Font("楷体", 0, 25));
		add(intoStore);
		setSize(385,150);
	}
	@Override
	public String toString() {
		return title.getText();
	}
	@Override
	protected void paintComponent(Graphics g) {
		super.paintComponent(g);
		g.drawImage(img, 10, 10, 100, 100, this);
		g.setColor(new Color(220, 220, 220));
		g.drawLine(5, 5, 340, 5);
		g.drawLine(5, 5, 5, 115);
		g.drawLine(5, 115, 340, 115);
		g.drawLine(340, 5, 340, 115);
	}
	/**
	 * 
	 * @return
	 * 获取外卖栏中进入店铺的按钮
	 */
	public JButton getIntoStore() {
		return intoStore;
	}
	public int getId(){
		return businessInfo.getBusiness_id();
	}
	public BusinessInfo getBusinessInfo() {
		return businessInfo;
	}	
}
