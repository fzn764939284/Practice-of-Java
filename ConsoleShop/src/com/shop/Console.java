package com.shop;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Console {
	List<Good> goodlist = new ArrayList<Good>();
	List<User> userlist = new ArrayList<User>();
	Scanner scan = new Scanner(System.in);
	
	//������
	public static void main(String[] args) {   
		Console cs = new Console();
		cs.Start();
	}
	
	//��ʼ���
    private void Start(){        
    	boolean go_on = true;
    	while(go_on){
    		//�˵���ʾ
    		this.ShowMenu();
        	int n = scan.nextInt();
        	//�û�ѡ��
        	go_on = this.choice(n);
        }
    }
	
	private void ShowMenu(){  
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
	}
	
	private boolean choice(int n){    
		switch (n){
    	case 1:
    		this.reg();
    		break;
    	case 2:
    		this.login();
    		break;
    	case 3:
    		showGood();
    		break;
    	case 4:
    		System.out.println("ѡ����4");
    		break;
    	case 5:
    		this.adminlogin();
    		break;
    	case 6:
    		System.out.println("ѡ����6���������˳���");
    		return false;
    	default:
    		System.out.println("ѡ���������������룡");
    		break;
    	}
		return true;
	}
	
	private void reg(){
		System.out.println();
		System.out.println("****��ӭע�ᣡ****");
		String username = null;
		boolean dis = true;
		while(dis){
			System.out.print("�������û�����");
			username = scan.next();
			for(int i= 0;i<userlist.size();i++){
				String nowusername = userlist.get(i).getUsername();
				if(username.equals(nowusername)){
					System.out.println();
					System.out.println("*****�û�����ռ���ˣ���һ���ɣ�*****");
					dis = false;
					break;
					}
			}
			if(dis == true){
				break;
			}else{
				dis = true;
			}
			
		}
		while(true){
			System.out.print("���������룺");
			String password = scan.next();
			System.out.print("���ٴ��������룺");
			String repassword = scan.next();
			if(password.equals(repassword)){
				User newuser = new User(username,password);
				userlist.add(newuser);
				break;
			}else{
				System.out.println("�������벻һ�£����������룡");
			}
		}
		System.out.println();
		System.out.println("****��ϲ��~ע��ɹ���****");
	 }
     
	private void login(){
		System.out.println();
		System.out.println("****��ӭ��¼��****");
		System.out.print("�������û�����");
		String login_username = scan.next();
		System.out.print("���������룺");
		String login_password = scan.next();
		for(int i=0;i<userlist.size();i++){
			String username = userlist.get(i).getUsername();
			String password = userlist.get(i).getUserpass();
			if(userlist.size() == 0){
				System.out.println();
				System.out.println("****������~��¼ʧ�ܣ�****");
				System.out.println("*û��ע����ѡ��1����С�����������ѡ��2��*");
				break;
			}
			if(username.equals(login_username) && password.equals(login_password)){
				System.out.println();
				System.out.println("****������~��¼�ɹ���****");
				break;
			}else{
				if(i == (userlist.size()-1)){
					System.out.println();
					System.out.println("****������~��¼ʧ�ܣ�****");
					System.out.println("*û��ע����ѡ��1����С�����������ѡ��2��*");
					break;
				}	
			}
		}
		
	}
	
	private void adminlogin(){
		System.out.println();
		System.out.println("****��ӭ����Ա��¼��****");
		while(true){
			System.out.print("�������û�����");
			String login_username = scan.next();
			System.out.print("���������룺");
			String login_password = scan.next();
			if(login_username.equals("admin") && login_password.equals("admin")){
				System.out.println();
				System.out.println("****������~��¼�ɹ���****");
				break;
			}else{
				System.out.println();
				System.out.println("****������~��¼ʧ�ܣ�****");
			}
			
		}
		boolean admingo = true;
		while(admingo){
			admingo = showAdminMenu();	
		}
		
		
	}
	
	private boolean showAdminMenu(){
		System.out.println();
		System.out.println("*****����Աר��*****");
    	System.out.println("\t1.�����Ʒ");
    	System.out.println("\t2.�޸���Ʒ��Ϣ");
    	System.out.println("\t3.ɾ����Ʒ");
    	System.out.println("\t4.�鿴������Ʒ");
    	System.out.println("\t5.��ѯ������Ʒ");
    	System.out.println("\t6.�˳�");
    	System.out.println("************************");
    	System.out.print("����������ѡ��");
    	int n = scan.nextInt();
    	return chooseAdmin(n);
	}
    
	private boolean chooseAdmin(int n){
		switch (n){
    	case 1:
    		addGood();
    		break;
    	case 2:
    		changeGood();
    		break;
    	case 3:
    		delGood();
    		break;
    	case 4:
    		showGood();
    		break;
    	case 5:
    		showOne();
    		break;
    	case 6:
    		System.out.println();
    		System.out.println("****�����˳�����Ա��ݣ�****");
    		return false;
    	default:
    		System.out.println("ѡ���������������룡");
    		break;
    	}
		return true;
	}
	
	private void addGood(){
		boolean go = true;
		while(go){
			System.out.println();
			System.out.println("****�����Ʒ****");
			System.out.print("��������Ʒ��ţ�");
			String id = scan.next();
			System.out.print("��������Ʒ���ƣ�");
			String name = scan.next();
			System.out.print("��������Ʒ�۸�");
			BigDecimal price = scan.nextBigDecimal();
			System.out.print("��������Ʒ�������");
			int num = scan.nextInt();
			Good newgood = new Good(id,name,price,num);
			goodlist.add(newgood);
			System.out.println();
			System.out.println("****�洢�ɹ���****");
			System.out.print("Ҫ�����洢��Ʒ�𣿣�Y/N��");
			String isgo = scan.next();
			if(isgo.equals("N") || isgo.equals("n")){
				go = false;
			}
		}
	}
	
	private void changeGood(){
		System.out.println();
		System.out.println("****�޸���Ʒ****");
		boolean chggo = true;
		while(chggo){
			System.out.print("������Ҫ�޸ĵ���Ʒ��ţ�");
			String id = scan.next();
			for(int i=0;i<goodlist.size();i++){
				if(id.endsWith(goodlist.get(i).getId())){
					System.out.print("��������Ʒ�����޸ĺ�����");
					String name = scan.next();
					System.out.print("��������Ʒ�۸��޸ĺ�����");
					BigDecimal price = scan.nextBigDecimal();
					System.out.print("��������Ʒ������޸ĺ�����");
					int num = scan.nextInt();
					goodlist.get(i).setName(name);
					goodlist.get(i).setNum(num);
					goodlist.get(i).setPrice(price);
					System.out.println("****�޸ĳɹ���****");
					chggo = false;
				}
			}
			if(chggo == false){
				break;
			}else{
				System.out.println("Ҫ�޸ĵ���Ʒδ�ҵ����Ƿ�����޸ģ�Y/N����");
				String chg = scan.next();
				if(chg.equals("N") || chg.equals("n")){
					chggo = false;
				}
			}
		}
	}
	
	private void delGood(){
		boolean delgo = true;
		while(delgo){
			System.out.print("������Ҫɾ������Ʒ��ţ�");
			String id = scan.next();
			for(int i=0;i<goodlist.size();i++){
				if(id.endsWith(goodlist.get(i).getId())){
					goodlist.remove(i);
					System.out.println("****ɾ���ɹ���****");
					delgo = false;
				}
			}
			if(delgo == false){
				break;
			}else{
				System.out.println("Ҫɾ������Ʒδ�ҵ����Ƿ����ɾ����Y/N����");
				String del = scan.next();
				if(del.equals("N") || del.equals("n")){
					delgo = false;
				}
			}
		}
	}
	
	private void showGood(){
		System.out.println();
		System.out.println("****������Ʒ****");
		if(goodlist.size() == 0){
			System.out.println("�����̳ǻ�û����Ʒ��");
		}else{
			for(int i=0;i<goodlist.size();i++){
				goodlist.get(i).showgood();
			}
		}
	}
	
	private void showOne(){
		System.out.println();
		System.out.println("****�鿴����****");
		boolean showgo = true;
		while(showgo){
			System.out.print("������Ҫ�鿴�ı�����ţ�");
			String id = scan.next();
			System.out.println("*****Ҫ�鿴�ı���*****");
			for(int i=0;i<goodlist.size();i++){
				if(id.equals(goodlist.get(i).getId())){
					goodlist.get(i).showgood();
					showgo = false;
				}
			}
			if(showgo == false){
				break;
			}else{
				System.out.println("Ҫ�鿴����Ʒδ�ҵ����Ƿ�����鿴��Y/N����");
				String fin = scan.next();
				if(fin.equals("N") || fin.equals("n")){
					showgo = false;
				}
			}
		}
	}
}
