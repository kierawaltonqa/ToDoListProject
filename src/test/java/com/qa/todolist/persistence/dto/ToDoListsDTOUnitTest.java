package com.qa.todolist.persistence.dto;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToDoListsDTOUnitTest {

	//same as domain, except no constructor without id
	

	private ToDoListsDTO list = new ToDoListsDTO(1L,"list 1", null);
	private ToDoListsDTO list2 = new ToDoListsDTO(2L,"list 2", null);
	private ToDoListsDTO list3 = new ToDoListsDTO(2L,"list 2", null);
	
	private final List<ToDoEntriesDTO> entryList = new ArrayList<>();
	private final Date date1 = Date.valueOf("2021-02-06");
	private final ToDoEntriesDTO entry1 = new ToDoEntriesDTO(1L, "create back end", date1);
	
	@Test
	public void settersTest() {
		Assertions.assertThat(list).isNotNull();
		list.setId(1L);
		list.setTitle("list 1");
		list.setToDoList(null);
		
		Assertions.assertThat(list.getId()).isNotNull();
		Assertions.assertThat(list.getTitle()).isNotNull();
		Assertions.assertThat(list.getToDoList()).isNull();
	}
	@Test 
	public void equalsWithNull() {
		Assertions.assertThat(list).isNotNull();
	}
	@Test
	public void equalsWithDifferentObjects() {
		Assertions.assertThat(list).isNotEqualTo(list2);
	}
	@Test
	public void createList() {
		Assertions.assertThat(list.getId()).isEqualTo(1L);
		Assertions.assertThat(list.getTitle()).isEqualTo("list 1");
		Assertions.assertThat(list.getToDoList()).isEqualTo(null);
	}
	@Test
	public void checkEquality() {
		Assertions.assertThat(list).isEqualTo(list);
	}
	@Test
	public void checkEqualityBetweenDifferent() {
		Assertions.assertThat(list2.getTitle()).isEqualTo(list3.getTitle());
		Assertions.assertThat(list2.getId()).isEqualTo(list3.getId());
	}
	@Test
	public void checkNonEquality() {
		Assertions.assertThat(list.getId()).isNotEqualTo(list2.getId());
		Assertions.assertThat(list.getTitle()).isNotEqualTo(list2.getTitle());
	}
	@Test
	public void oneListTitleNull() {
		list2.setTitle(null);
		Assertions.assertThat(list2.getTitle()).isNotEqualTo(list3.getTitle());
	}
	@Test
	public void listTitlesNotEqual() {
		Assertions.assertThat(list.getTitle()).isNotEqualTo(list2.getTitle());
	}
	@Test
	public void listIdsNotEqual() {
		Assertions.assertThat(list.getId()).isNotEqualTo(list2.getId());
	}
	@Test
	public void nullId() {
		list.setId(null);
		Assertions.assertThat(list.getId()).isNull();
	}
	@Test
	public void nullId2() {
		list2.setId(null);
		Assertions.assertThat(list.getId()).isNotEqualTo(list3.getId());
	}
	@Test
	public void toDoListNotNull() {
		entryList.add(entry1);
		list2.setToDoList(entryList);
		Assertions.assertThat(list2.getToDoList()).isNotNull();
		Assertions.assertThat(list2.getToDoList()).isNotEqualTo(list3.getToDoList());
	}
	//testing at 91% 
}
