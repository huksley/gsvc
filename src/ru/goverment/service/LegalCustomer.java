package ru.goverment.service;

/**
 * Information about company or organization. 
 * 
 * @author ruslan
 */
public class LegalCustomer {

	/**
	 * Full official name of organization.
	 */
	public 	String fullName;
	
	/**
	 * Short official name of organization.
	 * Must be present.
	 */
	public String shortName;
	
	/**
	 * INN (Tax service identification number). Must be present.
	 */
	public String taxCode;
	
	/**
	 * KPP. Must be present.
	 */
	public String kpp;

	public String getFullName() {
		return fullName;
	}

	public void setFullName(String fullName) {
		this.fullName = fullName;
	}

	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
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
