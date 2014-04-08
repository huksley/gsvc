package ru.goverment.service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Information about account arrest (block of any further movement of money).
 * 
 * @author ruslan
 */
public class CustomerAccountArrest extends CustomerAccount {

	/**
	 * Date of arrest.
	 */
	public Date arrestDate;
	
	/**
	 * Remaining balance on account to date of arrest
	 */
	public BigDecimal balance;

	public Date getArrestDate() {
		return arrestDate;
	}

	public void setArrestDate(Date arrestDate) {
		this.arrestDate = arrestDate;
	}

	public BigDecimal getBalance() {
		return balance;
	}

	public void setBalance(BigDecimal balance) {
		this.balance = balance;
	}
}
