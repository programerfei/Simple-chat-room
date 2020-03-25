package com.zhang.net;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;

import javax.swing.JOptionPane;
import javax.swing.JTextArea;

public class ChatServerNet {

	private PrintStream ps=null;
	private BufferedReader br=null;
	private JTextArea showMsgFieid=null;
	
	public ChatServerNet(JTextArea showMsgFieid) {
		this.showMsgFieid=showMsgFieid;
			intNet();
			getMessage();
	}
	
	
	public void intNet() {
		try {
			ServerSocket serverSocket=new ServerSocket(9998);
			System.out.println("端口已经监听");
			Socket socket=serverSocket.accept();
			InputStream is=socket.getInputStream();
			br=new BufferedReader(new InputStreamReader(is));
			ps=new PrintStream(socket.getOutputStream());
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			JOptionPane.showMessageDialog(null, "服务器端口9998已被占用");
		}
		
	}
	
	public void sendMsgToClient(String msg) {
		ps.println(msg);
		showMsgFieid.append("我发送的消息："+msg+"\r\n");
	}
	
	public void getMessage() {
		new Thread(new Runnable() {

			@Override
			public void run() {
				// TODO Auto-generated method stub
				while(true) {
					try {
						String getMsg= br.readLine();//如果没有消息就等待
						showMsgFieid.append("收到客户端的消息:"+getMsg+"\r\n");//把字符串追加到文本框的后面
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
