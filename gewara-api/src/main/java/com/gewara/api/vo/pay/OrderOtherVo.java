package com.gewara.api.vo.pay;

import java.io.Serializable;

import org.apache.commons.lang.ArrayUtils;
import org.apache.commons.lang.StringUtils;

import com.gewara.api.vo.BaseVo;

public class OrderOtherVo extends BaseVo{
	
	
	private static final long serialVersionUID = -135038508746485007L;
	public static final String PAY_CARD = "ABD";
	public static final String PAY_DISCOUNT = "M";
	private  Long id;
	private String takemethod;		//(A 电子票  、E 快递  、I 身份证电子票、 A,E,I 电子票+快递+身份证电子票)
	private String expressid;		//快递方式
	private String elecard;
	private boolean openPointPay;
	private int minpoint;
	private int maxpoint;
	private boolean ewarning;		//是否包含电子票
	private boolean greetings;
	
	public OrderOtherVo(){}
	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getTakemethod() {
		return takemethod;
	}
	public void setTakemethod(String takemethod) {
		this.takemethod = takemethod;
	}
	public boolean isExpress() {
		return StringUtils.isNotBlank(expressid);
	}
	public boolean isOpenCardPay() {
		return StringUtils.containsAny(this.elecard, PAY_CARD);
	}
	
	public boolean isOpenPointPay() {
		return openPointPay;
	}
	
	public boolean isDisCountPay(){
		return StringUtils.contains(this.elecard, PAY_DISCOUNT);
	}
	public void setOpenPointPay(boolean openPointPay) {
		this.openPointPay = openPointPay;
	}
	public int getMinpoint() {
		return minpoint;
	}
	public void setMinpoint(int minpoint) {
		this.minpoint = minpoint;
	}
	public int getMaxpoint() {
		return maxpoint;
	}
	public void setMaxpoint(int maxpoint) {
		this.maxpoint = maxpoint;
	}
	
	public String getExpressid() {
		return expressid;
	}
	public String getElecard() {
		return elecard;
	}
	public void setElecard(String elecard) {
		this.elecard = elecard;
	}
	public void setExpressid(String expressid) {
		this.expressid = expressid;
	}
	public boolean isEwarning() {
		return ewarning;
	}
	public void setEwarning(boolean ewarning) {
		this.ewarning = ewarning;
	}
	public boolean isGreetings() {
		return greetings;
	}
	public void setGreetings(boolean greetings) {
		this.greetings = greetings;
	}
	public boolean hasTakemethod(String... methods){
		if(ArrayUtils.isEmpty(methods)) return false;
		for (String method : methods) {
			if(!StringUtils.contains(this.takemethod, method)){
				return false;
			}
		}
		return true;
	}
	
	public void insertElecard(String ecard){
		if(isOpenCardPay() && isDisCountPay() || StringUtils.isBlank(ecard)){
			return;
		}
		if(StringUtils.isBlank(this.elecard)){
			this.elecard = ecard;
		}else if(StringUtils.equals(ecard, PAY_CARD)){
			if(!StringUtils.contains(this.elecard, ecard)){
				this.elecard = ecard + this.elecard;
			}
		}else if(StringUtils.equals(ecard,PAY_DISCOUNT)){
			if(!StringUtils.contains(this.elecard, ecard)){
				this.elecard += ecard;
			}
		}
	}
	@Override
	public Serializable realId() {
		return id;
	}
}
