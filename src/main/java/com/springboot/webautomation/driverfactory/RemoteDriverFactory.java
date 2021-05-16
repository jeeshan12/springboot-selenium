package com.springboot.webautomation.driverfactory;

import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.context.annotation.*;

import java.net.URL;

@Lazy
@Configuration
@Profile("remote")
public class RemoteDriverFactory {

    @Value("${remote.url}")
    private URL url;

    private static final Logger LOGGER = LoggerFactory.getLogger(RemoteDriverFactory.class);

    @Bean
    @Scope("browserbeanscope")
    @ConditionalOnProperty(name = "browser", havingValue = "firefox")
    public WebDriver getFirefoxDriver() {
        WebDriverManager.firefoxdriver().setup();
        LOGGER.info("Creating {} browser driver", "firefox");
        return new RemoteWebDriver(this.url, DesiredCapabilities.firefox());
    }


    @Bean
    @Scope("browserbeanscope")
    @ConditionalOnMissingBean
    public WebDriver getChromeDriver() {
        WebDriverManager.chromedriver().setup();
        LOGGER.info(url.toString());
        LOGGER.info("Creating {} browser driver", "chrome");
        return new RemoteWebDriver(this.url, DesiredCapabilities.chrome());
    }

}
