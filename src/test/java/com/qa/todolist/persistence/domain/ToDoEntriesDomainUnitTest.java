package com.qa.todolist.persistence.domain;


import java.sql.Date;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToDoEntriesDomainUnitTest {
	
	private java.util.Date dueDate = Date.valueOf("2021-02-06");
	private java.util.Date dueDate2 = Date.valueOf("2021-02-06");
	
	private ToDoEntriesDomain entry = new ToDoEntriesDomain(1L,"task1", dueDate, null);
	private ToDoEntriesDomain other = new ToDoEntriesDomain(2L,"task2", dueDate2, null);
	

	
	@Test
	public void equalsWithNull() {
		
	}
	
	@Test
	public void getId() {
		
	}
	@Test
	public void setId() {
		
	}
	@Test
	public void getDescription() {
		
	}
	@Test
	public void setDescription() {
		
	}
	public void getDueDate() {
		
	}
	@Test
	public void setDueDate() {
		
	}
}
