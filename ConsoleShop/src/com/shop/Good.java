package com.shop;

import java.math.BigDecimal;

public class Good {
	private String id;     // ���
	private String name;   // ����
	private BigDecimal price;   // �۸�
	private int num;        // �����
	
	Good(String id,String name,BigDecimal price,int num){
		this.id = id;
		this.name = name;
		this.price = price;
		this.num = num;
	}
	
	public void setId(String id){
		this.id = id;
	}
	
	public void setName(String name){
		this.name = name;
	}
	
	public void setPrice(BigDecimal price){
		this.price = price;
	}
	
	public void setNum(int num){
		this.num = num;
	}
	
	public String getId(){
		return this.id;
	}
	
	public String getName(){
		return name;
	}
	
	public BigDecimal getPrice(){
		return price;
	}
	
	public int getNum(){
		return num;
	}
	
	public void showgood(){
		System.out.println("���:"+id+"   ����:"+name+"   �۸�:"+price+"   �����:"+num);
	}
}
