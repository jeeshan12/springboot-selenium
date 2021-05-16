package com.springboot.webautomation.pages;

import com.springboot.webautomation.base.BasePage;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Lazy;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Lazy
@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class LoginPage extends BasePage {

    @FindBy(id = "newUser")
    private WebElement btnNewUser;

    @Value("${base.url}")
    private String url;

    @FindBy(id = "userName")
    private WebElement txtUserName;

    @FindBy(id = "password")
    private WebElement txtPassword;


    @FindBy(id = "name")
    private WebElement lblError;

    public void navigateToUrl() {

        this.driver.navigate().to(url);
    }

    public void clickNewLoginButton() {

        this.seleniumUtils.clickElement(btnNewUser);
    }


    public void enterUserCredentials(final String userName, final String password) {
        this.seleniumUtils.enterDataToTextBox(this.txtUserName, userName);
        this.seleniumUtils.enterDataToTextBox(this.txtPassword, password);

    }

    public String getLoginErrorMessage() {
        this.wait.until(d -> this.lblError.isDisplayed());
        return seleniumUtils.getROText(this.lblError);
    }

    public void close() {
        this.driver.close();
    }

    @Override
    public boolean isLoaded() {
        return this.wait.until(d -> this.btnNewUser.isDisplayed() && this.btnNewUser.isEnabled());
    }

}
