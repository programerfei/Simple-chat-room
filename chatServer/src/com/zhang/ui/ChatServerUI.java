package com.zhang.ui;

import java.awt.Color;
import java.awt.Container;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.WindowConstants;

import com.zhang.net.ChatServerNet;

public class ChatServerUI extends JFrame{
	
	private int Width=560,Height=600;//窗口的宽和高
	private JTextArea showMsgFieid=null;
	private JTextArea inputMsgField=null;
	private JButton sendMsgBtn=null;
	private JLabel showUserInfoLabel=null;//QQ秀
	private ImageIcon showUserImage=null;
	private JLabel showUserImageLabel=null;
	private String username=null;
	private JScrollPane inputFieldPane=null;
	private JScrollPane showFieldPane=null;
	private ChatServerNet serverNet=null;
	
	public ChatServerUI(String username) {
		this.username=username;
		initUI();
		addComponent();
		showUI();
		addListener();
		
		//建立网络连接，并在网络上不停的监听是否有消息
		serverNet=new ChatServerNet(showMsgFieid);
	}
	
	public void initUI() {
		
		showMsgFieid=new JTextArea();//显示消息
		showMsgFieid.setEditable(false);//文本框不能编辑
		inputMsgField=new JTextArea();//输入消息
		inputMsgField.setLineWrap(true);
		inputMsgField.setWrapStyleWord(true);
		inputMsgField.setAutoscrolls(true);
		
		showMsgFieid.setLineWrap(true);
		showMsgFieid.setWrapStyleWord(true);
		showMsgFieid.setAutoscrolls(true);
		sendMsgBtn=new JButton("发送");
		showUserImage=new ImageIcon("C:\\Users\\11499\\Desktop\\个人事务\\qq秀.jpeg");
		showUserImageLabel=new JLabel(showUserImage,JLabel.CENTER);
		showUserInfoLabel=new JLabel("欢迎你："+this.username,JLabel.CENTER);//显示用户信息的标签
		inputFieldPane=new JScrollPane(inputMsgField);
		showFieldPane=new JScrollPane(showMsgFieid);
	}
	
	public void addComponent() {
		
		
		this.add(showUserInfoLabel);
		showUserInfoLabel.setForeground(new Color(0,176,101));
		showUserInfoLabel.setBounds(Width-180, 430, 170, 20);
		showUserImageLabel.setIcon(showUserImage);
		this.add(showUserImageLabel);
		showUserImageLabel.setBounds(Width-180, 1, 170, 435);
		this.setLayout(null);
		this.add(inputFieldPane);
		inputFieldPane.setBounds(5, Height-200, Width-200, 100);
		this.add(showMsgFieid);
		showMsgFieid.setBounds(5, 3, Width-200, Height-250);
		this.add(sendMsgBtn);
		sendMsgBtn.setBounds(Width-300, Height-95, 100, 30);
		Container container=getContentPane();
		container.add(showFieldPane);
		
	}
	
	public void addListener() {
		this.setDefaultCloseOperation(JFrame .EXIT_ON_CLOSE);
		sendMsgBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				
				String str=inputMsgField.getText();
				if(str==null||str.trim().length()<1) {
					JOptionPane.showMessageDialog(null, "消息不能为空");
				}else {
					serverNet.sendMsgToClient(str);
					inputMsgField.setText("");
				}
				
			}
		});
	}
	
	public void showUI() {
		//窗口左上角图片
		this.setIconImage(new ImageIcon("images\\QQ截图20191020212335.png").getImage());
		int x=((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2);
		int y=((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
		this.setLocation(x, y);
		this.setResizable(false);
		this.setSize(Width, Height);
		this.setVisible(true);
		this.setTitle("服务器聊天端口打开。。。欢迎您"+this.username);//窗口标题
	}
	
	public static void main(String[] args) {
		new ChatServerUI("");
	}

}
