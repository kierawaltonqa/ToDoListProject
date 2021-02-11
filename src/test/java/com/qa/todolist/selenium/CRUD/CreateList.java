package com.qa.todolist.selenium.CRUD;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class CreateList {

	public static String url = "http://localhost:8080/crud.html";

	@FindBy(id = "listTitle")
	private WebElement listTitleInput;

	@FindBy(id = "createButton")
	private WebElement createListButton;

	@FindBy(id = "createList")
	private WebElement showListDiv;

	public CreateList(WebDriver driver) {

	}

	public void createNewList(CharSequence title) {
		listTitleInput.sendKeys(title);
		createListButton.click();
	}

	public String status() {
		return this.showListDiv.getText();
	}
}
