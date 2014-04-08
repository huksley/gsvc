package ru.goverment.service;

import java.util.Date;

/**
 * Passport or any other document which is used to identify client.
 * 
 * @author ruslan
 */
public class IdentityDocument {

	/**
	 * Document serial number (can be null/empty)
	 */
	public String serial;
	
	/**
	 * Document number, must be present
	 */
	public String number;
	
	/**
	 * Birth date, if known.
	 */
	public Date birthDate;
	
	/**
	 * Document issue date, must be present
	 */
	public Date issueDate;

	public String getSerial() {
		return serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public Date getBirthDate() {
		return birthDate;
	}

	public void setBirthDate(Date birthDate) {
		this.birthDate = birthDate;
	}

	public Date getIssueDate() {
		return issueDate;
	}

	public void setIssueDate(Date issueDate) {
		this.issueDate = issueDate;
	}
}
