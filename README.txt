Example of integration implemented in Java which better to be implemented 
in BPEL or BPMN

Declares two services GovermentService <=> BankService
and integration servlet which calls both of them.

Integration logic in servlet must be moved to BPEL/BPMN or similar integration solution
for better change control, business transparency, monitoring and robustness.

== BUSINESS DOMAIN == 

Goverment service contains queue of requests to be processed by banks.
Bank read requests and invoke bank service to process them.

[GovermentService] -> [Request]* -> [BankService] -> [Response] -> [GovermentService]

For documentation build and publish this project in Tomcat and open javadoc
http://localhost:8080/gsvc/javadoc

== REQUIREMENTS ==

	- JDK 7
	- Apache Tomcat 7.0.x
	- Apache Ant 1.8.x

== BUILDING ==

Issue 
> ant distrib
to generate WSDL, web service clients, compile everything
and build gsvc.war

== USAGE ==

1. Copy war or web directory to webapps in tomcat
2. Start tomcat (bin/catalina run)
3. Point browser to http://localhost:8080/gsvc/
4. Read http://localhost:8080/gsvc/javadoc for API and webservices documentation
5. Click on http://localhost:8080/gsvc/process to invoke integration and process all requests

