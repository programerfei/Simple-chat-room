package com.zhang.tools;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;



/**
 * ������
 * @author 11499
 *
 */
public class FileUtiles {

	public static String getValue(String key) {
		//����
		Properties p=new Properties();
		try {
			//��ȡbinĿ¼���µ��ļ��������ļ�����io����
			InputStream readDB=FileUtiles.class.getClassLoader().getResourceAsStream("db.properties");
			p.load(readDB);
			return p.getProperty(key,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * �����û����������ж��û��Ƿ��ܷ��½
	 */
	public static boolean checkLogin(String uname,String pwd) {
		String username=FileUtiles.getValue("username");
		String passward=FileUtiles.getValue("pwd");
		//�ж���Ϣ�Ƿ�һ��
		if(username.equals(uname)&&passward.equals(pwd)) {
			return true;
		}else {
			return false;
		}
		
	}
	
	
	public static void main(String[] args) {
		String str=getValue("usern");
		System.out.println(str);
	}
}
