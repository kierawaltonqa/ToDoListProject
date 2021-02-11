package com.qa.todolist.selenium.CRUD;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

public class TestPage {
	private static RemoteWebDriver driver;
	private String url = "http://localhost:8080/index.html";
	private static ExtentReports report;
	private static ExtentTest test;

	@BeforeAll
	public static void init() {
		// extent report
		report = new ExtentReports("target/reports/TestPageReport.html", true);
		// system property
		System.setProperty("webdriver.chrome.driver", "src/main/resources/drivers/chrome/chromedriver.exe");
		driver = new ChromeDriver();
	}

	@AfterEach
	public void endTest() {
		report.endTest(test);
		// clear any data that might have been printed to the webpage screen
		driver.get("http://localhost:8080/index.html");
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		// I want to navigate to the crud lists page clear all data from the screen
		website.navCrudLists();
		website.clear.clearAllDataFromScreen();
	}

	@AfterAll
	public static void after() {
		// closes the chrome driver
		driver.quit();
		// clean up extent reports
		report.flush();
		report.close();
	}

	@Test
	public void readAll() {
		// set up extent report
		test = report.startTest("Read All Lists Test");
		// given that I can access the index page
		driver.get(url);
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		// I want to navigate to the crud lists page and read all lists
		website.navCrudLists();
		website.read.readAll();
		// I should be able to view all lists on the database
		String status = website.read.readAllStatus();
		String expected = "1: General Tasks";
		if (status.contains(expected)) {
			test.log(LogStatus.PASS, expected);
		} else {
			test.log(LogStatus.FAIL, "Read All Test Failed");
		}
		// assertions
		Assertions.assertThat(status).isNotNull();
		assertTrue(status.contains("1: General Tasks"));
		assertTrue(status.contains("2: Shopping List"));
		assertTrue(status.contains("3: QA Project 2 tasks"));
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
		website.createEntry.createNewEntry("new list entry", "12-02-2021", "1");
		// I should be able to view this new list entry on the screen
		String status = website.createEntry.status();
		String expected = "new list entry";
		// assertions
		assertTrue(status.contains(expected));
		// NEED TO WORK OUT HOW TO TEST FOR DATE
		// assertTrue(status.contains("task: expected, complete
		// by:2021-02-12T00:00:00.000+00:00"));
		// ALSO COULD/SHOULD HAVE MORE/DIFFERENT ASSERTIONS HERE
	}

	@Test
	public void updateList() {
		// given that I can access the index page
		driver.get(url);
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		// I want to navigate to the crud lists page
		website.navCrudLists();
		// and I want to update the title of a pre-existing list
		website.updateList.updateListEntry("1", "General Tasks Updated");
		// then I should be able to view the updated list title on the screen
		String status = website.updateList.status();
		String expected = "General Tasks Updated";
		// assertions
		assertTrue(status.contains(expected));

	}

	@Test
	public void deleteList() {
		// given that I can access the index page
		driver.get(url);
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		// I want to navigate to the crud lists page
		website.navCrudLists();
		// and I want to delete a list by its ID
		website.delete.deleteList("2");
		// now when I read lists, this list with ID 2 is gone
		website.read.readById("2");
		String status = website.read.readAllStatus();
		// assertions
		assertTrue(status.isEmpty());
	}

	@Test
	public void deleteTask() {
		// given that I can access the index page
		driver.get(url);
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		// I want to navigate to the crud lists page
		website.navCrudLists();
		// and I want to delete a task by its ID
		website.delete.deleteTask("4");
		// now when I read lists, the task with this id is gone
		website.read.readAll();
		String status = website.read.readAllStatus();
		// assert that the task with ID 4 is not there
		assertFalse(status.contains("(id: 4)"));
	}

//	@Test
//	public void clearCreateHistory() {
//		// given that I can access the index page
//		driver.get(url);
//		HomePage website = PageFactory.initElements(driver, HomePage.class);
//		// I want to navigate to the crud lists page
//		website.navCrudLists();
//		// and create a new list
//		website.createList.createNewList("My New List");
//		// and then select the finished adding button
//		website.clear.clearAddDetailsFromScreen();
//		// then my screen should be cleared
//		String status = website.createList.status();
//		assertTrue(status.isEmpty());
//		
//	}
}
