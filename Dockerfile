FROM adoptopenjdk/maven-openjdk11
LABEL author="Mohd Jeeshan"
WORKDIR /app
ARG CHROME_VERSION="google-chrome-stable"
RUN apt-get update && apt-get install -y gnupg2
RUN  apt-get update \
  && apt-get install -y wget \
  && rm -rf /var/lib/apt/lists/*
RUN apt-get -y -qq update && \
	apt-get install -y -qq curl && \
	apt-get clean
# install jq to parse json within bash scripts
RUN curl -o /usr/local/bin/jq http://stedolan.github.io/jq/download/linux64/jq && \
  chmod +x /usr/local/bin/jq
RUN rm -rf /var/lib/apt/lists/* && apt update
RUN wget -q -O - https://dl-ssl.google.com/linux/linux_signing_key.pub | apt-key add - \
  && echo "deb http://dl.google.com/linux/chrome/deb/ stable main" >> /etc/apt/sources.list.d/google-chrome.list \
  && apt-get update -qqy \
  && apt-get -qqy install \
    ${CHROME_VERSION:-google-chrome-stable} --force-yes \
  && rm /etc/apt/sources.list.d/google-chrome.list \
  && rm -rf /var/lib/apt/lists/* /var/cache/apt/*
COPY target/libs libs
COPY target/springboot-selenium.jar springboot-selenium.jar
COPY target/springboot-selenium-tests.jar springboot-selenium-tests.jar
COPY target/springboot-selenium.jar.original springboot-selenium.jar.original
COPY ./testng.xml ./testng.xml
COPY .env .env
ADD hubcheck.sh hubcheck.sh
ENTRYPOINT sh hubcheck.sh