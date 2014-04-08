#!/bin/sh
cp=
for N in `ls -1 web/WEB-INF/lib/*.jar`; do
	cp=$N:$cp		
done
java -classpath $cp org.apache.ws.java2wsdl.Java2WSDL $*
