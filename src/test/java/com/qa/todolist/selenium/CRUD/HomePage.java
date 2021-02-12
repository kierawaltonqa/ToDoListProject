package com.qa.todolist.selenium.CRUD;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class HomePage {

	public static String url = "http://localhost:8080/index.html";
	
	@FindBy(xpath = "//*[@id=\"navbarResponse\"]/ul/li[2]/a")
	private WebElement crudLists;
	
	public CreateList createList;
	public CreateEntry createEntry;
	public Read read;
	public UpdateEntry updateEntry;
	public UpdateList updateList;
	public Delete delete;
	public ClearPrintedData clear;
	
	public HomePage(WebDriver driver) {
		this.createList = PageFactory.initElements(driver, CreateList.class);
		this.createEntry = PageFactory.initElements(driver, CreateEntry.class);
		this.read = PageFactory.initElements(driver, Read.class);
		this.updateEntry = PageFactory.initElements(driver, UpdateEntry.class);
		this.updateList = PageFactory.initElements(driver, UpdateList.class);
		this.delete = PageFactory.initElements(driver, Delete.class);
		this.clear = PageFactory.initElements(driver, ClearPrintedData.class);
	}
	
	public void navCrudLists() {
		crudLists.click();
	}
}
