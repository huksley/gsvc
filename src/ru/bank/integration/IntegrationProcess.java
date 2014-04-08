package ru.bank.integration;

import java.math.BigDecimal;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.logging.Logger;

import javax.xml.datatype.DatatypeFactory;
import javax.xml.datatype.XMLGregorianCalendar;

import ru.bank.service.client.BankServicePortType;
import ru.bank.service.client.Client;
import ru.bank.service.client.ClientAccount;
import ru.bank.service.client.ClientCredit;
import ru.goverment.service.client.CustomerAccount;
import ru.goverment.service.client.CustomerAccountArrest;
import ru.goverment.service.client.CustomerCredit;
import ru.goverment.service.client.GovermentServicePortType;
import ru.goverment.service.client.IdentityDocument;
import ru.goverment.service.client.LegalCustomer;
import ru.goverment.service.client.NaturalCustomer;
import ru.goverment.service.client.Request;
import ru.goverment.service.client.Response;

/**
 * Implementation of process.
 * <p>
 * MUST BE IMPLEMENTED USING BPEL/BPM/MOM.
 * 
 * @author ruslan
 */
public class IntegrationProcess {
	
	// ==== Copied from Javadoc ====
	
    public static final int STATE_NEW = 1;
    public static final int STATE_ACCEPTED = 2;
    public static final int STATE_FULLFILLED = 3;
    public static final int STATE_ERROR = 4;
    
    // ==== Copied from Javadoc ====
   
	public static final int TYPE_INQUIRY = 1;
	public static final int TYPE_ARREST = 2;    
	
	/**
	 * Process all available requests.
	 * 
	 * @param gov Goverment service client
	 * @param bank Bank service client
	 * @param log Logger
	 */
	public void process(GovermentServicePortType gov, BankServicePortType bank, Logger log) {
		List<Request> requests = gov.findRequest();
		for (Request request: requests) {
			processRequest(gov, bank, log, request);
		}
	}

	/**
	 * Process single request.
	 * 
	 * @param gov
	 * @param bank
	 * @param log
	 * @param request
	 */
	public void processRequest(GovermentServicePortType gov, BankServicePortType bank, Logger log, Request request) {
		try {
			// Invoke -> mark as processing
			gov.updateState(request.getId(), STATE_ACCEPTED, "");

			// Assign -> Create response
			String bic = bank.getBIC();
			Response response = new Response();
			response.setRequestId(request.getId());
			
			// Assign -> Adapt client
			Client c = new Client();
			LegalCustomer leg = request.getLegalCustomer();
			NaturalCustomer nat = request.getNaturalCustomer();				
			if (leg != null) {
				c.setName(leg.getFullName());
				c.setTaxCode(leg.getTaxCode());
				c.setKpp(leg.getKpp());
				log.info("Legal customer " + leg.getFullName() + ", " + leg.getTaxCode() + ", " + leg.getKpp());
			} else 
			if (nat != null) {
				c.setName(nat.getLastName());
				c.setName1(nat.getFirstName());
				c.setName2(nat.getMiddleName());
				IdentityDocument doc = nat.getIdentityDocument();
				if (doc != null) {
					c.setDocumentSerialNumber(doc.getSerial() + doc.getNumber());
				} else {
					throw new IllegalArgumentException("No document for naturalCustomer!");
				}
				log.info("Natural customer " + nat.getLastName() + ", " + nat.getFirstName() + ", " + nat.getMiddleName());
			} else {
				throw new IllegalArgumentException("Bot legalCustomer/naturalCustomer is null!"); 
			}
			
			if (request.getType() == TYPE_INQUIRY) {
				log.info("Processing inquiry " + request.getId());
				
				// Invoke -> Find all client accounts
				List<ClientAccount> bankAccounts = bank.getAccounts(c);
				log.info("Found accounts for client: " + bankAccounts.size());
				
				// Invoke -> Find all client credits
				List<ClientCredit> bankCredits = bank.getCredits(c);
				log.info("Found credits for client: " + bankAccounts.size());
				
				// Assign -> Accounts
				List<CustomerAccount> responseAccounts = response.getAccounts();
				for (ClientAccount bankAccount: bankAccounts) {
					CustomerAccount responseAccount = new CustomerAccount();
					responseAccount.setBic(bic);
					responseAccount.setPicture(bankAccount.getCode());
					responseAccount.setOpenDate(bankAccount.getOpenDate());
					responseAccounts.add(responseAccount);
				}
				
				// Assign -> Credits
				List<CustomerCredit> responseCredits = response.getCredits();
				for (ClientCredit bankCredit: bankCredits) {
					CustomerCredit responseCredit = new CustomerCredit();
					responseCredit.setBic(bic);
					responseCredit.setOpenDate(bankCredit.getOpenDate());
					responseCredit.setQty(bankCredit.getQty());
					responseCredit.setCode(bankCredit.getCode());
					responseCredits.add(responseCredit);
				}					
			} else 
			if (request.getType() == TYPE_ARREST) {
				log.info("Processing arrest " + request.getId());
				
				List<CustomerAccount> requestAccounts = request.getAccounts();
				log.info("Accounts to arrest: " + requestAccounts.size());
				
				// Assign -> Arrest date is today (overly simplified) 
				GregorianCalendar now = (GregorianCalendar) Calendar.getInstance();
				XMLGregorianCalendar xnow = DatatypeFactory.newInstance().newXMLGregorianCalendar(now);
				xnow.setTime(0, 0, 0);
								
				// Invoke -> Arrest all accounts				
				List<CustomerAccountArrest> responseArrestAccounts = response.getAccountArrests();
				for (CustomerAccount requestAccount: requestAccounts) {
					if (requestAccount != null) {
    					log.info("Arrest account " + requestAccount);
    					
    					// Assign -> Prepare account to arrest
    					ClientAccount bankAccount = new ClientAccount();
    					bankAccount.setCode(requestAccount.getPicture());
    					bankAccount.setOpenDate(requestAccount.getOpenDate());
    					
    					// Invoke -> Arrest specified account
    					BigDecimal qty = bank.arrestAccount(c, bankAccount, xnow);
    					log.info("Account " + requestAccount.getPicture() + " arrested at " + now + ", qty " + qty);
    					
    					CustomerAccountArrest responseArrestAccount = new CustomerAccountArrest();
    					responseArrestAccount.setBic(bic);
    					responseArrestAccount.setOpenDate(requestAccount.getOpenDate());
    					responseArrestAccount.setArrestDate(xnow);
    					responseArrestAccount.setBalance(qty);
    					responseArrestAccount.setPicture(requestAccount.getPicture());
    					responseArrestAccounts.add(responseArrestAccount);
					}
				}
			} else {
				throw new IllegalArgumentException("Unknown type: " + request.getType());
			}
			
			// Invoke -> Send response
			log.info("Sending response for " + request.getId() + ": " + response);
			gov.sendResponse(request.getId(), response);				
		} catch (Exception e) {
			e.printStackTrace();
			gov.updateState(request.getId(), STATE_ERROR, "Error processing " + request.getId() + ": " + (e.getMessage() != null ? e.getMessage() : e.toString()));
		}
	}
}
