package com.qa.todolist.selenium.CRUD;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdateList {

	public static String url = "http://localhost:8080/crud.html";

	@FindBy(id = "updatelistId")
	private WebElement updateListIdInput;

	@FindBy(id = "updatelistTitle")
	private WebElement updateListTitleInput;

	@FindBy(id = "updateListButton")
	private WebElement updateListButton;

	@FindBy(id = "showUpdate")
	private WebElement showUpdatesDiv;

	public UpdateList(WebDriver driver) {

	}

	public void updateListEntry(CharSequence id, CharSequence title) {
		updateListIdInput.sendKeys(id);
		updateListTitleInput.sendKeys(title);
		updateListButton.click();
	}

	public String status() {
		return this.showUpdatesDiv.getText();
	}
}
