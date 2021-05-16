package com.springboot.webautomation.services;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

@Service
@Lazy
public class SeleniumUtils {


    public void enterDataToTextBox(final WebElement element, final String valueToEnter) {
        element.clear();
        element.sendKeys(valueToEnter);
    }

    public void clickElement(final WebElement element) {
        element.click();
    }

    public String getROText(WebElement element) {
        return element.getText();
    }
}
