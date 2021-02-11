package com.qa.todolist.selenium.CRUD;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class ClearPrintedData {

	public static String url = "http://localhost:8080/crud.html";
	
	@FindBy(id = "finishAddButton")
	private WebElement clearAddedDetails;
	
	@FindBy(id = "clearLists")
	private WebElement clearReadDetails;
	
	@FindBy(id = "finishUpdateButton")
	private WebElement clearUpdateDetails;
	
	public ClearPrintedData(WebDriver driver) {
		
	}
	
	public void clearAddDetailsFromScreen() {
		clearAddedDetails.click();
	}
	
	public void clearReadDetailsFromScreen() {
		clearReadDetails.click();
	}
	
	public void clearUpdateDetailsFromScreen() {
		clearUpdateDetails.click();
	}
	
	public void clearAllDataFromScreen() {
		clearAddedDetails.click();
		clearReadDetails.click();
		clearUpdateDetails.click();
	}
}
