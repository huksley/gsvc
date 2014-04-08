package ru.bank.integration;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Handler;
import java.util.logging.LogRecord;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.ws.BindingProvider;

import ru.bank.service.client.BankService;
import ru.bank.service.client.BankServicePortType;
import ru.goverment.service.client.GovermentService;
import ru.goverment.service.client.GovermentServicePortType;
import ru.goverment.service.client.Request;

/**
 * Integration servlet.
 * Processes all requests on invocation.
 * 
 * @author ruslan
 */
public class IntegrationServlet extends HttpServlet {
	private static final String BANK_SERVICE = "http://localhost:8080/gsvc/services/BankService";
	private static final String GOVERMENT_SERVICE = "http://localhost:8080/gsvc/services/GovermentService";
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		response.setCharacterEncoding("UTF-8");
		final PrintWriter out = response.getWriter();
		out.println("<h1>Integration</h1>");
		
		Logger log = Logger.getLogger(getClass().getSimpleName() + System.currentTimeMillis());
		log.addHandler(new Handler() {			
			@Override
			public void publish(LogRecord record) {
				out.println("<div>" + record.getLevel().getName() + " " + record.getMessage() + "</div>");
				out.flush();
			}
			
			@Override
			public void flush() {
				out.flush();
			}
			
			@Override
			public void close() throws SecurityException {
			}
		});
		
		GovermentService govermentService = new GovermentService();
		GovermentServicePortType gov = govermentService.getGovermentServiceHttpSoap11Endpoint();
		BindingProvider govermentServiceBindingProvider = (BindingProvider) gov;
		govermentServiceBindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, GOVERMENT_SERVICE);
		
		BankService bankService = new BankService();
		BankServicePortType bank = bankService.getBankServiceHttpSoap11Endpoint();
		BindingProvider bankBindingProvider = (BindingProvider) bank;
		bankBindingProvider.getRequestContext().put(BindingProvider.ENDPOINT_ADDRESS_PROPERTY, BANK_SERVICE);
		
		List<Request> l = gov.findRequest();
		for (int i = 0; i < l.size(); i++) {
			log.info("Request in queue: " + l.get(i).getId());
		}
		if (l.size() == 0) {
			log.info("No requests in queue");
		}
		String bic = bank.getBIC();
		log.info("Bank BIC: " + bic);
		
		new IntegrationProcess().process(gov, bank, log);
	}
	
	

	@Override
	protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
		doGet(req, resp);
	}
}
