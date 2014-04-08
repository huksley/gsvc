package ru.bank.service;

/**
 * Information about client.
 * 
 * @author ruslan
 */
public class Client {

	/**
	 * Lastname/or full name of client.
	 */
	public String name;
	
	/**
	 * First name of person
	 */
	public String name1;
	
	/**
	 * Middlename of person
	 */
	public String name2;
	
	/**
	 * Full serial + number of passport for person
	 */
	public String documentSerialNumber;
	
	/**
	 * Legal customer INN
	 */
	public String taxCode;
	
	/**
	 * Legal customer KPP
	 */
	public String kpp;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getName1() {
		return name1;
	}

	public void setName1(String name1) {
		this.name1 = name1;
	}

	public String getName2() {
		return name2;
	}

	public void setName2(String name2) {
		this.name2 = name2;
	}

	public String getDocumentSerialNumber() {
		return documentSerialNumber;
	}

	public void setDocumentSerialNumber(String documentSerialNumber) {
		this.documentSerialNumber = documentSerialNumber;
	}

	public String getTaxCode() {
		return taxCode;
	}

	public void setTaxCode(String taxCode) {
		this.taxCode = taxCode;
	}

	public String getKpp() {
		return kpp;
	}

	public void setKpp(String kpp) {
		this.kpp = kpp;
	}
}
