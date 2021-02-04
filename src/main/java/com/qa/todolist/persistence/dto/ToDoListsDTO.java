package com.qa.todolist.persistence.dto;

public class ToDoListsDTO {

	private Long id;
	private String title;
	
	public ToDoListsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ToDoListsDTO(Long id, String title) {
		super();
		this.id = id;
		this.title = title;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}
	
	
}
