package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class ResultPage {

    @FindBy(id = "errorLinkToHome")
    private WebElement errorLinkToHome;

    @FindBy(id = "successLinkToHome")
    private WebElement successLinkToHome;

    public ResultPage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public void clickErrorLinkToHome() {
        errorLinkToHome.click();
    }

    public void clickSuccessLinkToHome() {
        successLinkToHome.click();
    }

    public boolean isErrorLinkToHomeAccessible() {
        return isLinkToHomeAccessible(errorLinkToHome);
    }

    public boolean isSuccessLinkToHomeAccessible() {
        return isLinkToHomeAccessible(successLinkToHome);
    }

    private boolean isLinkToHomeAccessible(WebElement link) {
        try {
            String text = link.getText();
            return text.equals("here");

        } catch (NoSuchElementException e) {
            return false;
        }
    }

}
