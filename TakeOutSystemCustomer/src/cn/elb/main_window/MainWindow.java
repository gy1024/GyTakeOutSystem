package cn.elb.main_window;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Set;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.ScrollPaneConstants;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.elb.entity.BusinessInfo;
import cn.elb.entity.CommodityInfo;
import cn.elb.entity.CustomerInfo;
import cn.elb.entity.DataPackage;
import cn.elb.entity.Goods;
import cn.elb.entity.Request;
import cn.elb.socket.SendData;
import cn.elb.swing.refit.BusinessMenu;
import cn.elb.swing.refit.ImageJpanel;
import cn.elb.swing.refit.TakeOutBar;

public class MainWindow extends JFrame {

	private static final long serialVersionUID = 20180705L;
	/**
	 * 登录界面和注册界面的字体
	 */
	private final Font LOGIN_AND_REGISTER_FONT = new Font("黑体", 0, 20);
	private final Font APPLICATION_FONT = new Font("黑体", 0, 15);
	/**
	 * 商家表
	 */
	private HashMap<Integer,BusinessInfo> businessTable;
	/**
	 * 订单
	 */
	private HashMap<BusinessInfo,ArrayList<Goods>> orderForm;
	/**
	 * 存储所有的店铺信息和商品信息
	 */
	private HashMap<BusinessInfo,ArrayList<CommodityInfo>> allData;
	/**
	 * 承载页面的舞台
	 */
	private JPanel stage;//舞台
	/**
	 * 第一层界面有</br>
	 * 欢迎页:welcome------->0</br>
	 * 登录页:login--------->1</br>
	 * 注册页:register------>2</br>
	 * 应用页(第二层):application--->3</br>
	 */
	private JPanel[] firstPage;//第一层界面容器
	/**
	 * 登录界面账号输入框
	 */
	private JTextField loginUserNameField;
	/**
	 * 登录界面密码输入框
	 */
	private JPasswordField loginPasswordField;
	/**
	 * 登录界面登录按钮
	 */
	private JButton login_loginButton;
	/**
	 * 登录界面注册按钮
	 */
	private JButton login_registerButton;
	/**
	 * 注册界面账号输入框
	 */
	private JTextField registerUserNameField;
	/**
	 * 注册界面密码输入框
	 */
	private JPasswordField registerPasswordField;
	/**
	 * 注册界面密码再次确认输入框
	 */
	private JPasswordField registerPasswordField_re;
	/**
	 * 注册界面返回按钮
	 */
	private JButton register_backButton;
	/**
	 * 注册界面注册按钮
	 */
	private JButton register_registerButton;
	/**
	 * 应用界面底层导航菜单层
	 */
	private JPanel menuPane;
	/**
	 * 应用界面内容层
	 */
	private JPanel bodyPane;
	/**
	 * 应用界面底层导航菜单层按钮容器
	 * 0----->外卖
	 * 1----->购物车
	 * 2----->订单
	 * 3----->我的
	 */
	private JButton[] menuButton;
	/**
	 * 应用界面内容层容器
	 * 0----->外卖
	 * 1----->购物车
	 * 2----->订单
	 * 3----->我的
	 * 4----->可以动态改变的卖家菜单
	 */
	private JPanel[] bodyPaneArray;
	/**
	 * 外卖界面滚动栏
	 */
	private JScrollPane takeOutScrollPane;
	/**
	 * 购物车界面滚动栏
	 */
	private JScrollPane shopCartScrollPane;
	/**
	 * 订单界面滚动栏
	 */
	private JScrollPane orderScrollPane;
	/**
	 * 外卖界面滚动栏里的外卖界面
	 */
	private JPanel takeOutPane;
	/**
	 * 店铺栏
	 */
	private TakeOutBar[] takeOutBar;
	/**
	 * 动态改变的卖家菜单中返回按钮
	 */
	private JButton backbtn;
	/**
	 *动态改变的卖家菜单中滚动栏
	 */
	private JScrollPane takeOutMenuScrollPane;
	/**
	 * 动态改变的卖家菜单中滚动栏里的界面
	 */
	private JPanel takeOutMenuPane;
	/**
	 * 购物车
	 */
	private HashMap<CommodityInfo,Integer> shopcart;
	/**
	 * 购物车界面提交按钮
	 */
	private JButton putbtn;
//	/**
//	 * 购物车界面滚动栏里的界面
//	 */
//	private JPanel shopcartPane;
	/**
	 * 购物车滚动栏里的内容
	 */
	private JTextArea carttext;
	/**
	 * 清空购物车按钮
	 */
	private JButton clearCart;
	public static void main(String[] args) {
		EventQueue.invokeLater(() ->{
			try {
				//解决皮肤美化包切换输入法白屏BUG
				System.setProperty("sun.java2d.noddraw", "true");
				BeautyEyeLNFHelper.launchBeautyEyeLNF();	//皮肤美化包
				UIManager.put("RootPane.setupButtonVisible", false);
				MainWindow frame = new MainWindow();
				frame.setVisible(true);
			} catch (Exception e) {
				e.printStackTrace();
			}
		});
	}

