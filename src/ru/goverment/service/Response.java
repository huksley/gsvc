package ru.goverment.service;

/**
 * Response from bank.
 * 
 * @author ruslan
 */
public class Response {

	/**
	 * ID of request on which this response is generated.
	 * Must be equal to {@link Request#id}.
	 */
	public String requestId;
	
	/**
	 * If {@link Request#type} is {@link RequestType#INQUIRY} returns all open accounts of client
	 * If {@link Request#type} is {@link RequestType#ARREST} must be null.
	 */
	public CustomerAccount[] accounts;
	
	/**
	 * If {@link Request#type} is {@link RequestType#INQUIRY} returns all open credit of client
	 * If @link Request#type} is {@link RequestType#ARREST} must be null. 
	 */
	public CustomerCredit[] credits;
	
	/**
	 * If {@link Request#type} is {@link RequestType#ARREST} returns all blocked accounts and their balance.
	 * If {@link Request#type} is {@link RequestType#INQUIRY} must be null.
	 */
	public CustomerAccountArrest[] accountArrest;

	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public CustomerAccount[] getAccounts() {
		return accounts;
	}

	public void setAccounts(CustomerAccount[] accounts) {
		this.accounts = accounts;
	}

	public CustomerCredit[] getCredits() {
		return credits;
	}

	public void setCredits(CustomerCredit[] credits) {
		this.credits = credits;
	}

	public CustomerAccountArrest[] getAccountArrest() {
		return accountArrest;
	}

	public void setAccountArrest(CustomerAccountArrest[] accountArrest) {
		this.accountArrest = accountArrest;
	}
}
