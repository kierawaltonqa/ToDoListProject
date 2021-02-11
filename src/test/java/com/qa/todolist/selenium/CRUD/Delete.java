package com.qa.todolist.selenium.CRUD;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Delete {
	
	public static String url = "http://localhost:8080/crud.html";
	
	@FindBy(id = "deletelistId")
	private WebElement listIdDeleteInput;
	
	@FindBy(id = "deleteListButton")
	private WebElement deleteListByIdButton;
	
	@FindBy(id = "deletetaskId")
	private WebElement taskIdDeleteInput;
	
	@FindBy(id = "deleteTaskButton")
	private WebElement deleteTaskByIdButton;
	
	public Delete(WebDriver driver) {
		
	}
	
	public void deleteList(CharSequence id) {
		listIdDeleteInput.sendKeys(id);
		deleteListByIdButton.click();
	}
	
	public void deleteTask(CharSequence id) {
		taskIdDeleteInput.sendKeys(id);
		deleteTaskByIdButton.click();
	}
}
