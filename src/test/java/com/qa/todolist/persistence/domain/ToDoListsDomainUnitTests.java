package com.qa.todolist.persistence.domain;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import com.qa.todolist.persistence.dto.ToDoEntriesDTO;

@SpringBootTest
public class ToDoListsDomainUnitTests {

	private ToDoListsDomain list = new ToDoListsDomain(1L,"list 1", null);
	private ToDoListsDomain list2 = new ToDoListsDomain(2L,"list 2", null);
	private ToDoListsDomain list3 = new ToDoListsDomain(2L,"list 2", null);
	
	private final List<ToDoEntriesDomain> entryList = new ArrayList<>();
	private final Date date1 = Date.valueOf("2021-02-06");
	private final ToDoEntriesDomain entry1 = new ToDoEntriesDomain(1L, "create back end", date1, null);
	
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
	@Test
	public void constructorWithoutId() {
		ToDoListsDomain list4 = new ToDoListsDomain("List 4", null);
		Assertions.assertThat(list4.getId()).isNull();
		Assertions.assertThat(list4.getTitle()).isNotNull();
		Assertions.assertThat(list4.getTitle()).isEqualTo("List 4");
		Assertions.assertThat(list4.getToDoList()).isNull();
	}
	
	
}
