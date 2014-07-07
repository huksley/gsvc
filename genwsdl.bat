@echo off
call java2wsdl -cp web/WEB-INF/classes -cn ru.goverment.service.GovermentService -o .
call java2wsdl -cp web/WEB-INF/classes -cn ru.bank.service.BankService -o .



