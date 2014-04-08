#!/bin/sh
#wget -O GovermentService.wsdl http://localhost:8080/gsvc/services/GovermentService?wsdl
#wget -O BankService.wsdl http://localhost:8080/gsvc/services/BankService?wsdl
./java2wsdl.sh -cp web/WEB-INF/classes -cn ru.goverment.service.GovermentService -o .
./java2wsdl.sh -cp web/WEB-INF/classes -cn ru.bank.service.BankService -o .


