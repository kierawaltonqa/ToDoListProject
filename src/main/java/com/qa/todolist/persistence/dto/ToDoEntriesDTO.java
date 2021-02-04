package com.qa.todolist.persistence.dto;

public class ToDoEntriesDTO {

	// note: data passed as response does not differ from data taken in for entries
	// still have DTO for convention purposes

	private Long id;
	private String description;
	private String dueDate;

	public ToDoEntriesDTO() {
		super();
	}

	public ToDoEntriesDTO(Long id, String description, String dueDate) {
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

	public String getDueDate() {
		return dueDate;
	}

	public void setDueDate(String dueDate) {
		this.dueDate = dueDate;
	}

}
