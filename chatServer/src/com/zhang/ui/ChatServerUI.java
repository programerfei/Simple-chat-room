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
	
	private int Width=560,Height=600;//���ڵĿ�͸�
	private JTextArea showMsgFieid=null;
	private JTextArea inputMsgField=null;
	private JButton sendMsgBtn=null;
	private JLabel showUserInfoLabel=null;//QQ��
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
		
		//�����������ӣ����������ϲ�ͣ�ļ����Ƿ�����Ϣ
		serverNet=new ChatServerNet(showMsgFieid);
	}
	
	public void initUI() {
		
		showMsgFieid=new JTextArea();//��ʾ��Ϣ
		showMsgFieid.setEditable(false);//�ı����ܱ༭
		inputMsgField=new JTextArea();//������Ϣ
		inputMsgField.setLineWrap(true);
		inputMsgField.setWrapStyleWord(true);
		inputMsgField.setAutoscrolls(true);
		
		showMsgFieid.setLineWrap(true);
		showMsgFieid.setWrapStyleWord(true);
		showMsgFieid.setAutoscrolls(true);
		sendMsgBtn=new JButton("����");
		showUserImage=new ImageIcon("C:\\Users\\11499\\Desktop\\��������\\qq��.jpeg");
		showUserImageLabel=new JLabel(showUserImage,JLabel.CENTER);
		showUserInfoLabel=new JLabel("��ӭ�㣺"+this.username,JLabel.CENTER);//��ʾ�û���Ϣ�ı�ǩ
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
					JOptionPane.showMessageDialog(null, "��Ϣ����Ϊ��");
				}else {
					serverNet.sendMsgToClient(str);
					inputMsgField.setText("");
				}
				
			}
		});
	}
	
	public void showUI() {
		//�������Ͻ�ͼƬ
		this.setIconImage(new ImageIcon("images\\QQ��ͼ20191020212335.png").getImage());
		int x=((int)Toolkit.getDefaultToolkit().getScreenSize().getWidth()/2);
		int y=((int)Toolkit.getDefaultToolkit().getScreenSize().getHeight()/2);
		this.setLocation(x, y);
		this.setResizable(false);
		this.setSize(Width, Height);
		this.setVisible(true);
		this.setTitle("����������˿ڴ򿪡�������ӭ��"+this.username);//���ڱ���
	}
	
	public static void main(String[] args) {
		new ChatServerUI("");
	}

}
