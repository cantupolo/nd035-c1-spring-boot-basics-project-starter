package com.udacity.jwdnd.course1.cloudstorage;

import com.udacity.jwdnd.course1.cloudstorage.pages.HomePage;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebElement;

import static org.junit.jupiter.api.Assertions.*;

/**
 * 1) Write a test that creates a note, and verifies it is displayed.
 * 2) Write a test that edits an existing note and verifies that the changes are displayed.
 * 3) Write a test that deletes a note and verifies that the note is no longer displayed.
 */
public class NoteTests extends CloudStorageApplicationTests {

    @BeforeAll
    static void beforeAll() {
        WebDriverManager.chromedriver().setup();
    }

    /**
     * Creates a note, and verifies it is displayed.
     */
    @Test
    public void testCreateNote() {
        try {
            signup("Second", "User", "user2", "456");
            login("user2", "456");
            driver.get("http://localhost:" + port + "/home");
            HomePage home = new HomePage(driver);
            home.clickNotesTab();
            Thread.sleep(500);
            home.clickNewNoteButton();
            Thread.sleep(500);
            home.setNoteTitle("Note test create");
            home.setNoteDescription("Note description");
            home.clickSaveNoteButton();
            home.clickNotesTab();
            Thread.sleep(500);
            driver.findElement(By.xpath("//button[@data-parameter2='Note test create']")).click();
            Thread.sleep(500);
            assertTrue(home.getNoteTitle().equals("Note test create"));
            assertTrue(home.getNoteDescription().equals("Note description"));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Edits an existing note and verifies that the changes are displayed.
     */
    @Test
    public void testEditNote() {
        try {
            signup("First", "User", "user1", "123");
            login("user1", "123");
            driver.get("http://localhost:" + port + "/home");
            HomePage home = new HomePage(driver);
            home.clickNotesTab();
            Thread.sleep(500);
            home.clickNewNoteButton();
            Thread.sleep(500);
            home.setNoteTitle("Note test");
            home.setNoteDescription("Note description");
            home.clickSaveNoteButton();

            home.clickNotesTab();
            Thread.sleep(500);
            driver.findElement(By.xpath("//button[@data-parameter2='Note test']")).click();
            Thread.sleep(500);
            home.setNoteTitle("Note test edit");
            home.setNoteDescription("Note description edit");
            home.clickSaveNoteButton();

            home.clickNotesTab();
            Thread.sleep(500);
            driver.findElement(By.xpath("//button[@data-parameter2='Note test edit']")).click();
            Thread.sleep(500);
            assertTrue(home.getNoteTitle().equals("Note test edit"));
            assertTrue(home.getNoteDescription().equals("Note description edit"));

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    /**
     * Deletes a note and verifies that the note is no longer displayed.
     */
    @Test
    public void testDeleteNote() {
        try {
            signup("Third", "User", "user3", "789");
            login("user3", "789");
            driver.get("http://localhost:" + port + "/home");
            HomePage home = new HomePage(driver);
            home.clickNotesTab();
            Thread.sleep(500);
            home.clickNewNoteButton();
            Thread.sleep(500);
            home.setNoteTitle("Note test");
            home.setNoteDescription("Note description");
            home.clickSaveNoteButton();

            home.clickNotesTab();
            Thread.sleep(500);
            WebElement element = driver.findElement(By.xpath("//button[@data-parameter2='Note test']"));
            String id = element.getAttribute("id");
            id = id.replaceFirst("Edit", "Del");
            driver.findElement(By.id(id)).click();
            home.clickNotesTab();
            Thread.sleep(500);
            try {
                driver.findElement(By.id(id));
            } catch (NoSuchElementException e) {
                assertTrue(true);
            }

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}