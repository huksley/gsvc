package ru.bank.service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Client credit
 * 
 * @author ruslan
 */
public class ClientCredit {

	/**
	 * Credit contract number
	 */
	public String code;
	
	/**
	 * Credit open date
	 */
	public Date openDate;
	
	/**
	 * Credit qty
	 */
	public BigDecimal qty;

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

	public BigDecimal getQty() {
		return qty;
	}

	public void setQty(BigDecimal qty) {
		this.qty = qty;
	}
}
