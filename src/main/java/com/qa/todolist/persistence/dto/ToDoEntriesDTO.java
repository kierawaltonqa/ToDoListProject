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

	private boolean completed;

	// private ToDoListsDTO myList;
	// causes a recursive error

	public ToDoEntriesDTO() {
		super();
	}

	public ToDoEntriesDTO(Long id, String description, Date dueDate, boolean completed) {
		super();
		this.id = id;
		this.description = description;
		this.dueDate = dueDate;
		this.completed = completed;
	}

	// for testing purposes
	public ToDoEntriesDTO(Long id, String description, boolean completed) {
		super();
		this.id = id;
		this.description = description;
		this.completed = completed;
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

	public boolean isCompleted() {
		return completed;
	}

	public void setCompleted(boolean completed) {
		this.completed = completed;
	}

}
