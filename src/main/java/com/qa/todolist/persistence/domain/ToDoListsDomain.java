package com.qa.todolist.persistence.domain;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.validation.constraints.NotNull;

import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

@Entity
public class ToDoListsDomain {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long id;

	@NotNull
	private String title;

	@OneToMany(mappedBy = "myList", fetch = FetchType.EAGER)
	@OnDelete(action = OnDeleteAction.CASCADE)
	private List<ToDoEntriesDomain> toDoList;

	public ToDoListsDomain() {
		super();
		// TODO Auto-generated constructor stub
	}

	public ToDoListsDomain(Long id, String title, List<ToDoEntriesDomain> toDoList) {
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

	public List<ToDoEntriesDomain> getToDoList() {
		return toDoList;
	}

	public void setToDoList(List<ToDoEntriesDomain> toDoList) {
		this.toDoList = toDoList;
	}

}
