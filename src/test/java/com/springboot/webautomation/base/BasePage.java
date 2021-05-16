package com.springboot.webautomation.base;

import com.springboot.webautomation.services.SeleniumUtils;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

public abstract class BasePage {

    @Autowired
    public SeleniumUtils seleniumUtils;

    @Lazy
    @Autowired
    protected WebDriver driver;

    @Lazy
    @Autowired
    protected WebDriverWait wait;


    @Lazy
    @Autowired
    protected JavascriptExecutor jse;

    @PostConstruct
    public void initPages() {
        PageFactory.initElements(this.driver, this);
        // this.driver.manage().window().maximize();
    }

    public abstract boolean isLoaded();
}
