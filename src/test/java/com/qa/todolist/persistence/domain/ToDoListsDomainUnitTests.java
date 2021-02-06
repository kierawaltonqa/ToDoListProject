package com.qa.todolist.persistence.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToDoListsDomainUnitTests {

	private ToDoListsDomain list = new ToDoListsDomain(1L,"list 1", null);
	private ToDoListsDomain list2 = new ToDoListsDomain(2L,"list 2", null);
	private ToDoListsDomain list3 = new ToDoListsDomain(2L,"list 2", null);
	
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
		Assertions.assertThat(list).isNotEqualTo(list2);
	}
	@Test
	public void oneListTitleNull() {
		list2.setTitle(null);
		Assertions.assertThat(list2).isNotEqualTo(list3);
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

	
}
