package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import com.udacity.jwdnd.course1.cloudstorage.pages.LoginPage;
import com.udacity.jwdnd.course1.cloudstorage.pages.SignupPage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.*;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
abstract class CloudStorageApplicationTests {

	@LocalServerPort
	protected Integer port;

	protected WebDriver driver;

	@BeforeEach
	public void beforeEach() {
		this.driver = new ChromeDriver();
	}

	@AfterEach
	public void afterEach() {
		if (this.driver != null) {
			driver.quit();
		}
	}

	protected void signup(String firstName, String lastName,
						String userName, String password) throws InterruptedException {
		driver.get("http://localhost:" + port + "/signup");
		SignupPage signup = new SignupPage(driver);
		signup.setFirstName(firstName);
		signup.setLastName(lastName);
		signup.setUserName(userName);
		signup.setPassword(password);
		signup.submit();
	}

	protected void login(String userName, String password) throws InterruptedException {
		driver.get("http://localhost:" + port + "/login");
		LoginPage login = new LoginPage(driver);
		login.setUserName(userName);
		login.setPassword(password);
		login.submit();
	}

}
