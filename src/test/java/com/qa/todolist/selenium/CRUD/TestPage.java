package com.qa.todolist.selenium.CRUD;

import static org.junit.Assert.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

public class TestPage {
	private static RemoteWebDriver driver;
	private String url = "http://localhost:8080/index.html";

	@BeforeAll
	public static void init() {
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterAll
	public static void after() {
		// closes the chrome driver
		driver.quit();
	}

	@Test
	public void readAll() {
		// given that I can access the index page
		driver.get(url);
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		// I want to navigate to the crud lists page and read all lists
		website.navCrudLists();
		website.read.readAll();
		// I should be able to view all lists on the database
		String status = website.read.readAllStatus();
		// String expected = "1: General Tasks";
		// assertions
		Assertions.assertThat(status).isNotNull();
		assertTrue(status.contains("1: General Tasks"));
	}

	@Test
	public void readOne() {
		// given that I can access the index page
		driver.get(url);
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		// I want to navigate to the crud lists page
		website.navCrudLists();
		// and I want to view a specific list by entering its ID
		website.read.readById("1");
		// I should be able to view the list with ID 1
		String status = website.read.readAllStatus();
		String expected = "1: General Tasks";
		// assertions
		assertTrue(status.contains(expected));
	}

	@Test
	public void createList() {
		// given that I can access the index page
		driver.get(url);
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		// I want to navigate to the crud lists page
		website.navCrudLists();
		// and I want to create a new list
		website.createList.createNewList("My New List");
		// I should be able to view this new list title on the screen
		String status = website.createList.status();
		String expected = "My New List";
		// assertions
		assertTrue(status.contains(expected));
		assertTrue(status.contains("new list: My New List"));
	}

	@Test
	public void createEntry() {
		// given that I can access the index page
		driver.get(url);
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		// I want to navigate to the crud lists page
		website.navCrudLists();
		// and I want to create a new list entry
		website.createEntry.createNewEntry("new list entry", "12-02-2021", "2");
		// I should be able to view this new list entry on the screen
		String status = website.createEntry.status();
		String expected = "new list entry";
		// assertions
		assertTrue(status.contains(expected));
	}

}
