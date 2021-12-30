package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class LoginPage {

    @FindBy(id = "inputUsername")
    private WebElement userName;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "login-button")
    private WebElement submitButton;

    public void setUserName(String value) {
        userName.sendKeys(value);
    }

    public String getUserName() {
        return userName.getAttribute("value");
    }

    public void setPassword(String value) {
        password.sendKeys(value);
    }

    public String getPassword() {
        return password.getAttribute("value");
    }

    public void submit() {
        submitButton.submit();
    }

    public LoginPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }
}
