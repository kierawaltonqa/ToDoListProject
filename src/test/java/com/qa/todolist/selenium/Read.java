package com.qa.todolist.selenium;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class Read {

	public static String url = "http://localhost:8080/crud.html";

	@FindBy(id = "listId")
	private WebElement listIdReadInput;

	@FindBy(id = "readListById")
	private WebElement listIdReadButton;

	// @FindBy(id = "readLists")
	@FindBy(xpath = "//*[@id=\"readLists\"]")
	private WebElement listReadAllButton;

	@FindBy(xpath = "//*[@id=\"showLists\"]/h4[1]")
	private WebElement startOfList;

	@FindBy(id = "showLists")
	private WebElement showListsSection;

	public Read(WebDriver driver) {

	}

	public void readById(CharSequence id) {
		listIdReadInput.sendKeys(id);
		listIdReadButton.click();
	}

	public void readAll() {
		listReadAllButton.click();
	}

	public String readAllStatus() {
		return this.showListsSection.getText();
	}
}
