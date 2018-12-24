package com.cn.xls;

public class Student {
	
	private String ddbh;
    private String mjhym;
    private String mjzfb;
    private String price;
    private String name;
    private String  shdz;
    private String sjh;
	public String getDdbh() {
		return ddbh;
	}
	public void setDdbh(String ddbh) {
		this.ddbh = ddbh;
	}
	public String getMjhym() {
		return mjhym;
	}
	public void setMjhym(String mjhym) {
		this.mjhym = mjhym;
	}
	public String getMjzfb() {
		return mjzfb;
	}
	public void setMjzfb(String mjzfb) {
		this.mjzfb = mjzfb;
	}
	public String getPrice() {
		return price;
	}
	public void setPrice(String price) {
		this.price = price;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getShdz() {
		return shdz;
	}
	public void setShdz(String shdz) {
		this.shdz = shdz;
	}
	public String getSjh() {
		return sjh;
	}
	public void setSjh(String sjh) {
		this.sjh = sjh;
	}
	@Override
	public String toString() {
		return "Student [ddbh=" + ddbh + ", mjhym=" + mjhym + ", mjzfb="
				+ mjzfb + ", price=" + price + ", name=" + name + ", shdz="
				+ shdz + ", sjh=" + sjh + "]";
	}
     
    
}
