package com.zhang.tools;

import java.util.Properties;
import java.io.IOException;
import java.io.InputStream;



/**
 * 工具类
 * @author 11499
 *
 */
public class FileUtiles {

	public static String getValue(String key) {
		//集合
		Properties p=new Properties();
		try {
			//读取bin目录下下的文件，并把文件放入io流中
			InputStream readDB=FileUtiles.class.getClassLoader().getResourceAsStream("db.properties");
			p.load(readDB);
			return p.getProperty(key,null);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}
	/*
	 * 根据用户名和密码判断用户是否能否登陆
	 */
	public static boolean checkLogin(String uname,String pwd) {
		String username=FileUtiles.getValue("username");
		String passward=FileUtiles.getValue("pwd");
		//判断信息是否一致
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
