package com.qa.todolist.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateEntry {
	
	public static String url = "http://localhost:8080/crud.html";
	
	@FindBy(id = "taskTitle")
	private WebElement taskDescription;
	
	@FindBy(id = "taskDueDate")
	private WebElement taskDueDate;
	
	@FindBy(id = "IdOfList")
	private WebElement listIdOfTask;
	
	@FindBy(id = "addButton")
	private WebElement buttonToAddTask;
	
	@FindBy(id = "taskList")
	private WebElement showTaskListDiv;
	
	public CreateEntry(WebDriver driver) {
		
	}
	public void createNewEntry(CharSequence description, CharSequence dueDate, CharSequence listId) {
		taskDescription.sendKeys(description);
		taskDueDate.sendKeys(dueDate);
		listIdOfTask.sendKeys(listId);
		buttonToAddTask.click();
	}
	public String status() {
		return this.showTaskListDiv.getText();
	}

}
