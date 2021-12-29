package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.assertTrue;

/**
 * 1) Write a test that verifies that an unauthorized user can only access the login and signup pages.
 * 2) Write a test that signs up a new user, logs in, verifies that the home page is accessible,
 * logs out, and verifies that the home page is no longer accessible.
 */
public class UserTests extends CloudStorageApplicationTests {

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    @Test
    public void getLoginPage() {
        driver.get("http://localhost:" + this.port + "/login");
        Assertions.assertEquals("Login", driver.getTitle());
    }

    @Test
    public void testUnauthorizedUserHomePage() {
        driver.get("http://localhost:" + port + "/home");
        HomePage home = new HomePage(driver);
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(!home.isAccessible());
    }

    @Test
    public void testUnauthorizedUserHomePageAsLoginPage() {
        driver.get("http://localhost:" + port + "/home");
        testLoginPage();
    }

    @Test
    public void testUnauthorizedUserLoginPage() {
        driver.get("http://localhost:" + port + "/login");
        testLoginPage();
    }

    private void testLoginPage() {
        LoginPage login = new LoginPage(driver);
        login.setUserName("teste");
        login.setPassword("123");
        try {
            Thread.sleep(1);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        assertTrue(login.getUserName().equals("teste"));
        assertTrue(login.getPassword().equals("123"));
    }

    @Test
    public void testUnauthorizedUserSignupPage() {
        driver.get("http://localhost:" + port + "/signup");
        SignupPage signup = new SignupPage(driver);
        try {
            Thread.sleep(1);
            signup.setFirstName("Tony");
            signup.setLastName("Stark");
            Thread.sleep(5);
            assertTrue(signup.getFirstName().equals("Tony"));
            assertTrue(signup.getLastName().equals("Stark"));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testLoggedUserHomePageAccessible() {
        try {
            signup("First", "User", "user1", "123");
            login("user1", "123");
            driver.get("http://localhost:" + port + "/home");
            HomePage home = new HomePage(driver);
            assertTrue(home.isAccessible());

            driver.get("http://localhost:" + port + "/logout");
            assertTrue(!home.isAccessible());

        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }

}
