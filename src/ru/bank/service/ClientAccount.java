package ru.bank.service;

import java.util.Date;

/***
 * Account information
 * 
 * @author ruslan
 */
public class ClientAccount {

	/**
	 * Account number
	 */
	public String code;
	
	/**
	 * Account open date
	 */
	public Date openDate;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
}
