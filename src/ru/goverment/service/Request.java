package ru.goverment.service;

/**
 * Request either to inquiry about available
 * accounts or credits for client or arrest (block) any money available.
 * 
 * @author ruslan
 */
public class Request {

	/**
	 * ID of request. Must be present. Have form fsspNNNNNN...
	 */
	public String id;
	
	/**
	 * Type of request, either {@link RequestType#INQUIRY} or {@link RequestType#ARREST}
	 */
	public int type;
	
	/**
	 * State of request, see {@link RequestState} for possible values.
	 */
	public int state;	
	
	/**
	 * This request is about company (organization).
	 * 
	 * Either {@link #legalCustomer} or {@link #naturalCustomer} must be specified.
	 * Both can`t be specified.
	 */
	public LegalCustomer legalCustomer;
	
	/**
	 * This request is about person.Either {@link #legalCustomer} or {@link #naturalCustomer} must be specified.
	 * Both can`t be specified.
	 */
	public NaturalCustomer naturalCustomer;
	
	/**
	 * Accounts on which arrest must be placed. Can only be specified if type == {@link RequestType#ARREST}.
	 */
	public CustomerAccount[] accounts;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getState() {
		return state;
	}

	public void setState(int state) {
		this.state = state;
	}

	public LegalCustomer getLegalCustomer() {
		return legalCustomer;
	}

	public void setLegalCustomer(LegalCustomer legalCustomer) {
		this.legalCustomer = legalCustomer;
	}

	public NaturalCustomer getNaturalCustomer() {
		return naturalCustomer;
	}

	public void setNaturalCustomer(NaturalCustomer naturalCustomer) {
		this.naturalCustomer = naturalCustomer;
	}

	public CustomerAccount[] getAccounts() {
		return accounts;
	}

	public void setAccounts(CustomerAccount[] accounts) {
		this.accounts = accounts;
	}
}
