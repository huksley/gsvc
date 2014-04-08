package ru.goverment.service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Information about customer`s credit.
 * 
 * @author ruslan
 */
public class CustomerCredit {

	/**
	 * Bank identification code, must match callers BIC when returned from {@link GovermentService#findRequest()}
	 */
	public String bic;
	
	/**
	 * Credit contract number
	 */
	public String code;
	
	/**
	 * Date of credit contract
	 */
	public Date openDate;
	
	/**
	 * Total credit qty
	 */
	public BigDecimal qty;

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}	
}
