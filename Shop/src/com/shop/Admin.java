package com.shop;

import java.io.Serializable;
import java.math.BigDecimal;

public class Admin extends User implements Serializable{
	
	//����Ա��¼
	public void AdminLogin(){
		System.out.println();
		System.out.println("****��ӭ����Ա��¼��****");
		while(true){
			System.out.print("�������û�����");
			String login_username = Shop.scan.next();
			System.out.print("���������룺");
			String login_password = Shop.scan.next();
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
	
	//����Աר��
	public boolean showAdminMenu(){
		System.out.println();
		System.out.println("*****����Աר��*****");
    	System.out.println("\t1.�����Ʒ");
    	System.out.println("\t2.�޸���Ʒ��Ϣ");
    	System.out.println("\t3.ɾ����Ʒ");
    	System.out.println("\t4.�鿴������Ʒ");
    	System.out.println("\t5.��ѯ������Ʒ");
    	System.out.println("\t6.�����û�");
    	System.out.println("\t7.�˳�");
    	System.out.println("************************");
    	System.out.print("����������ѡ��");
    	int n = Shop.scan.nextInt();
    	return chooseAdmin(n);
	}
	
	//����Աѡ��
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
    		mangeUser();
    		break;
    	case 7:
    		System.out.println();
    		System.out.println("****�����˳�����Ա��ݣ�****");
    		return false;
    	default:
    		System.out.println("ѡ���������������룡");
    		break;
    	}
		return true;
	}
	
	//�����û�
	public void mangeUser(){
		showUsers();
		System.out.println("������Ҫɾ�����û�����");
		String del = Shop.scan.next();
		delUser(del);
		System.out.println("******ɾ���ɹ���*****");
	}
	
	//��ʾ�û�
	public void showUsers(){
		for(int i=0;i<Shop.userList.size();i++){
			if(Shop.userList.size() == 0){
				System.out.println("ϵͳ��ʱû���û���");
				break;
			}
			Shop.userList.get(i).showUser();
		}
	}
	
	//ɾ���û�
	public void delUser(String name){
		Shop.userList.remove(FindUser(name));
		Shop.userGood.remove(FindUser(name));
	}
	
	//�����Ʒ
	public void addGood(){
		boolean go = true;
		while(go){
			System.out.println();
			System.out.println("****�����Ʒ****");
			System.out.print("��������Ʒ��ţ�");
			String id = Shop.scan.next();
			System.out.print("��������Ʒ���ƣ�");
			String name = Shop.scan.next();
			System.out.print("��������Ʒ�۸�");
			float price = Shop.scan.nextFloat();
			System.out.print("��������Ʒ�������");
			int num = Shop.scan.nextInt();
			Good newgood = new Good(id,name,price,num);
			Shop.goodList.add(newgood);
			System.out.println();
			System.out.println("****�洢�ɹ���****");
			System.out.print("Ҫ�����洢��Ʒ�𣿣�Y/N��");
			String isgo = Shop.scan.next();
			if(isgo.equals("N") || isgo.equals("n")){
				go = false;
			}
		}
	}
	
	//�޸���Ʒ
	public void changeGood(){
		System.out.println();
		System.out.println("****�޸���Ʒ****");
		boolean chggo = true;
		while(chggo){
			System.out.print("������Ҫ�޸ĵ���Ʒ��ţ�");
			String id = Shop.scan.next();
				if(FindGood(id)!=null){
					Good nowgood = FindGood(id);
					System.out.print("��������Ʒ�����޸ĺ�����");
					String name = Shop.scan.next();
					System.out.print("��������Ʒ�۸��޸ĺ�����");
					float price = Shop.scan.nextFloat();
					System.out.print("��������Ʒ������޸ĺ�����");
					int num = Shop.scan.nextInt();
					nowgood.setName(name);
					nowgood.setNum(num);
					nowgood.setPrice(price);
					System.out.println("****�޸ĳɹ���****");
					chggo = false;
				}else{
					System.out.println("Ҫ�޸ĵ���Ʒδ�ҵ����Ƿ�����޸ģ�Y/N����");
					String chg = Shop.scan.next();
					if(chg.equals("N") || chg.equals("n")){
						chggo = false;
					}
				}
			
		}
	}
    
	//ɾ����Ʒ
	public void delGood(){
		boolean delgo = true;
		while(delgo){
			System.out.print("������Ҫɾ������Ʒ��ţ�");
			String id = Shop.scan.next();
			Good nowgood = FindGood(id);	
			if(nowgood!=null ){
					Shop.goodList.remove(nowgood);
					System.out.println("****ɾ���ɹ���****");
					delgo = false;
			}else{
				System.out.println("Ҫɾ������Ʒδ�ҵ����Ƿ����ɾ����Y/N����");
				String del = Shop.scan.next();
				if(del.equals("N") || del.equals("n")){
					delgo = false;
				}
			}
		 }
	}
	
	//������Ʒ
	private void showOne(){
		System.out.println();
		System.out.println("****�鿴����****");
		boolean showgo = true;
		while(showgo){
			System.out.print("������Ҫ�鿴�ı�����ţ�");
			String id = Shop.scan.next();
			System.out.println("*****Ҫ�鿴�ı���*****");
			Good nowgood = FindGood(id);
				if(nowgood != null){
					nowgood.showgood();
					showgo = false;
				}else{
				System.out.println("Ҫ�鿴����Ʒδ�ҵ����Ƿ�����鿴��Y/N����");
				String fin = Shop.scan.next();
				if(fin.equals("N") || fin.equals("n")){
					showgo = false;
				}
			}
		}
	}
}
