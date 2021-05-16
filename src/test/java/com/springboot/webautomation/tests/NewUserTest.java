package com.springboot.webautomation.tests;

import com.github.javafaker.Faker;
import com.springboot.webautomation.pages.LoginPage;
import com.springboot.webautomation.pages.NewUserPage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Lazy;
import org.testng.Assert;
import org.testng.annotations.AfterClass;
import org.testng.annotations.Test;


public class NewUserTest extends SpringTestNGRunnerTests {

    @Autowired
    private Faker faker;

    @Lazy
    @Autowired
    private LoginPage page;

    @Lazy
    @Autowired
    private NewUserPage newUserPage;

    @Autowired
    private ApplicationContext context;

    @Test
    public void test() {
        this.page.navigateToUrl();
        Assert.assertEquals(true, this.page.isLoaded(), "Login Page is not loaded successfully");
        this.page.clickNewLoginButton();
        Assert.assertEquals(true, this.newUserPage.isLoaded(), "New User Page is not loaded successfully");
        this.newUserPage.enterNewUserInfo(
                faker.name().firstName(),
                faker.name().lastName(),
                faker.name().username(),
                faker.number().digits(6)
        );
        this.newUserPage.clickRegisterButton();
        Assert.assertEquals("Please verify reCaptcha to register!", this.newUserPage.getErrorLabel(), "Error message didn't match");
    }

    @AfterClass(alwaysRun = true)
    public void tearDown() {
        this.newUserPage.close();
    }

}
