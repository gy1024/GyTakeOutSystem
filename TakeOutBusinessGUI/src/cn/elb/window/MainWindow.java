package cn.elb.window;

import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;

import org.jb2011.lnf.beautyeye.BeautyEyeLNFHelper;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.TypeReference;

import cn.elb.entity.BusinessInfo;
import cn.elb.entity.DataPackage;
import cn.elb.entity.Request;
import cn.elb.socket.SendData;
import cn.hjj.funnction.MyThread;

import javax.swing.JScrollPane;
import javax.swing.JButton;
import javax.swing.JTextArea;
import javax.swing.ScrollPaneConstants;
import javax.swing.JTextField;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;

public class MainWindow {

	private JFrame frame;
	private JTextField businessName;
	private JPasswordField businesspassword;
	private BusinessInfo businessInfo;
	private JButton loginbtn;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					System.setProperty("sun.java2d.noddraw", "true");
					BeautyEyeLNFHelper.launchBeautyEyeLNF();	//皮肤美化包
					UIManager.put("RootPane.setupButtonVisible", false);
					MainWindow window = new MainWindow();
					window.frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public MainWindow() {
		initialize();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.setSize(500, 660);
		frame.setTitle("饿了吧卖家接单系统");
		frame.setLocationRelativeTo(null);//窗体居中显示
		frame.setResizable(false);//禁止拉伸
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		JPanel jp = new JPanel();
		frame.getContentPane().add(jp);
		jp.setLayout(null);
		
		
		JScrollPane scrollPane = new JScrollPane();
		scrollPane.setBounds(10, 10, 431, 538);
		scrollPane.setHorizontalScrollBarPolicy(ScrollPaneConstants.HORIZONTAL_SCROLLBAR_NEVER);//取消水平（左右滚动条）
		jp.add(scrollPane);
		
		JTextArea textArea = new JTextArea();
		textArea.setLineWrap(true);
		textArea.setEditable(false);
		textArea.setFont(new Font("黑体", 0, 15));
		scrollPane.setViewportView(textArea);
		
		loginbtn = new JButton("登录");
		loginbtn.setBounds(348, 554, 93, 23);
		jp.add(loginbtn);
		loginbtn.addActionListener(event ->{
			if(businessName.getText().equals("")&&businesspassword.getText().equals("")){
				JOptionPane.showMessageDialog(null,"输入框不能为空！","提示框",JOptionPane.ERROR_MESSAGE);
			}else{
				String str =SendData.sendData(new DataPackage<BusinessInfo>(Request.商家登录,new BusinessInfo(businessName.getText(),businesspassword.getText())));
				DataPackage<BusinessInfo> dp = JSON.parseObject(str,new TypeReference<DataPackage<BusinessInfo>>(){});
				if(dp==null){
					JOptionPane.showMessageDialog(null,"账号或密码输入错误！","提示框",JOptionPane.ERROR_MESSAGE);
				}else{
					businessInfo = dp.getO();
					JOptionPane.showMessageDialog(null,"登录成功！等待接单!","提示框",JOptionPane.PLAIN_MESSAGE);
					new Thread(new MyThread(textArea,businessInfo)).start();
				}
			}
		});
		
		businessName = new JTextField();
		businessName.setBounds(63, 554, 120, 22);
		jp.add(businessName);
		
		JLabel label = new JLabel("账号");
		label.setBounds(20, 557, 54, 15);
		jp.add(label);
		
		JLabel label_1 = new JLabel("密码");
		label_1.setBounds(186, 557, 54, 15);
		jp.add(label_1);
		
		businesspassword = new JPasswordField();
		businesspassword.setBounds(223, 554, 111, 22);
		jp.add(businesspassword);
	}
}
