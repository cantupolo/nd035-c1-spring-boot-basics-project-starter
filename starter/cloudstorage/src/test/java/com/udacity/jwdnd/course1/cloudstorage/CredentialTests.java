package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 1) Write a test that creates a set of credentials, verifies that they are displayed, and verifies that
 * the displayed password is encrypted.
 * 2) Write a test that views an existing set of credentials, verifies that the viewable password is unencrypted,
 * edits the credentials, and verifies that the changes are displayed.
 * 3) Write a test that deletes an existing set of credentials and verifies that the credentials are no
 * longer displayed.
 */
public class CredentialTests extends CloudStorageApplicationTests {

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    /**
     * Creates a set of credentials, verifies that they are displayed, and verifies that
     * the displayed password is encrypted.
     */
    @Test
    public void testCreateCredentials() {
        try {
            signup("First", "User", "user1", "123");
            login("user1", "123");
            driver.get("http://localhost:" + port + "/home");
            HomePage home = new HomePage(driver);
            home.clickCredentialsTab();
            Thread.sleep(500);
            for (int i = 0; i < 4; i++) {
                home.clickNewCredentialButton();
                Thread.sleep(500);

                String credUrl = "https://www.google" + i + ".com";
                String credUsername = "guser" + i;
                String credPassword = "123g" + i;

                home.setCredentialUrl(credUrl);
                home.setCredentialUsername(credUsername);
                home.setCredentialPassword(credPassword);
                home.clickSaveCredentialButton();
                closeResultPage();
                String editId = driver.findElement(
                        By.xpath("//button[@data-parameter2='" + credUrl + "']")).getAttribute("id");
                String url = driver.findElement(
                        By.id(editId.replaceFirst("Edit", "Url"))).getText();
                String username = driver.findElement(
                        By.id(editId.replaceFirst("Edit", "Usr"))).getText();
                String password = driver.findElement(
                        By.id(editId.replaceFirst("Edit", "Pwd"))).getText();
                assertTrue(credUrl.equals(url));
                assertTrue(credUsername.equals(username));
                assertTrue(!credPassword.equals(password));
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Views an existing set of credentials, verifies that the viewable password is unencrypted,
     * edits the credentials, and verifies that the changes are displayed.
     */
    @Test
    public void testEditCredentials() {
        try {
            signup("Second", "User", "user2", "456");
            login("user2", "456");
            driver.get("http://localhost:" + port + "/home");
            HomePage home = new HomePage(driver);
            home.clickCredentialsTab();
            Thread.sleep(200);
            for (int i = 4; i < 8; i++) {
                home.clickNewCredentialButton();
                Thread.sleep(500);

                String credUrl = "https://www.google" + i + ".com";
                String credUsername = "guser" + i;
                String credPassword = "123g" + i;

                home.setCredentialUrl(credUrl);
                home.setCredentialUsername(credUsername);
                home.setCredentialPassword(credPassword);
                home.clickSaveCredentialButton();
                closeResultPage();

                driver.findElement(
                        By.xpath("//button[@data-parameter2='" + credUrl + "']")).click();
                assertTrue(credUrl.equals(home.getCredentialUrl()));
                assertTrue(credUsername.equals(home.getCredentialUsername()));
                assertTrue(credPassword.equals(home.getCredentialPassword()));

                home.clickSaveCredentialButton();
                closeResultPage();

                driver.findElement(
                        By.xpath("//button[@data-parameter2='" + credUrl + "']")).click();

                credUrl = "https://www.google" + (2 * i) + ".com";
                credUsername = "guser" + (2 * i);
                credPassword = "123g" + (2 * i);

                Thread.sleep(500);
                home.setCredentialUrl(credUrl);
                home.setCredentialUsername(credUsername);
                home.setCredentialPassword(credPassword);

                home.clickSaveCredentialButton();
                closeResultPage();
                driver.findElement(
                        By.xpath("//button[@data-parameter2='" + credUrl + "']")).click();

                assertTrue(credUrl.equals(home.getCredentialUrl()));
                assertTrue(credUsername.equals(home.getCredentialUsername()));
                assertTrue(credPassword.equals(home.getCredentialPassword()));

                home.clickSaveCredentialButton();
                closeResultPage();
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes an existing set of credentials and verifies that the credentials are no
     * longer displayed.
     */
    @Test
    public void testDeleteCredentials() {
        try {
            signup("Third", "User", "user3", "789");
            login("user3", "789");
            driver.get("http://localhost:" + port + "/home");
            HomePage home = new HomePage(driver);
            home.clickCredentialsTab();
            Thread.sleep(500);
            for (int i = 0; i < 4; i++) {
                home.clickNewCredentialButton();
                Thread.sleep(500);

                String credUrl = "https://www.google" + i + ".com";
                String credUsername = "guser" + i;
                String credPassword = "123g" + i;

                home.setCredentialUrl(credUrl);
                home.setCredentialUsername(credUsername);
                home.setCredentialPassword(credPassword);
                home.clickSaveCredentialButton();
                closeResultPage();
                String editId = driver.findElement(
                        By.xpath("//button[@data-parameter2='" + credUrl + "']")).getAttribute("id");
                String url = driver.findElement(
                        By.id(editId.replaceFirst("Edit", "Url"))).getText();
                String username = driver.findElement(
                        By.id(editId.replaceFirst("Edit", "Usr"))).getText();
                String password = driver.findElement(
                        By.id(editId.replaceFirst("Edit", "Pwd"))).getText();
                assertTrue(credUrl.equals(url));
                assertTrue(credUsername.equals(username));
                assertTrue(!credPassword.equals(password));
            }

            for (int i = 0; i < 4; i++) {
                String credUrl = "https://www.google" + i + ".com";
                String credUsername = "guser" + i;
                String credPassword = "123g" + i;

                String editId = driver.findElement(
                        By.xpath("//button[@data-parameter2='" + credUrl + "']")).getAttribute("id");
                driver.findElement(
                        By.id(editId.replaceFirst("Edit", "Del"))).click();
                closeResultPage();
                try {
                    driver.findElement(
                            By.xpath("//button[@data-parameter2='" + credUrl + "']"));
                    assertTrue(false);
                } catch (NoSuchElementException e) {
                    assertTrue(true);
                }
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

}