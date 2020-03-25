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
	 * ���췽��
	 */
	public ClientLogin() {
		// TODO Auto-generated constructor stub
		init();
		addComponent();
		addListener();
		showFrame();
	}
	
	/*
	 * ��ʼ������
	 */
	public void init() {
		this.setTitle("�ͻ��˵�½��");
		loginLoge=new ImageIcon("D:\\eclipse_work\\chatServer\\images\\����.jpg");
		showLogoLabel=new JLabel(loginLoge,JLabel.CENTER);//��ʼ��JLabel
		
		showLogoLabel.setBounds(0, 0, Width, 150);
		userLabel=new JLabel("�û�����");
		pwdLabel=new JLabel("���룺");
		usernameText=new JTextField();
		pwdText=new JPasswordField();
		loginBtn=new JButton("��½");
		restBtn=new JButton("����");
		
	}
	
	/*
	 * �Ѹ�������ڸ÷�������װ
	 */
	public void addComponent() {
		this.setLayout(null);//����û���κεĲ���
		this.add(showLogoLabel);//�������ӵ�������
		
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
	 * �����ӹ���
	 */
	public void addListener() {
		this.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		//����ť����¼�
		loginBtn.addActionListener(new ActionListener() {
			
			@Override
			public void actionPerformed(ActionEvent e) {
				// TODO Auto-generated method stub
				String username=usernameText.getText();//��ȡ�ı����ֵ
				String pwd=pwdText.getText();
				
				//��Ӧ�ý��г����ж�
				
				boolean b=FileUtiles.checkLogin(username, pwd);
				if(b==true) {//�ɹ���½  �����������
					new ChatClientUI(username);
					ClientLogin.this.dispose();//�رմ���
				}else {
					//������Ϣ�Ի���
					JOptionPane.showMessageDialog(null, "�û��������������");
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
	 * ��ʾ����
	 */
	public void showFrame() {
		//�ô��ڴ���Ļ�м䵯��
		/*
		 * �õ�������Ļ�ĸ߶ȺͿ�ȣ����أ� screenWidth  screenHeight
		 * ��εõ������Լ���Ļ�ĸ߶ȺͿ��  
		 * 
		 */
		
		int screenWidth=(int)this.getToolkit().getScreenSize().width;
		int screenHeight=(int)this.getToolkit().getScreenSize().height;
		int x=(screenWidth-Width)/2;
		int y=(screenHeight-Height)/2;
		this.setLocation(x, y);
		this.setResizable(false);//�ô��ڲ��ܱ仯
		
		this.setVisible(true);
		this.setSize(Width, Height);
		
	}
	
	

	public static void main(String[] args) {
		new ClientLogin();
	}

}
