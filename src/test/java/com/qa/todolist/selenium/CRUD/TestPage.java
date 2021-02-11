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
		//given that I can access the index page
		driver.get(url);
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		//I want to navigate to the crud lists page and read all lists
		website.navCrudLists();
		website.read.readAll();
		//I should be able to view all lists on the database
		String status = website.read.readAllStatus();
		//String expected = "1: General Tasks";
		//assertions
		Assertions.assertThat(status).isNotNull();
		assertTrue(status.contains("1: General Tasks"));
	}
	@Test
	public void readOne() {
		//given that I can access the index page
		driver.get(url);
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		//I want to navigate to the crud lists page
		website.navCrudLists();
		//and I want to view a specific list by entering its ID
		website.read.readById("1");
		//I should be able to view the list with ID 1
		String status = website.read.readAllStatus();
		String expected = "1: General Tasks";
		//assertions
		assertTrue(status.contains(expected));
	}
	
}
