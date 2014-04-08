package ru.goverment.service;

import java.util.Date;

/**
 * Information about customer account
 * 
 * @author ruslan
 */
public class CustomerAccount {

	/**
	 * Bank identitication code, must match callers BIC when returned from {@link GovermentService#findRequest()}
	 */
	public String bic;
	
	/**
	 * Account number
	 */
	public String picture;
	
	/**
	 * Account open date
	 */
	public Date openDate;

	public String getBic() {
		return bic;
	}

	public void setBic(String bic) {
		this.bic = bic;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public Date getOpenDate() {
		return openDate;
	}

	public void setOpenDate(Date openDate) {
		this.openDate = openDate;
	}
}
