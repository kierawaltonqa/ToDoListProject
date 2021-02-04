package com.qa.todolist.persistence.dto;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

public class ToDoEntriesDTO {

	private Long id;
	private String description;
	
	@Basic
	@Temporal(TemporalType.DATE)
	private java.util.Date dueDate;
	
	//private ToDoListsDTO myList;
	//causes a recursive error (because house shows cat list)

	public ToDoEntriesDTO() {
		super();
	}

	public ToDoEntriesDTO(Long id, String description, Date dueDate) {
		super();
		this.id = id;
		this.description = description;
		this.dueDate = dueDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public java.util.Date getDueDate() {
		return dueDate;
	}

	public void setDueDate(java.util.Date dueDate) {
		this.dueDate = dueDate;
	}

	
}
