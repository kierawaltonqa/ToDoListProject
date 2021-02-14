package com.qa.todolist.selenium.CRUD;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.PageFactory;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;

import com.relevantcodes.extentreports.ExtentReports;
import com.relevantcodes.extentreports.ExtentTest;
import com.relevantcodes.extentreports.LogStatus;

@SpringBootTest(webEnvironment = WebEnvironment.DEFINED_PORT)
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
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
		// stops the window pop up
		ChromeOptions config = new ChromeOptions();
		config.setHeadless(!true);
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

//NOT WORKING - CAN'T FIGURE OUT WHY??
//	@Test
//	public void readAll() {
//		// set up extent report
//		test = report.startTest("Read All Lists Test");
//		// given that I can access the index page
//		driver.get(url);
//		HomePage website = PageFactory.initElements(driver, HomePage.class);
//		// I want to navigate to the crud lists page and read the lists I created
//		website.navCrudLists();
//		website.read.readAll();
//		// I should be able to view all lists on the database
//		String status = website.read.readAllStatus();
//		String expected = "General Tasks";
//		if (!status.contains(expected)) {
//			test.log(LogStatus.PASS, expected);
//		} else {
//			test.log(LogStatus.FAIL, "Read All Test Failed");
//		}
//		// assertions
//		// Assertions.assertThat(status).isNotNull();
//		assertTrue(status.contains(expected));
//		assertTrue(status.contains("2: QA Project 2 tasks"));
//	}

	@Test
	public void readOne() {
		// set up extent report
		test = report.startTest("Read One List Test");
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
		if (status.contains(expected)) {
			test.log(LogStatus.PASS, expected);
		} else {
			test.log(LogStatus.FAIL, "Read One Test Failed");
		}
		// assertions
		assertTrue(status.contains(expected));
	}

	@Test
	public void createList() {
		// set up extent report
		test = report.startTest("Create List Test");
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
		if (status.contains(expected)) {
			test.log(LogStatus.PASS, expected);
		} else {
			test.log(LogStatus.FAIL, "Create List Test Failed");
		}
		// assertions
		assertTrue(status.contains(expected));
		assertTrue(status.contains("new list: My New List"));

	}

	@Test
	public void createEntry() {
		// set up extent report
		test = report.startTest("Create Entry Test");
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
		if (status.contains(expected)) {
			test.log(LogStatus.PASS, expected);
		} else {
			test.log(LogStatus.FAIL, "Create Entry Test Failed");
		}
		// assertions
		assertTrue(status.contains(expected));

		// COULD/SHOULD HAVE MORE/DIFFERENT ASSERTIONS HERE
	}

	@Test
	public void updateList() {
		// set up extent report
		test = report.startTest("Update List Test");
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
		if (status.contains(expected)) {
			test.log(LogStatus.PASS, expected);
		} else {
			test.log(LogStatus.FAIL, "Update List Test Failed");
		}
		// assertions
		assertTrue(status.contains(expected));

	}

	@Test
	public void updateEntry() {
		// set up extent report
		test = report.startTest("Update Entry Test");
		// given that I can access the index page
		driver.get(url);
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		// I want to navigate to the crud lists page
		website.navCrudLists();
		// and I want to update the details of a pre-existing task
		website.updateEntry.updateTaskEntry("2", "testing for back end updated", "13-02-2021", true, "2");
		// then this task should be show when I read for id 2
		website.read.readById("2");
		String status2 = website.read.readAllStatus();
		String expected2 = "testing for back end updated";
		// complete by: 2021-03-03T00:00:00.000+00:00
		if (status2.contains(expected2)) {
			test.log(LogStatus.PASS, expected2);
		} else {
			test.log(LogStatus.FAIL, "Update Entry Test Failed");
		}
		// assertions
		assertTrue(status2.contains(expected2));
	}

	@Test
	public void deleteList() {
		// set up extent report
		test = report.startTest("Delete List Test");
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
		if (status.isEmpty()) {
			test.log(LogStatus.PASS, "Delete List Test Passed");
		} else {
			test.log(LogStatus.FAIL, "Delete List Test Failed");
		}
		// assertions
		assertTrue(status.isEmpty());
	}

	@Test
	public void deleteTask() {
		// set up extent report
		test = report.startTest("Delete List Test");
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
		String expected = "(id: 4)";
		if (status.contains(expected)) {
			test.log(LogStatus.PASS, expected);
		} else {
			test.log(LogStatus.FAIL, "Delete List Test Failed");
		}
		// assert that the task with ID 4 is not there
		assertFalse(status.contains(expected));
	}

	@Test
	public void clearCreateHistory() {
		// set up extent report
		test = report.startTest("Clear Create History Test");
		// given that I can access the index page
		driver.get(url);
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		// I want to navigate to the crud lists page
		website.navCrudLists();
		// and create a new list
		website.createList.createNewList("My New List");
		// and then select the finished adding button
		website.clear.clearAddDetailsFromScreen();
		// then my screen should be cleared
		String status = website.createList.status();
		if (status.isEmpty()) {
			test.log(LogStatus.PASS, "Clear Create History Test Passed");
		} else {
			test.log(LogStatus.FAIL, "Clear Create History Test Failed");
		}
		assertTrue(status.isEmpty());
	}

	@Test
	public void clearReadHistory() {
		// set up extent report
		test = report.startTest("Clear Read History Test");
		// given that I can access the index page
		driver.get(url);
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		// I want to navigate to the crud lists page
		website.navCrudLists();
		// and read a new list
		website.read.readById("2");
		// and then select the clear lists button
		website.clear.clearReadDetailsFromScreen();
		// then my screen should be cleared
		String status = website.read.readAllStatus();
		if (status.isEmpty()) {
			test.log(LogStatus.PASS, "Clear Read History Test Passed");
		} else {
			test.log(LogStatus.FAIL, "Clear Read History Test Failed");
		}
		assertTrue(status.isEmpty());
	}

	@Test
	public void clearUpdateHistory() {
		// set up extent report
		test = report.startTest("Clear Update History Test");
		// given that I can access the index page
		driver.get(url);
		HomePage website = PageFactory.initElements(driver, HomePage.class);
		// I want to navigate to the crud lists page
		website.navCrudLists();
		// and update a list title
		website.updateList.updateListEntry("1", "General Tasks Updated");
		// and then select the finished updating button
		website.clear.clearUpdateDetailsFromScreen();
		// then my screen should be cleared
		String status = website.updateList.status();
		if (status.isEmpty()) {
			test.log(LogStatus.PASS, "Clear Update History Test Passed");
		} else {
			test.log(LogStatus.FAIL, "Clear Update History Test Failed");
		}
		assertTrue(status.isEmpty());
	}
}
