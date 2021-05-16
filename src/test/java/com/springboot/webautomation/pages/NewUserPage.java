package com.springboot.webautomation.pages;

import com.google.common.util.concurrent.Uninterruptibles;
import com.springboot.webautomation.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

import java.util.concurrent.TimeUnit;

@Lazy
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class NewUserPage extends BasePage {


    @FindBy(id = "firstname")
    private WebElement txtFirstName;

    @FindBy(id = "gotologin")
    private WebElement btnGoToLogin;

    @FindBy(id = "lastname")
    private WebElement txtLastName;

    @FindBy(id = "userName")
    private WebElement txtUserName;

    @FindBy(id = "password")
    private WebElement txtPassword;

    @FindBy(id = "register")
    private WebElement btnRegister;


    @FindBy(css = "span#recaptcha-anchor div.recaptcha-checkbox-border")
    private WebElement cbxNotRobot;


    @FindBy(id = "name")
    private WebElement lblError;


    public void enterNewUserInfo(final String firstName, final String lastName, final String userName, final String password) {
        seleniumUtils.enterDataToTextBox(txtFirstName, firstName);
        seleniumUtils.enterDataToTextBox(txtLastName, lastName);
        seleniumUtils.enterDataToTextBox(txtUserName, userName);
        seleniumUtils.enterDataToTextBox(txtPassword, password);
    }

    public void clickRegisterButton() {
        this.jse.executeScript("arguments[0].click();", this.btnRegister);
    }

    public void clickNotRobotCheckBox() {
        this.wait.until(d -> this.cbxNotRobot.isDisplayed() && this.cbxNotRobot.isEnabled());
        seleniumUtils.clickElement(this.cbxNotRobot);
        Uninterruptibles.sleepUninterruptibly(3, TimeUnit.SECONDS);
    }


    public String getErrorLabel() {
        return seleniumUtils.getROText(this.lblError);
    }

    public void close() {
        this.driver.quit();
    }

    @Override
    public boolean isLoaded() {
        return this.wait.until(d -> this.btnGoToLogin.isDisplayed() && this.btnGoToLogin.isEnabled());
    }
}
