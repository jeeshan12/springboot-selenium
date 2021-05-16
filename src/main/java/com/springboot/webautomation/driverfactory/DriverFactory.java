package com.springboot.webautomation.driverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;

import org.openqa.selenium.WebDriver;

import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;

import java.io.IOException;

@Lazy
@Configuration
@Profile("!remote")
public class DriverFactory {

    private static final Logger LOGGER = LoggerFactory.getLogger(DriverFactory.class);


    @Bean
    @Scope("browserbeanscope")
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    public WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        LOGGER.info("Creating {} browser driver", "firefox");
        return new FirefoxDriver();
    }


    @Bean
    @Scope("browserbeanscope")
    @ConditionalOnProperty(name = "browser", havingValue = "chromeheadless")
    public WebDriver getChromeHeadlessDriver() {
        WebDriverManager.chromedriver().setup();
        ChromeOptions options = new ChromeOptions();
        options.addArguments("--headless");
        options.addArguments("--disable-dev-shm-usage");
        options.addArguments("--no-sandbox");
        LOGGER.info("Creating {} browser driver", "chromeheadless");

        return new ChromeDriver(options);

    }

    @Bean
    @Scope("browserbeanscope")
    @ConditionalOnProperty(name = "browser", havingValue = "firefoxheadless")
    public WebDriver getFirefoxHeadlessDriver() {
        WebDriverManager.firefoxdriver().setup();
        FirefoxOptions options = new FirefoxOptions();
        options.addArguments("--headless");
        LOGGER.info("Creating {} browser driver", "firefoxheadless");
        return new FirefoxDriver(options);
    }

    @Bean
    @Scope("browserbeanscope")
    @ConditionalOnMissingBean
    public WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        LOGGER.info("Creating {} browser driver", "chrome");


        return new ChromeDriver();
    }


}
