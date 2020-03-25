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
						
						showMsgFieid.append("收到客服务器的消息:"+new Date().toLocaleString()+"\r\n"+getMsg+"\r\n");//把字符串追加到文本框的后面
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
