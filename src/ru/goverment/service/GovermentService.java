package ru.goverment.service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Logger;

import javax.xml.ws.WebServiceException;

/**
 * Goverment interaction service.
 * <p>
 * Declares interface for query requests from goverment for
 * information about clients and block (arrest) of accounts.
 * <p>
 * This is a sample service, without authentication or checking.
 * 
 * @author ruslan
 */
public class GovermentService {
	
	private Logger log = Logger.getLogger("GovermentService");
	private List<Request> requests = new ArrayList<Request>();
	private Map<String,Response> responses = new HashMap<String,Response>();
	
	{
		try {
    		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
    		Request r = new Request();
    		r.naturalCustomer = new NaturalCustomer();
    		r.naturalCustomer.lastName = "Testov";
    		r.naturalCustomer.firstName = "Test";
    		r.naturalCustomer.middleName = "Testovich";
    		r.naturalCustomer.identityDocument = new IdentityDocument();
    		r.naturalCustomer.identityDocument.birthDate = df.parse("1979-04-13");
    		r.naturalCustomer.identityDocument.issueDate = df.parse("2003-05-28");
    		r.naturalCustomer.identityDocument.serial = "4505";
    		r.naturalCustomer.identityDocument.number = "101911";
    		r.type = RequestType.INQUIRY;
    		addRequest(r);
    		
    		Request lr = new Request();
    		lr.legalCustomer = new LegalCustomer();
    		lr.legalCustomer.fullName = "Russian Bank Systems Joint Stock Company";
    		lr.legalCustomer.kpp = "312892828";
    		lr.legalCustomer.taxCode = "312818191919";
    		lr.legalCustomer.shortName = "BANKSYSTEMS JSC";
    		lr.type = RequestType.ARREST;
    		addRequest(lr);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Registers new request, marks it as  {@link RequestState#NEW} and available in {@link #findRequest()}
	 * 
	 * @param request
	 * @return Id of request - {@link Request#id}
	 */
	public String addRequest(Request request) {
		request.state = RequestState.NEW;
		request.id = "fssp" + String.valueOf(System.currentTimeMillis());
		log.info("Added request " + request.id);
		requests.add(request);
		return request.id;
	}
	
	/**
	 * Returns all available for processing requests in  {@link RequestState#NEW} state.
	 * 
	 * @return List of requests or null (or empty array) if no requests found.
	 */
	public Request[] findRequest() {
		List<Request> l = new ArrayList<Request>();
		log.info("Total requests: " + requests.size());
		for (Request r: requests) {
			if (r.state == RequestState.NEW) {
				log.info("Returning request " + r.id);
				l.add(r);
			}
		}		
		log.info("Returning requests: " + l.size());
		return l.toArray(new Request[l.size()]);
	}
	
	/**
	 * Requests current state of request, for example  {@link RequestState#NEW}
	 * 
	 * @link {@link RequestState}
	 * @param requestId Id of request - {@link Request#id}
	 * @return
	 */
	public int getState(String requestId) {
		for (Request r: requests) {
			if (r.id.equals(requestId)) {
				return r.state;
			}
		}
		throw new WebServiceException("Request not found: " + requestId);
	}
	
	/**
	 * Updates state of requests.
	 * Called after accepting request into processing (call with {@link RequestState#ACCEPTED}) 
	 * or if error occurs (call with {@link RequestState#ERROR})
	 * 
	 * @param requestId Id of request - {@link Request#id}
	 * @param requestState One of {@link RequestState} constants.
	 * @param message Message, if requestState is {@link RequestState#ACCEPTED} can be null (or empty)
	 * @return Old state or 0 if not found.
	 */
	public int updateState(String requestId, int requestState, String message) {
		for (Request rr: requests) {
			if (rr.id.equals(requestId)) {
				log.info("Updating state of " + rr.id + " to " + requestState);
				int oldState = rr.state;
				rr.state = requestState;
				log.info("New state: " + getState(requestId));
				return oldState;
			}
		}
		throw new WebServiceException("Request not found: " + requestId);
	}
	

	
	/**
	 * Sends response for request, sets state to {@link RequestState#FULLFILLED}
	 * 
	 * @param requestId Id of request - {@link Request#id}
	 * @param response
	 */
	public void sendResponse(String requestId, Response response) {
		log.info("Response received for " + requestId + ": " + response);
		updateState(requestId, RequestState.FULLFILLED, "");
		responses.put(requestId, response);
	}
}
