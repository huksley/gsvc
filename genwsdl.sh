#!/bin/sh
./java2wsdl.sh -cp web/WEB-INF/classes -cn ru.goverment.service.GovermentService -o .
./java2wsdl.sh -cp web/WEB-INF/classes -cn ru.bank.service.BankService -o .


