@echo off
rem UGLY HACK!
SETLOCAL ENABLEEXTENSIONS
SETLOCAL ENABLEDELAYEDEXPANSION
set CP=
for %%i IN (web/WEB-INF/lib/*.jar) do set CP=!CP!;web/WEB-INF/lib/%%i
java -classpath %CP% org.apache.ws.java2wsdl.Java2WSDL %*


