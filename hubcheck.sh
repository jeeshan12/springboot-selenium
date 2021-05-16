#!/bin/bash

echo "Checking if hub is ready to register node and start execution"
x=1
while [ "$( curl -X GET http://selenium-hub:4444/wd/hub/status | jq -r .value.ready )" != "true" ] && [ $x -lt 30 ];
do
	sleep 1
	x=`expr $x + 1`
	echo "Trying for $x  time"
done

java -cp libs/*:springboot-selenium.jar:springboot-selenium-tests.jar:springboot-selenium.jar.original \
      -Dspring.profiles.active=$PROFILE \
       org.testng.TestNG $TESTNGMODULE