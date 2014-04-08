package ru.bank.service;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Service implementation.
 * 
 * @author ruslan
 */
public class BankService {

	public String getBIC() {
		return "328282881";
	}
	
	public ClientAccount[] getAccounts(Client client) {
		return new ClientAccount[0];
	}
	
	public ClientCredit[] getCredits(Client client) {
		return new ClientCredit[0];
	}
	
	public BigDecimal arrestAccount(Client client, ClientAccount acc, Date arrestDate) {
		return new BigDecimal(1000.00);
	}
}
