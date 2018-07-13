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

import cn.elb.entity.CommodityInfo;

public class BusinessMenu extends JPanel{

	private static final long serialVersionUID = -6861429303145378865L;
	private Image img;
	private JLabel title;
	private CommodityInfo commodityInfo;
	private JButton addCartBtn;
	public BusinessMenu(CommodityInfo commodityInfo) {
		this.commodityInfo = commodityInfo;
		try {
			img = new ImageIcon(new URL("http://127.0.0.1/img/"+commodityInfo.getCommodity_name()+".png")).getImage();
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		title = new JLabel(commodityInfo.getCommodity_name());
		title.setBounds(120, 10, 200, 30);
		title.setFont(new Font("黑体", 0, 25));
		add(title);
		JLabel price = new JLabel("售价:"+commodityInfo.getCommodity_price()+"元");
		price.setFont(new Font("黑体", 0, 20));
		price.setForeground(Color.RED);
		price.setBounds(120,40, 200, 30);
		add(price);
		addCartBtn = new JButton("加入购物车");
		addCartBtn.setBounds(200, 65, 130, 45);
		addCartBtn.setFont(new Font("楷体", 0, 18));
		add(addCartBtn);
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
	public CommodityInfo getCommodityInfo() {
		return commodityInfo;
	}
	public JButton getAddCartBtn() {
		return addCartBtn;
	}
}
