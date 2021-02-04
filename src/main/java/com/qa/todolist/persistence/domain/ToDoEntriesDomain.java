package com.qa.todolist.persistence.domain;

import java.util.Date;

import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

@Entity
public class ToDoEntriesDomain {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;
	
	@NotNull
	private String description;
	
	@NotNull
	@Basic
	@Temporal(TemporalType.DATE)
	private java.util.Date dueDate;

	//empty constructor
	public ToDoEntriesDomain() {
		super();
	}

	//constructor with all fields
	public ToDoEntriesDomain(Long id, @NotNull String description, Date dueDate) {
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
