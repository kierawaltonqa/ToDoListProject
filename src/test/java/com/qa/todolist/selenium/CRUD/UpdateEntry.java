package com.qa.todolist.selenium.CRUD;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class UpdateEntry {

	public static String url = "http://localhost:8080/crud.html";

	@FindBy(id = "taskIDUpdate")
	private WebElement updateTaskIdInput;

	@FindBy(id = "taskTitleUpdate")
	private WebElement updateTaskTitleInput;

	@FindBy(id = "taskDueDateUpdate")
	private WebElement updateTaskDueDateInput;

	@FindBy(id = "completed")
	private WebElement updateTaskCompletedInput;

	@FindBy(id = "IdOfListUpdate")
	private WebElement updateListIdOfTask;

	@FindBy(id = "updateButton")
	private WebElement updateTaskButton;

	@FindBy(id = "updatedTaskList")
	private WebElement showUpdatedTask;

	public UpdateEntry(WebDriver driver) {

	}

	public void updateTaskEntry(CharSequence id, CharSequence description, CharSequence dueDate, boolean completed,
			CharSequence listId) {
		updateTaskIdInput.sendKeys(id);
		updateTaskTitleInput.sendKeys(description);
		updateTaskDueDateInput.sendKeys(dueDate);
		if (completed == true) {
			updateTaskCompletedInput.isSelected();
		}
		updateListIdOfTask.sendKeys(listId);
		updateTaskButton.click();
	}

	public String status() {
		return this.showUpdatedTask.getText();
	}
}
