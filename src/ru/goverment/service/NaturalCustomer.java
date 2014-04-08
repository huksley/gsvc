package ru.goverment.service;

/**
 * Information about person.
 * 
 * @author ruslan
 */
public class NaturalCustomer {
	/**
	 * Last name of client, must be present.
	 */
	public String lastName;
	
	/**
	 * First name of client, must be present.
	 */
	public String firstName;
	
	/**
	 * Middle name of client, if available.
	 */
	public String middleName;
	
	/**
	 * Passport or any other document which is used to identify client.
	 */
	public IdentityDocument identityDocument;

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getMiddleName() {
		return middleName;
	}

	public void setMiddleName(String middleName) {
		this.middleName = middleName;
	}

	public IdentityDocument getIdentityDocument() {
		return identityDocument;
	}

	public void setIdentityDocument(IdentityDocument identityDocument) {
		this.identityDocument = identityDocument;
	}	
}
