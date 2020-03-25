package com.zhang.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintStream;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Date;

import javax.swing.JTextArea;

public class ChatClientNet {

	private PrintStream ps=null;
	private BufferedReader br=null;
	private JTextArea showMsgFieid=null;
	
	public ChatClientNet(JTextArea showMsgFieid) {
		// TODO Auto-generated constructor stub
		intNet();
		this.showMsgFieid=showMsgFieid;
		getMessage();
	}
	
	public void intNet() {
		try {
			Socket socket=new Socket("127.0.0.1",9998);
			OutputStream os=socket.getOutputStream();
			ps=new PrintStream(os);
			br=new BufferedReader(new InputStreamReader(socket.getInputStream()));
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}
	
	public void sendMsgToClient(String msg) {
		ps.println(msg);
		showMsgFieid.append("�ҷ��͵���Ϣ��"+msg+"\r\n");
	}
	
	public void getMessage() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					try {
						String getMsg= br.readLine();//���û����Ϣ�͵ȴ�
						
						showMsgFieid.append("�յ��ͷ���������Ϣ:"+new Date().toLocaleString()+"\r\n"+getMsg+"\r\n");//���ַ���׷�ӵ��ı���ĺ���
					} catch (IOException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
				}
			}
			
		}).start();
	}
	public static void main(String[] args) {
		
	}
}
