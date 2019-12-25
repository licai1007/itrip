package org.java.quartz;

import java.awt.Frame;

public class Client extends Frame{
	public void myClient(){
		super.setLocation(50,50);//窗口位置
		super.setSize(500,500);//窗口大小
		super.setTitle("测试");
		super.setVisible(true);//可见
	}
	public static void main(String[] args) {
		Client client = new Client();
		client.myClient();
	}
}
