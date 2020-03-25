package src.com.zhang.ui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.*;
  
import src.com.zhang.tools.FileUtiles;


public class ClientLogin extends JFrame{
	
	private ImageIcon loginLoge=null;
	private  JLabel showLogoLabel=null;
	private int Width=500,Height=450;
	private JLabel userLabel=null;
	private JLabel pwdLabel=null;
	private JTextField usernameText=null;
	private JPasswordField pwdText=null;
	private JButton loginBtn=null,restBtn=null;
	
	/*
	 * 构造方法
	 */
	public ClientLogin() {
		// TODO Auto-generated constructor stub
		init();
		addComponent();
		addListener();
		showFrame();
	}
	
	/*
	 * 初始化界面
	 */
	public void init() {
		this.setTitle("客户端登陆！");
		loginLoge=new ImageIcon("D:\\eclipse_work\\chatServer\\images\\二哈.jpg");
		showLogoLabel=new JLabel(loginLoge,JLabel.CENTER);//初始化JLabel
		
		showLogoLabel.setBounds(0, 0, Width, 150);
		userLabel=new JLabel("用户名：");
		pwdLabel=new JLabel("密码：");
		usernameText=new JTextField();
		pwdText=new JPasswordField();
		loginBtn=new JButton("登陆");
		restBtn=new JButton("重置");
		
	}
	
	/*
	 * 把各种组件在该方法中组装
	 */
	public void addComponent() {
		this.setLayout(null);//容器没有任何的布局
		this.add(showLogoLabel);//把组件添加到容器中
		
		this.add(userLabel);
		userLabel.setBounds(50, 190, 180, 30);
		this.add(pwdLabel);
		pwdLabel.setBounds(50, 250, 180, 30);
		this.add(usernameText);
		usernameText.setBounds(120, 190, 200, 30);
		this.add(pwdText);
		pwdText.setBounds(120, 250, 200, 30);
		this.add(loginBtn);
		loginBtn.setBounds(100, 290, 100, 30);
		this.add(restBtn);
		restBtn.setBounds(220, 290, 100, 30);
		
	}
	
	/*
	 * 组件添加功能
	 */
	public void addListener() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//给按钮添加事件
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String username=usernameText.getText();//获取文本框的值
				String pwd=pwdText.getText();
				
				//还应该进行长度判断
				
				boolean b=FileUtiles.checkLogin(username, pwd);
				if(b==true) {//成功登陆  弹出聊天界面
					new ChatClientUI(username);
					ClientLogin.this.dispose();//关闭窗口
				}else {
					//弹出消息对话框
					JOptionPane.showMessageDialog(null, "用户名或者密码错误！");
				}
				
			}
		});
		
		restBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				usernameText.setText("");
				pwdText.setText("");
			}
		});
	}
	
	/*
	 * 显示窗口
	 */
	public void showFrame() {
		//让窗口从屏幕中间弹出
		/*
		 * 得到整个屏幕的高度和宽度（像素） screenWidth  screenHeight
		 * 其次得到我们自己屏幕的高度和宽度  
		 * 
		 */
		
		int screenWidth=(int)this.getToolkit().getScreenSize().width;
		int screenHeight=(int)this.getToolkit().getScreenSize().height;
		int x=(screenWidth-Width)/2;
		int y=(screenHeight-Height)/2;
		this.setLocation(x, y);
		this.setResizable(false);//让窗口不能变化
		
		this.setVisible(true);
		this.setSize(Width, Height);
		
	}
	
	

	public static void main(String[] args) {
		new ClientLogin();
	}

}
