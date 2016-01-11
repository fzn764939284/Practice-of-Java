package com.shop;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Iterator;
import java.util.List;
import java.util.Set;

public class User implements Serializable{
	    
		private String username;
		private String userpass;
		private boolean isLogin;
		
		//ע��
		public void reg(){     
				System.out.println();
				System.out.println("****��ӭע�ᣡ****");
				String username = null;
				boolean dis = true;
				while(dis){
					System.out.print("�������û�����");
					username = Shop.scan.next();
					if(FindUser(username)!=null)
					{
						System.out.println();
						System.out.println("*****�û�����ռ���ˣ���һ���ɣ�*****");
						dis = false;
					}
					if(dis){
						break;
					}else{
					    dis = true;
					}
					
				}
				while(true){
					System.out.print("���������룺");
					String password = Shop.scan.next();
					System.out.print("���ٴ��������룺");
					String repassword = Shop.scan.next();
					if(password.equals(repassword)){
						User newuser = new User(username,password);
						Shop.userList.add(newuser);
						Shop.userGood.put(newuser, Shop.mygoodList);
						break;
					}else{
						System.out.println("�������벻һ�£����������룡");
					}
				}
				isLogin = true;
				System.out.println();
				System.out.println("****��ϲ��~ע��ɹ���****");
				
				
		}
	    //��¼
		public void login(){
			System.out.println();
			System.out.println("****��ӭ��¼��****");
			System.out.print("�������û�����");
			String login_username = Shop.scan.next();
			System.out.print("���������룺");
			String login_password = Shop.scan.next();
			if(FindUser(login_username)!=null){
				if(FindUser(login_username).getUserpass().equals(login_password)){
					Shop.onlineuser = new User(login_username,login_password);
					Shop.onlineuser.setIsLogin(true);
					System.out.println();
					System.out.println("****������~��¼�ɹ���****");
				}else{
					System.out.println();
					System.out.println("****������~����������벻��ȷ��****");
				}
			}
			else{
				System.out.println();
				System.out.println("****������~�㻹û��ע���أ�****");
			}
		}
		//��ʾ��Ʒ���̳У�
		public void showGood(){
			System.out.println();
			System.out.println("****������Ʒ****");
			if(Shop.goodList.size() == 0){
				System.out.println("�����̳ǻ�û����Ʒ��");
			}else{
				for(int i=0;i<Shop.goodList.size();i++){
					Shop.goodList.get(i).showgood();
				}
				if(Shop.onlineuser.getIsLogin()){
					Shop.onlineuser.buy();
					Shop.onlineuser.save();
				}else{
					System.out.println("���ȵ�¼�ٹ�����Ʒ��");
				}
			}
		}
		//���湺�ﳵ
		public void save(){
			if(Shop.onlineuser.getIsLogin()){
				Set<User> keyuser = Shop.userGood.keySet();
				Iterator<User> ite = keyuser.iterator();
				boolean iskey = false;
				int num;
				// �����û��Ĺ��ﳵ
				for(User user:keyuser){
					if(ite.hasNext()){
						User nowuser =ite.next();
						String nowname = (String)nowuser.getUsername();
						if(Shop.onlineuser.getUsername().equals(nowname)){
							List<Good> mygoodlist = Shop.userGood.get(nowuser);
							Shop.userGood.remove(Shop.onlineuser);
							Shop.userGood.put(Shop.onlineuser, mygoodlist);
							iskey = true;
							break;
						}
					}
				}
					if(!iskey){
						Shop.userGood.put(Shop.onlineuser, Shop.mygoodList);
					}
				}
				
		}
		//��ʾ���ﳵ
		public void showMyGood(){
			if(Shop.onlineuser.getIsLogin()){
				Set<User> keyuser = Shop.userGood.keySet();
				Iterator<User> ite = keyuser.iterator();
				boolean iskey = false;
				float Sumprice = 0;
				float oneprice = 0;
				int num;
				// �����û��Ĺ��ﳵ
				for(User user:keyuser){
					if(ite.hasNext()){
						User nowuser =ite.next();
						String nowname = (String)nowuser.getUsername();
						if(Shop.onlineuser.getUsername().equals(nowname)){
							System.out.println();
							System.out.println("*****���ﳵ*****");
							List<Good> mygoodList = Shop.userGood.get(nowuser);
							for(int i = 0;i < mygoodList.size();i++ ){
								mygoodList.get(i).showgood();
								oneprice = mygoodList.get(i).getPrice();
								num = mygoodList.get(i).getNum();
								Sumprice += num*oneprice;
								iskey = true;	
							}
							System.out.println("���б�������"+Sumprice+"Ԫ");
							break;
						}
					}
				}
				if(!iskey){
					System.out.println();
					System.out.println("*****���ﳵ�ﻹû�����ݣ�*****");
				}
				
			}else{
				System.out.println();
				System.out.println("*****���ȵ�¼�ڲ鿴���ﳵ��*****");
			}
		}
		//�˳����湺�ﳵ
		public void back(){
			if(this.getIsLogin()){
				Set<User> keyname = Shop.userGood.keySet();
				Iterator<User> ite = keyname.iterator();
				boolean iskey = false;
				// �����û��Ĺ��ﳵ
				for(User username:keyname){
					if(ite.hasNext()){
						if(Shop.onlineuser.getUsername().equals(Shop.userGood.get(ite.next()))){
							Shop.userGood.remove(Shop.onlineuser.getUsername());
							Shop.userGood.put(Shop.onlineuser,Shop.mygoodList);
							iskey = true;
						}
					}
				}
				if(iskey){
					Shop.userGood.put(Shop.onlineuser,Shop.mygoodList);
				}
				Shop.onlineuser.setIsLogin(false);
			}
				
		}
		//�����û�
		public User FindUser(String name){
				for(int i=0;i<Shop.userList.size();i++){
					if(name.equals(Shop.userList.get(i).getUsername())){
						return Shop.userList.get(i);
					}
				}
				return null;
			
		}
        //������Ʒ
		public Good FindGood(String id){
			Good nowgood = null;
			for(int i=0;i<Shop.goodList.size();i++){
				nowgood = Shop.goodList.get(i);
				if(id.equals(nowgood.getId())){
					return nowgood;
				}
			}
			return null;
		}
		//����
		public void buy(){
			System.out.println();
			System.out.println("****������Ʒ****");
			System.out.print("��������Ʒ���(���빺��������N)��");
			String id = Shop.scan.next();
		    if(id.equals("N") || id.equals("n")){
					
			}else if(FindGood(id)!=null)
			{
					System.out.print("�����빺��������");
					int num= Shop.scan.nextInt();
					float price = FindGood(id).getPrice();
					String name = FindGood(id).getName();
					Good mygood = new Good(id,name,price,num);
					FindGood(id).setNum((FindGood(id).getNum()-num));
					Shop.mygoodList.add(mygood);
				
			}else{
				System.out.println("");
				System.out.println("****��Ҫ�������Ʒ�����ڣ�****");
			}
			
			
		}
		User(){
			
		}
		User(String username,String userpass){
			this.username = username;
			this.userpass = userpass;
		}
		
		public void setIsLogin(boolean isLogin){
			this.isLogin = isLogin;
		}

		public boolean getIsLogin(){
			return isLogin;
		}
		
		public void setUsername(String username){
			this.username = username;
		}
		
		public void setUserpass(String userpass){
			this.userpass = userpass;
		}
		
		public String getUsername(){
			return username;
		}
		
		public String getUserpass(){
			return userpass;
		}
		
		public void showUser(){
			System.out.println("�û�����"+username + "  ���룺" + userpass);
		}
}
