package com.udacity.jwdnd.course1.cloudstorage.pages;

import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

    @FindBy(id = "nav-files-tab")
    private WebElement filesTab;

    @FindBy(id = "nav-notes-tab")
    private WebElement notesTab;

    @FindBy(id = "nav-credentials-tab")
    private WebElement credentialsTab;

    @FindBy(id = "addNewNote")
    private WebElement newNoteButton;

    @FindBy(id = "noteSubmit")
    private WebElement saveNoteButton;

    @FindBy(id = "addNewCredential")
    private WebElement newCredentialButton;

    @FindBy(id = "credentialSubmit")
    private WebElement saveCredentialButton;

    @FindBy(id = "closeCredential")
    private WebElement closeCredentialButton;

    @FindBy(id = "note-title")
    private WebElement noteTitle;

    @FindBy(id = "note-description")
    private WebElement noteDescription;

    @FindBy(id = "credential-url")
    private WebElement credentialUrl;

    @FindBy(id = "credential-username")
    private WebElement credentialUsername;

    @FindBy(id = "credential-password")
    private WebElement credentialPassword;

    public HomePage(WebDriver driver) {
        PageFactory.initElements(driver, this);
    }

    public boolean isAccessible() {
        try {
            String text = getFilesTabText();
            return text.equals("Files");

        } catch (NoSuchElementException e) {
            return false;
        }
    }

    public void clickNewNoteButton() {
        newNoteButton.click();
    }

    public void clickSaveNoteButton() {
        saveNoteButton.submit();
    }

    public void clickNotesTab() {
        notesTab.click();
    }

    public void clickNewCredentialButton() {
        newCredentialButton.click();
    }

    public void clickSaveCredentialButton() {
        saveCredentialButton.submit();
    }

    public void clickCloseCredentialButton() {
        closeCredentialButton.submit();
    }

    public void clickCredentialsTab() {
        credentialsTab.click();
    }

    public String getFilesTabText() {
        return filesTab.getText();
    }

    public String getNoteTitle() {
        return noteTitle.getAttribute("value");
    }

    public void setNoteTitle(String value) {
        noteTitle.clear();
        noteTitle.sendKeys(value);
    }

    public String getNoteDescription() {
        return noteDescription.getAttribute("value");
    }

    public void setNoteDescription(String value) {
        noteDescription.clear();
        noteDescription.sendKeys(value);
    }

    public String getCredentialUrl() {
        return credentialUrl.getAttribute("value");
    }

    public void setCredentialUrl(String value) {
        credentialUrl.clear();
        credentialUrl.sendKeys(value);
    }

    public String getCredentialUsername() {
        return credentialUsername.getAttribute("value");
    }

    public void setCredentialUsername(String value) {
        credentialUsername.clear();
        credentialUsername.sendKeys(value);
    }

    public String getCredentialPassword() {
        return credentialPassword.getAttribute("value");
    }

    public void setCredentialPassword(String value) {
        credentialPassword.clear();
        credentialPassword.sendKeys(value);
    }

}
