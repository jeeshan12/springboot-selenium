package com.springboot.webautomation.tests;

import com.github.javafaker.Faker;
import com.springboot.webautomation.pages.LoginPage;
import org.openqa.selenium.WebDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;

public class LoginTest extends SpringTestNGRunnerTests {

    @Autowired
    private Faker faker;

    @Lazy
    @Autowired
    private LoginPage page;


    @Autowired
    private ApplicationContext context;

    @Test
    public void test() {
        this.page.navigateToUrl();
        Assert.assertEquals(true, this.page.isLoaded(), "Login Page is not loaded successfully");
        this.page.enterUserCredentials(faker.name().username(), faker.number().digits(5));
        this.page.clickNewLoginButton();

    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        this.page.close();
    }
}
