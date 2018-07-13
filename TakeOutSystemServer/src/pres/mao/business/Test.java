package pres.mao.business;

import java.awt.List;
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;

import com.alibaba.fastjson.JSON;

import cn.elb.dao.BusinessInfoDao;
import cn.elb.dao.impl.BusinessInfoDaoImpl;
import cn.elb.entity.CommodityList;
import cn.elb.entity.CustomerInfo;

/**
 * 外卖订餐系统<br>
 * 商家部分<br>
 * 
 * @author Mao_TP
 *
 */
class Te {
	private Color color;
	private Object o;
	private ArrayList<String> list;

	public ArrayList<String> getList() {
		return list;
	}

	public void setList(ArrayList<String> list) {
		this.list = list;
	}

	public Object getO() {
		return o;
	}

	public void setO(Object o) {
		this.o = o;
	}

	public Color getColor() {
		return color;
	}

	public void setColor(Color color) {
		this.color = color;
	}

	@Override
	public String toString() {
		return "Te [color=" + color + ", o=" + o + ", list=" + list + "]";
	}

}

enum Color {
	red, blue, green;

}

public class Test {

	public static void main(String[] args) throws FileNotFoundException, IOException {

		// Test t = new Test();
		// t.addCommodity("addcommodity.txt", "commodity.txt"); // 添加商品
		// t.queryStatus("orderTest.txt"); // 查询订单状态
		// t.modifyStatus("orderTest.txt"); // 修改订单状态
		// t.showCommodity(10001);
		Te t = new Te();
		t.setColor(Color.red);

		t.setList(new ArrayList<String>());
		t.getList().add("asd");
		t.getList().add("zXC");
		t.getList().add("xfg");
		t.getList().add("qqq");
		t.setO(new CustomerInfo("mao", "123"));
		String str = JSON.toJSONString(t);
		System.out.println(str);
		System.out.println(JSON.parseObject(str, Te.class));

	}

	/**
	 * 读取订单 <br>
	 * 
	 * @param path
	 *            序列化内容
	 * @return b 订单信息
	 * @throws IOException
	 */
	public byte[] readOrder(String path) throws IOException {

		BufferedInputStream bis = new BufferedInputStream(new FileInputStream(path));
		// BufferedInputStream bos2 = new BufferedInputStream(new ObjectInputStream(new
		// FileInputStream("orderTest.txt")));————之后要修改成序列化
		byte[] b = new byte[bis.available()];
		bis.read(b);
		bis.close();
		return b;
	}

	/**
	 * 写入订单 <br>
	 * 
	 * @param b
	 *            订单信息数据
	 * @param path
	 *            序列化内容
	 * @throws IOException
	 */
	public void writeOrder(byte[] b, String path) throws IOException {

		BufferedOutputStream bos = new BufferedOutputStream(new FileOutputStream(path));
		// BufferedOutputStream bos2 = new BufferedOutputStream(new
		// ObjectOutputStream(new
		// FileOutputStream("orderTest.txt")));————之后要修改成序列化
		bos.write(b);
		bos.flush();
		bos.close();
	}

	/**
	 * 查询订单状态 <br>
	 * 
	 * @param orderPath
	 *            订单表
	 * @throws IOException
	 */
	public void queryStatus(String orderPath) throws IOException {

		// 读取订单
		byte[] orderTest = readOrder(orderPath);

		// 显示订单
		String str = new String(orderTest);
		System.out.println(str);
	}

	/**
	 * 修改订单状态 <br>
	 * 
	 * 状态：<br>
	 * 00 保存订单，状态为（未接单）<br>
	 * 01 修改订单状态为（已接单）<br>
	 * 02  修改订单状态为（配送中）<br>
	 * 03 修改订单状态为（已完成）<br>
	 * 
	 * @param orderPath
	 *            订单表
	 * @throws FileNotFoundException
	 * @throws IOException
	 */
	public void modifyStatus(String orderPath) throws FileNotFoundException, IOException {

		// 读取订单
		byte[] orderTest = readOrder(orderPath);

		// 显示订单
		String str = new String(orderTest);
		System.out.println(str);

		// 修改订单(主要是修改订单状态)
		str = str.replaceAll("status=00", "status=01");// 这里可以换成一个enum

		// 保存订单
		byte[] temp = str.getBytes();
		System.out.println("============");
		writeOrder(temp, orderPath);
		System.out.println(new String(readOrder(orderPath)));

	}

	/**
	 * 增加商品 <br>
	 * 
	 * @param addCommoditypath
	 *            增加的商品表
	 * @param commoditypath
	 *            商品表
	 * @throws IOException
	 */
	public void addCommodity(String addCommoditypath, String commoditypath) throws IOException {

		// 读取要增加的商品列表
		// 通过填表的方式完成新增商品
		byte[] commodity = readOrder(addCommoditypath);
		String str = new String(commodity);
		// System.out.println(str);// 显示订单

		// 读取现有商品列表
		byte[] commoditys = readOrder(commoditypath);

		// 增加商品——在sql中采用增加，在txt测试中暂时采用string字符串添加
		str = (new String(commoditys)) + System.getProperty("line.separator") + str;
		writeOrder(str.getBytes(), commoditypath);
		System.out.println(str);
	}

	/**
	 * 删除商品
	 * 
	 * @throws IOException
	 */
	public void deleteCommodity(int commodity_id) throws IOException {

		// 读取当前商品列表,Test使用
		byte[] commoditys = readOrder("commodity.txt");

		// 根据发送过来的商品ID删除商品

	}

	/**
	 * 展示商品
	 */
	public void showCommodity(int business_id) {
		BusinessInfoDao bid = new BusinessInfoDaoImpl();
		bid.selectCommodityInfo(business_id);
		// iTODO
	}
}
