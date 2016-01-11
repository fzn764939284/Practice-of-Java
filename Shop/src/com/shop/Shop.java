package com.shop;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Shop implements Serializable{
	//����
	static List<User> userList= new ArrayList<User>();
	static List<Good> goodList= new ArrayList<Good>();
	static List<Good> mygoodList = new ArrayList<Good>();
	static Map<User,List> userGood = new HashMap<User,List>();
	
	static Scanner scan = new Scanner(System.in);
	static User onlineuser = new User(); 
	
	// �ļ���
	File userfile = new File("user.txt");
	File goodfile = new File("good.txt");
	File usergoodfile = new File("usergood.txt");
	ObjectInputStream ois = null; 
	ObjectOutputStream oos = null;
	
	//��������Ϊ�˵��÷���
	Admin admin = new Admin();
	User user = new User();
	
	//������
	public static void main(String[] args) {
	Shop shop = new Shop();
      shop.run ();
	}
	// ����
	public void run(){
		onlineuser.setIsLogin(false);
		isHad();
		FromFile();
		boolean is_go = true;
		while(is_go){
			is_go = this.showMenu();
		}
		
	}

	//�˵���ʾ
	public boolean showMenu(){
		System.out.println();
		System.out.println("*****��ӭ���������̳�*****");
    	System.out.println("\t1.�û�ע��");
    	System.out.println("\t2.�û���¼");
    	System.out.println("\t3.�鿴�̳�");
    	System.out.println("\t4.���ﳵ");
    	System.out.println("\t5.����Ա��¼");
    	System.out.println("\t6.�˳�");
    	System.out.println("************************");
    	System.out.print("����������ѡ��");
    	int n = scan.nextInt();
    	return choice(n);
	}

	//�û�ѡ��
	public boolean choice(int n){
        
		switch (n){
    	case 1:
    	     user.reg();
    		break;
    	case 2:
    		 user.login();
    		break;
    	case 3:
    		 admin.showGood();
    		break;
    	case 4:
    		user.showMyGood();
    		break;
    	case 5:
    		admin.AdminLogin();
    		break;
    	case 6:
    		System.out.println("���˳��̳ǣ�");
    		user.back();
    		ToFile();
    		return false;
    	default:
    		System.out.println("ѡ���������������룡");
    		break;
    	}
		return true;
	}

	//�ļ��Ƿ����
	public void isHad(){
		if(!userfile.exists()){
			try {
				userfile.createNewFile();
				if(!goodfile.exists()){
					goodfile.createNewFile();
				}
				if(!usergoodfile.exists()){
					usergoodfile.createNewFile();
				}
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}
    
	//���ļ�������Ϣ
	public void FromFile(){
		try {
			ois = new ObjectInputStream(new FileInputStream(new File("user.txt")));
			Shop.userList = (ArrayList<User>)ois.readObject();
			ois.close();
			ois = new ObjectInputStream(new FileInputStream(new File("good.txt")));
			Shop.goodList = (ArrayList<Good>)ois.readObject();
			ois.close();
			ois = new ObjectInputStream(new FileInputStream(new File("usergood.txt")));
			Shop.userGood = (Map<User, List>)ois.readObject();
			ois.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}
	
	//��Ϣ�����ļ�
	public void ToFile(){
		try {
			oos = new ObjectOutputStream(new FileOutputStream("user.txt"));
			oos.writeObject(Shop.userList);
			oos.close();
			oos = new ObjectOutputStream(new FileOutputStream("good.txt"));
			oos.writeObject(Shop.goodList);
			oos.close();
			oos = new ObjectOutputStream(new FileOutputStream("usergood.txt"));
			oos.writeObject(Shop.userGood);
			oos.close();
		} catch (Exception e) {
			e.printStackTrace();
		} 
		
	}
	
}
