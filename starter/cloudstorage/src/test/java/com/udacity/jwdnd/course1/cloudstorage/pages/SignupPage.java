package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class SignupPage {

    @FindBy(id = "inputFirstName")
    private WebElement firstName;

    @FindBy(id = "inputLastName")
    private WebElement lastName;

    @FindBy(id = "inputUsername")
    private WebElement userName;

    @FindBy(id = "inputPassword")
    private WebElement password;

    @FindBy(id = "buttonSignUp")
    private WebElement submit;

    public SignupPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void submit() {
        submit.submit();
    }

    public void setFirstName(String value) {
        firstName.sendKeys(value);
    }

    public String getFirstName() {
        return firstName.getAttribute("value");
    }

    public void setLastName(String value) {
        lastName.sendKeys(value);
    }

    public String getLastName() {
        return lastName.getAttribute("value");
    }

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

}
