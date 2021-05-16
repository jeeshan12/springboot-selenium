[![Linkedin](https://img.shields.io/badge/LinkedIn-0077B5?style=for-the-badge&logo=linkedin&logoColor=white)](https://www.linkedin.com/in/mohdjeeshan)

# Spring Boot Selenium Automation Project

## Table of Contents

- [Authors](#authors)
- [Pre-requisites](#pre-requisites)
- [Libraries](#libraries)
- [Running Tests](#running-tests)
- [Running Through IntelliJ](#running-through-intellij)

## Authors
* [Mohd Jeeshan](https://github.com/jeeshan12)

## Pre-requisites
Please install below tools to use this framework

* [Java](https://www.java.com/en/download/)
* [Maven](https://maven.apache.org/download.cgi)
* [Firefox](https://www.mozilla.org/)
* [Chrome](https://www.google.com/intl/en_in/chrome/)
* [Docker](https://www.docker.com/)

## Libraries
Below libraries are used to automate the web browsers
* [TestNG](https://testng.org/doc/) - a testing framework used to write selenium tests.
* [Selenium WebDriver](https://www.selenium.dev/documentation/en/webdriver/) - automated a browser locally, as a real user would experience or on remote machines using selenium grid.
* [WebDriverManager](https://github.com/bonigarcia/webdrivermanager) - a library to download drivers for chrome or firefox.
* [Faker](https://github.com/DiUS/java-faker) - a library to generate random data

## Running Tests

### Spring Profile
You can run either `default` , `qa` or `remote` profiles. The `default` runs on the properties defined in `application.properties`, `qa` will run on the properties defined in `application-qa.properties`, `remote` is used to run tests on `Selenium Grid`.

For the `default` profile you just need to execute mvn `test` task.
```
mvn clean test -Dfile=testng.xml
```

To execute the `qa` profile the environment variable `-Dspring.profiles.active` should be set. The following command line should be used:
```
mvn clean test -Dspring.profiles.active=qa -Dfile=testng.xml
```

### Browsers Execution
If you don't pass any browser name property in application.properties or via commandline , by default tests will be launched in `Chrome`. If you want to execute the tests on any other browser of you choice say `Firefox`, all you need to do is change the browser property in `application.properties` or `application-*.properties`. Same thing can be achieved via command line(I prefer to do in this way) by running the below command
```
mvn clean test -Dspring.prilfes.active=qa -Dbrowser=chromeheadless -Dfile=testng.mxl
```
Choices of browser available
---
* chrome
* chromeheadless
* firefox
* firefoxheadless


### Parallel
Framework is designed to handle the parallel execution on browsers. Parallel execution can be enabled by introducing `parallel="tests"` and `thread-count=2` for suite tag of `testng.xml`. `thread-count` property is configurable. You can set the value according to your need.
Know more about TestNG parallel execution [here.](https://testng.org/doc/documentation-main.html#parallel-tests)

## Running Through IDE
You can also execute the test scenarios through IntelliJ, VS Code or Eclpise.

### Locally
To run the test locally on your machine
* Right click on the test class file from  tests folder under test package and click on `Run as TestNG class in eclipse` or  `Run in IntelliJ`.
* Right-click on `testng.xml` file and click run.

### Remote (Docker)

To run testst remotely(selenium grid)
* Go to root directory of the framework (make sure `Dcokerfile is in root directory`) and build the image by running command
```
docker build -t dragon12/springboot-selenium .
```
* Now Start the `selenium grid` in background by running command
```
 docker-compose up -d selenium-hub chrome firefox
```
* Now run the below command to execute our tests on docker container
```
echo "PROFILE=remote" > .env && docker-compose up rune2e 
```
In the `application.yml` file make sure the property `context` is `remote`.

#### We can scale the chrome and firefox containers by running below commands
```
docker-compose up -d --scale firefox=2 --scale chrome=2
```

### Tear Down Grid

Run the below command to bring the `selenium hub` down
```
docker-compose down
```
