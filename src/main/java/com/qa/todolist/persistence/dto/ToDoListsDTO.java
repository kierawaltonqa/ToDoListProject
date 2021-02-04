package com.qa.todolist.persistence.dto;

import java.util.List;

public class ToDoListsDTO {

	private Long id;
	private String title;
	private List<ToDoEntriesDTO> toDoList;

	public ToDoListsDTO() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ToDoListsDTO(Long id, String title, List<ToDoEntriesDTO> toDoList) {
		super();
		this.id = id;
		this.title = title;
		this.toDoList = toDoList;
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

	public List<ToDoEntriesDTO> getToDoList() {
		return toDoList;
	}

	public void setToDoList(List<ToDoEntriesDTO> toDoList) {
		this.toDoList = toDoList;
	}

}
