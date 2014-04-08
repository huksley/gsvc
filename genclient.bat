@echo off
md gen-src
wsimport -b simple.jaxb -s gen-src -Xnocompile -target 2.2 -extension -p ru.bank.service.client file:BankService.wsdl
wsimport -b simple.jaxb -s gen-src -Xnocompile -target 2.2 -extension -p ru.goverment.service.client file:GovermentService.wsdl



