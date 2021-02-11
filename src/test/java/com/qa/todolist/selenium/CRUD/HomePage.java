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
	public ReadToDos read;
	public UpdateToDos update;
	public Delete delete;
	public ClearPrintedData clear;
	
	public HomePage(WebDriver driver) {
		this.createList = PageFactory.initElements(driver, CreateList.class);
		this.createEntry = PageFactory.initElements(driver, CreateEntry.class);
		this.read = PageFactory.initElements(driver, ReadToDos.class);
		this.update = PageFactory.initElements(driver, UpdateToDos.class);
		this.delete = PageFactory.initElements(driver, Delete.class);
		this.clear = PageFactory.initElements(driver, ClearPrintedData.class);
	}
	
	public void navCrudLists() {
		crudLists.click();
	}
}