	public MainWindow() {
		getData();
		/**
		 * 这儿方法执行的顺序不可以改变！！！！！
		 */
		initialize();//初始化
		setPage();//设置页面
		addEvent();//添加事件
	}
	/**
	 * 从服务器获取商家数据的方法
	 */
	private void getData(){
		allData = new HashMap<>();
		businessTable = new HashMap<>();
		orderForm = new HashMap<>();
		String str = SendData.sendData(new DataPackage<>(Request.初始化请求,null));
		allData = JSON.parseObject(str, new TypeReference<HashMap<BusinessInfo, ArrayList<CommodityInfo>>>() {});
		Set<BusinessInfo> set = allData.keySet();
		Iterator<BusinessInfo> ite = set.iterator();
		while(ite.hasNext()){
			BusinessInfo b = ite.next();
			businessTable.put(b.getBusiness_id(),b);
		}
	}
	/**
	 * 初始化组件
	 */
	private void initialize() {
		firstPage = new JPanel[4];//创建一个能存放四张界面的容器
		firstPage[0] = new ImageJpanel("welcome.png");
		firstPage[1] = new ImageJpanel("login&register.png");
		firstPage[2] = new ImageJpanel("login&register.png");
		firstPage[3] = new JPanel();
		stage = new JPanel();
		
		//登录界面组件初始化
		loginUserNameField = new JTextField();
		loginPasswordField = new JPasswordField();
		login_loginButton = new JButton("登录");
		login_registerButton = new JButton("注册");
		//注册界面组件初始化
		registerUserNameField = new JTextField();
		registerPasswordField = new JPasswordField();
		registerPasswordField_re = new JPasswordField();
		register_backButton = new JButton("返回");
		register_registerButton = new JButton("注册");
		//应用界面初始化
		menuPane = new JPanel();
		bodyPane = new JPanel();
		//初始化菜单按钮容器
		menuButton = new JButton[4];
		menuButton[0] = new JButton("外 卖",new ImageIcon("img/wk_c.png"));
		menuButton[1] = new JButton("购物车",new ImageIcon("img/gwc.png"));
		menuButton[2] = new JButton("订 单",new ImageIcon("img/dd.png"));
		menuButton[3] = new JButton("我 的",new ImageIcon("img/wd.png"));
		//初始化应用界面内容层容器
		bodyPaneArray = new JPanel[5];
		for (int i = 0; i < bodyPaneArray.length; i++) {
			bodyPaneArray[i] =  new JPanel();
		}
		//初始化外卖界面滚动栏
		takeOutScrollPane = new JScrollPane();
		//初始化购物车界面滚动栏
		shopCartScrollPane = new JScrollPane();
		//初始化订单界面滚动栏
		orderScrollPane = new JScrollPane();
		//初始化外卖界面滚动栏中的外卖界面
		takeOutPane = new JPanel();
		//初始化动态改变的卖家菜单中返回按钮
		backbtn = new JButton("返回");
		//初始化动态改变的卖家菜单中滚动栏
		takeOutMenuScrollPane = new JScrollPane();
		//初始化动态改变的卖家菜单中滚动栏里的界面
		takeOutMenuPane = new JPanel();
		//初始化购物车
		shopcart = new HashMap<>();
		//初始化购物车界面提交按钮
		putbtn = new JButton("提交");
		//初始化购物车界面滚动栏里的界面
//		shopcartPane = new JPanel();
		//初始化购物车内容栏
		carttext = new JTextArea();
		//初始化清空购物车按钮
		clearCart = new JButton("清空购物车");
	}
	/**
	 * 设置页面的方法
	 */
	private void setPage(){
		//窗口设置
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setSize(450, 660);
		setTitle("饿了吧");
		setLocationRelativeTo(null);//窗体居中显示
		setResizable(false);//禁止拉伸
		
		//第一层组件设置
		firstPage[0].setSize(400,600);
		firstPage[1].setSize(400,600);
		firstPage[2].setSize(400,600);
		firstPage[3].setSize(400,600);
		firstPage[1].setLayout(null);
		firstPage[1].setVisible(false);
		firstPage[2].setLayout(null);
		firstPage[2].setVisible(false);
		firstPage[3].setLayout(null);
		firstPage[3].setVisible(false);
		
		JLabel loginUserName = new JLabel("账号:");
		JLabel loginPassword = new JLabel("密码:");
		loginUserName.setFont(LOGIN_AND_REGISTER_FONT);
		loginUserName.setBounds(30, 240, 80, 40);
		loginPassword.setFont(LOGIN_AND_REGISTER_FONT);
		loginPassword.setBounds(30, 290, 80, 40);
		loginUserNameField.setBounds(100, 240, 240, 35);
		loginPasswordField.setBounds(100, 290, 240, 35);
		login_loginButton.setBounds(100, 350, 115, 50);
		login_loginButton.setFont(LOGIN_AND_REGISTER_FONT);
		login_registerButton.setBounds(220, 350, 115, 50);
		login_registerButton.setFont(LOGIN_AND_REGISTER_FONT);
		
		JLabel registerUserName = new JLabel("账号:");
		JLabel registerPassword = new JLabel("密码:");
		JLabel registerPassword_re = new JLabel("确认密码:");
		registerUserName.setFont(LOGIN_AND_REGISTER_FONT);
		registerUserName.setBounds(30, 190, 80, 40);
		registerPassword.setFont(LOGIN_AND_REGISTER_FONT);
		registerPassword.setBounds(30, 240, 80, 40);
		registerPassword_re.setFont(LOGIN_AND_REGISTER_FONT);
		registerPassword_re.setBounds(10, 290, 110, 40);
		registerUserNameField.setBounds(100, 190, 240, 35);
		registerPasswordField.setBounds(100, 240, 240, 35);
		registerPasswordField_re.setBounds(100, 290, 240, 35);
		register_backButton.setBounds(100, 350, 115, 50);
		register_backButton.setFont(LOGIN_AND_REGISTER_FONT);
		register_registerButton.setBounds(220, 350, 115, 50);
		register_registerButton.setFont(LOGIN_AND_REGISTER_FONT);
		
		firstPage[1].add(loginUserNameField);
		firstPage[1].add(loginPasswordField);
		firstPage[1].add(login_loginButton); 
		firstPage[1].add(login_registerButton);
		firstPage[1].add(loginUserName);
		firstPage[1].add(loginPassword);
		
		firstPage[2].add(registerUserNameField);
		firstPage[2].add(registerPasswordField);
		firstPage[2].add(registerPasswordField_re);
		firstPage[2].add(register_backButton);
		firstPage[2].add(register_registerButton);
		firstPage[2].add(registerUserName);
		firstPage[2].add(registerPassword);
		firstPage[2].add(registerPassword_re);
		//应用界面层设置
		menuPane.setLayout(null);
		menuPane.setBounds(0, 530, 400, 70);
		//添加并设置菜单层组件
		for(int i =0;i<menuButton.length;i++){
			menuButton[i].setBounds(0+i*100, 0, 100, 50);
			menuButton[i].setFont(APPLICATION_FONT);
			menuButton[i].setVerticalTextPosition(JButton.BOTTOM);
			menuButton[i].setHorizontalTextPosition(JButton.CENTER);
			menuPane.add(menuButton[i]);
		}
		
		//设置外卖界面滚动栏
		takeOutScrollPane.setSize(395, 530);
		//设置外卖界面滚动栏中的外卖界面
			takeOutPane.setPreferredSize(new Dimension(380, 2500));
			takeOutPane.setLayout(null);
//			List<BusinessInfo> list = new ArrayList<>();
//			list.add(new BusinessInfo(0,"张亮麻辣烫","pd/0.png"));
//			list.add(new BusinessInfo(1,"周黑鸭","pd/1.png"));
//			list.add(new BusinessInfo(2,"肯德基","pd/2.png"));
//			list.add(new BusinessInfo(3,"德克士","pd/3.png"));
//			takeOutBar = new TakeOutBar[list.size()];
//			for (int i = 0; i < takeOutBar.length; i++) {
//				takeOutBar[i] = new TakeOutBar(list.get(i));
//				takeOutBar[i].setBounds(15, 0+i*120, 350, 120);
//				takeOutPane.add(takeOutBar[i]);
//			}
			Set<BusinessInfo> set = allData.keySet();
			takeOutBar = new TakeOutBar[set.size()];
			Iterator<BusinessInfo> ite =set.iterator();
			int index = 0;
			while (ite.hasNext()) {
				takeOutBar[index] = new TakeOutBar(ite.next());
				takeOutBar[index].setBounds(15, 0+index*120, 350, 120);
				takeOutPane.add(takeOutBar[index]);
				index++;
			}
		takeOutScrollPane.setViewportView(takeOutPane);
		takeOutScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);//取消水平（左右滚动条）
		bodyPaneArray[0].add(takeOutScrollPane);
		//设置购物车界面滚动栏
		putbtn.setBounds(0, 510, 198, 20);
		putbtn.setFont(APPLICATION_FONT);
		bodyPaneArray[1].add(putbtn);
		clearCart.setFont(APPLICATION_FONT);
		clearCart.setBounds(200, 510, 198, 20);
		bodyPaneArray[1].add(clearCart);
		shopCartScrollPane.setBounds(0, 0, 395, 515);
//		shopCartScrollPane.setLayout(null);不能设置布局
		shopCartScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);//取消水平（左右滚动条）
//		shopcartPane.setPreferredSize(new Dimension(380, 2500));
//		shopcartPane.setLayout(null);
		carttext.setLineWrap(true);
		carttext.setEditable(false);
		carttext.setFont(new Font("黑体", 0, 15));
		shopCartScrollPane.setViewportView(carttext);
		bodyPaneArray[1].add(shopCartScrollPane);
		//设置订单界面滚动栏
		orderScrollPane.setBounds(0, 0, 395, 530);
		orderScrollPane.setLayout(null);
		orderScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);//取消水平（左右滚动条）
		bodyPaneArray[2].add(orderScrollPane);
		//设置动态改变的卖家菜单
		backbtn.setBounds(0, 0, 395, 20);
		backbtn.setFont(APPLICATION_FONT);
		bodyPaneArray[4].add(backbtn);
		takeOutMenuScrollPane.setBounds(0, 20, 395, 515);
		takeOutMenuScrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);//取消水平（左右滚动条）
		takeOutMenuPane.setPreferredSize(new Dimension(380, 2500));//设置滚动栏里的界面
		takeOutMenuPane.setLayout(null);
		takeOutMenuScrollPane.setViewportView(takeOutMenuPane);
		bodyPaneArray[4].add(takeOutMenuScrollPane);
		//内容层设置
		bodyPane.setLayout(null);
		bodyPane.setBounds(0, 0, 400, 530);
		bodyPane.setBackground(new Color(255, 193,193));
		for (int i = 0; i < bodyPaneArray.length; i++) {
			bodyPaneArray[i].setSize(400, 530);
			bodyPaneArray[i].setLayout(null);//清除布局
			if(i>0){
				bodyPaneArray[i].setVisible(false);
			}
			bodyPane.add(bodyPaneArray[i]);
		}
		//给应用界面添加菜单界面和内容界面
		firstPage[3].add(menuPane);
		firstPage[3].add(bodyPane);
		//舞台设置
		stage.setLayout(null); //清空舞台的布局
		//添加第一层组件
		for(int i=0;i<firstPage.length;i++){
			stage.add(firstPage[i]);
		}
		setContentPane(stage);
	}
	/**
	 * 添加事件的方法
	 */
	private void addEvent() {
		//欢迎页倒计时进入注册页
		new Thread(()->{
			try {
				Thread.sleep(1000);
			} catch (InterruptedException e) {
				e.printStackTrace();
			}
			firstPage[0].setVisible(false);
			firstPage[1].setVisible(true);
		}).start();
		//登录界面点击注册按钮跳转注册界面
		login_registerButton.addActionListener(event ->{
			firstPage[1].setVisible(false);
			firstPage[2].setVisible(true);
		});
		//注册界面点击返回按钮跳转登录界面
		register_backButton.addActionListener(event ->{
			firstPage[1].setVisible(true);
			firstPage[2].setVisible(false);
		});
		register_registerButton.addActionListener(event ->{
			if(registerUserNameField.getText().equals("")||registerPasswordField.getText().equals("")||registerPasswordField_re.getText().equals("")){
				JOptionPane.showMessageDialog(null,"输入框不能为空！","提示框",JOptionPane.ERROR_MESSAGE);
			}else{
				if(!registerPasswordField.getText().equals(registerPasswordField_re.getText())){
					JOptionPane.showMessageDialog(null,"两次密码不一致！","提示框",JOptionPane.ERROR_MESSAGE);
				}else{
					//发送注册请求
					String request = SendData.sendData(new DataPackage<CustomerInfo>(Request.用户注册,new CustomerInfo(registerUserNameField.getText(),registerPasswordField.getText())));
					if(request.equals("Success")){
						JOptionPane.showMessageDialog(null,"注册成功！","提示框",JOptionPane.PLAIN_MESSAGE);
					}else{
						JOptionPane.showMessageDialog(null,"用户已被注册！","提示框",JOptionPane.ERROR_MESSAGE);
						registerUserNameField.setText("");
						registerPasswordField.setText("");
						registerPasswordField_re.setText("");
					}
				}
			}
		});
		//登录界面点击登录按钮，登录成功，跳转操作界面
		login_loginButton.addActionListener(event ->{
			if(loginUserNameField.getText().equals("")||loginPasswordField.getText().equals("")){
				JOptionPane.showMessageDialog(null,"输入框不能为空！","提示框",JOptionPane.ERROR_MESSAGE);
			}else{
				String str = SendData.sendData(new DataPackage<CustomerInfo>(Request.用户登录,new CustomerInfo(loginUserNameField.getText(), registerPasswordField.getText())));
				DataPackage<CustomerInfo> dp = JSON.parseObject(str,new TypeReference<DataPackage<CustomerInfo>>(){});
				if(dp!=null){
					firstPage[1].setVisible(false);
					firstPage[3].setVisible(true);
				}else{
					JOptionPane.showMessageDialog(null,"账号或密码输入错误！","提示框",JOptionPane.ERROR_MESSAGE);
				}
			}
			
		});
		//提交订单
		putbtn.addActionListener(event ->{
//			System.out.println(orderForm);
			DataPackage<HashMap<BusinessInfo,ArrayList<Goods>>> dp = new DataPackage<>(Request.订单提交,orderForm,new CustomerInfo("xiaoming","123456"));
//			try {
//				OutputStreamWriter osw = new OutputStreamWriter(new FileOutputStream("TestData.txt"));
//				osw.write(JSON.toJSONString(dp));
//				osw.close();
//			} catch (IOException e) {
//				e.printStackTrace();
//			}
			String str =SendData.sendData(dp);
			if(str.equals("Success")){
				carttext.setText("");
				shopcart.clear();
				orderForm.clear();
				JOptionPane.showMessageDialog(null,"提交成功！等待配送！","提示框",JOptionPane.PLAIN_MESSAGE);
			}else{
				JOptionPane.showMessageDialog(null,"提交失败！请重新提交!","提示框",JOptionPane.ERROR_MESSAGE);
			}
		});
		//清空购物车
		clearCart.addActionListener(event ->{
			carttext.setText("");
			shopcart.clear();
			orderForm.clear();
		});
		//菜单按钮点击切换内容层界面
		menuButton[0].addActionListener(event ->{
			bodyPaneArray[0].setVisible(true);
			bodyPaneArray[1].setVisible(false);
			bodyPaneArray[2].setVisible(false);
			bodyPaneArray[3].setVisible(false);
			bodyPaneArray[4].setVisible(false);
			menuButton[0].setIcon(new ImageIcon("img/wk_c.png"));
			menuButton[1].setIcon(new ImageIcon("img/gwc.png"));
			menuButton[2].setIcon(new ImageIcon("img/dd.png"));
			menuButton[3].setIcon(new ImageIcon("img/wd.png"));
		});
		menuButton[1].addActionListener(event ->{
			bodyPaneArray[0].setVisible(false);
			bodyPaneArray[1].setVisible(true);
			bodyPaneArray[2].setVisible(false);
			bodyPaneArray[3].setVisible(false);
			bodyPaneArray[4].setVisible(false);
			menuButton[0].setIcon(new ImageIcon("img/wk.png"));
			menuButton[1].setIcon(new ImageIcon("img/gwc_c.png"));
			menuButton[2].setIcon(new ImageIcon("img/dd.png"));
			menuButton[3].setIcon(new ImageIcon("img/wd.png"));
			Set<CommodityInfo> set = shopcart.keySet();
			Iterator<CommodityInfo> ite = set.iterator();
			StringBuffer sb = new StringBuffer();
			sb.append("商品名称/").append("单价/").append("数量/").append("总价\n");
			sb.append("---------------------------------------\n");
			double price =0;
			while(ite.hasNext()){
				CommodityInfo c = ite.next();
				double money = c.getCommodity_price()*shopcart.get(c);
				price += money;
				sb.append(c.getCommodity_name()+"  ").append(c.getCommodity_price()+"元  ");
				sb.append(shopcart.get(c)).append("份  ").append(money+"元\n");
				BusinessInfo bi = businessTable.get(c.getBusiness_id());
				if(orderForm.containsKey(bi)){
					Goods goods = new Goods(c,shopcart.get(c));
					ArrayList<Goods> list =orderForm.get(bi);
					int index = -1;
					if((index=list.indexOf(goods))>=0){
						list.add(index,goods);
					}else{
						list.add(goods);
					}
					orderForm.put(bi,list);
				}else{
					ArrayList<Goods> list = new ArrayList<Goods>();
					list.add(new Goods(c,shopcart.get(c)));
					orderForm.put(bi,list);
				}
			}
			sb.append("---------------------------------------\n");
			sb.append("合计:").append(price).append("元");
			carttext.setText(sb.toString());
		});
		menuButton[2].addActionListener(event ->{
			bodyPaneArray[0].setVisible(false);
			bodyPaneArray[1].setVisible(false);
			bodyPaneArray[2].setVisible(true);
			bodyPaneArray[3].setVisible(false);
			bodyPaneArray[4].setVisible(false);
			menuButton[0].setIcon(new ImageIcon("img/wk.png"));
			menuButton[1].setIcon(new ImageIcon("img/gwc.png"));
			menuButton[2].setIcon(new ImageIcon("img/dd_c.png"));
			menuButton[3].setIcon(new ImageIcon("img/wd.png"));
		});
		menuButton[3].addActionListener(event ->{
			bodyPaneArray[0].setVisible(false);
			bodyPaneArray[1].setVisible(false);
			bodyPaneArray[2].setVisible(false);
			bodyPaneArray[3].setVisible(true);
			bodyPaneArray[4].setVisible(false);
			menuButton[0].setIcon(new ImageIcon("img/wk.png"));
			menuButton[1].setIcon(new ImageIcon("img/gwc.png"));
			menuButton[2].setIcon(new ImageIcon("img/dd.png"));
			menuButton[3].setIcon(new ImageIcon("img/wd_c.png"));
		});
		backbtn.addActionListener(event -> {
			bodyPaneArray[0].setVisible(true);
			bodyPaneArray[1].setVisible(false);
			bodyPaneArray[2].setVisible(false);
			bodyPaneArray[3].setVisible(false);
			bodyPaneArray[4].setVisible(false);
		});
		for (int i = 0; i < takeOutBar.length; i++) {
			takeOutBar[i].getIntoStore().addActionListener(event -> {
				JButton btn = (JButton)event.getSource();
				TakeOutBar t = (TakeOutBar)btn.getParent();
				System.out.println("用户进入了"+t.getId()+"号店铺:"+t);
				bodyPaneArray[0].setVisible(false);
				bodyPaneArray[1].setVisible(false);
				bodyPaneArray[2].setVisible(false);
				bodyPaneArray[3].setVisible(false);
				bodyPaneArray[4].setVisible(true);
				ArrayList<CommodityInfo> InfoList = allData.get(t.getBusinessInfo());
				BusinessMenu[] bm = new BusinessMenu[InfoList.size()];
				takeOutMenuPane.removeAll();
				for (int j = 0; j < InfoList.size(); j++) {
					bm[j] = new BusinessMenu(InfoList.get(j));
					bm[j].setLayout(null);
					bm[j].setBounds(15,0+j*120, 350, 120);
					takeOutMenuPane.add(bm[j]);
					bm[j].getAddCartBtn().addActionListener(btnevent ->{
						JButton addbtn = (JButton)btnevent.getSource();
						BusinessMenu b = (BusinessMenu)addbtn.getParent();
						CommodityInfo ci = b.getCommodityInfo();
						System.out.println("用户点击了"+ci.getCommodity_name()+"商品!");
						if(shopcart.get(ci) != null){
							shopcart.put(ci,shopcart.get(ci).intValue()+1);
						}else{
							shopcart.put(ci,1);
						}
						System.out.println(shopcart);
					});
				}
			});
		}
	}
}
