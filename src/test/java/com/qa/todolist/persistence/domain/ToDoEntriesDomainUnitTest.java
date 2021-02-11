package com.qa.todolist.persistence.domain;

import java.sql.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToDoEntriesDomainUnitTest {

	private java.util.Date dueDate = Date.valueOf("2021-02-06");
	private java.util.Date dueDate2 = Date.valueOf("2021-02-07");

	private ToDoEntriesDomain entry = new ToDoEntriesDomain(1L, "task1", dueDate, true, null);
	private ToDoEntriesDomain other = new ToDoEntriesDomain(2L, "task2", dueDate2, true, null);
	private ToDoEntriesDomain other2 = new ToDoEntriesDomain(2L, "task2", dueDate2, true, null);
	private ToDoListsDomain list = new ToDoListsDomain(1L, "list 1", null);

	@Test
	public void settersTest() {
		Assertions.assertThat(entry).isNotNull();
		entry.setId(1L);
		entry.setDescription("task1");
		entry.setDueDate(dueDate);
		entry.setCompleted(true);
		entry.setMyList(null);

		Assertions.assertThat(entry.getId()).isNotNull();
		Assertions.assertThat(entry.getDescription()).isNotNull();
		Assertions.assertThat(entry.getDueDate()).isNotNull();
		Assertions.assertThat(entry.isCompleted());
		Assertions.assertThat(entry.getMyList()).isNull();

	}

	@Test
	public void gettersTest() {
		Assertions.assertThat(entry.getId()).isNotNull();
		Assertions.assertThat(entry.getDescription()).isNotNull();
		Assertions.assertThat(entry.getDueDate()).isNotNull();
		Assertions.assertThat(entry.isCompleted()).isTrue();
		Assertions.assertThat(entry.getMyList()).isNull();
	}

	@Test
	public void createEntry() {
		Assertions.assertThat(entry.getId()).isEqualTo(1L);
		Assertions.assertThat(entry.getDescription()).isEqualTo("task1");
		Assertions.assertThat(entry.getDueDate()).isEqualTo(dueDate);
		Assertions.assertThat(entry.isCompleted()).isEqualTo(true);
		Assertions.assertThat(entry.getMyList()).isEqualTo(null);
	}

	@Test
	public void checkEquality() {
		Assertions.assertThat(other.getId()).isEqualTo(other2.getId());
		Assertions.assertThat(other.getDescription()).isEqualTo(other2.getDescription());
		Assertions.assertThat(other.getDueDate()).isEqualTo(other2.getDueDate());
		Assertions.assertThat(other.isCompleted()).isEqualTo(other2.isCompleted());
		Assertions.assertThat(other.getMyList()).isEqualTo(other2.getMyList());
	}

	@Test
	public void checkNonEquality() {
		Assertions.assertThat(entry.getId()).isNotEqualTo(other.getId());
		Assertions.assertThat(entry.getDescription()).isNotEqualTo(other.getDescription());
		Assertions.assertThat(entry.getDueDate()).isNotEqualTo(other.getDueDate());
	}

	@Test
	public void nullId() {
		entry.setId(null);
		Assertions.assertThat(entry.getId()).isNull();
	}

	@Test
	public void nullDescription() {
		entry.setDescription(null);
		Assertions.assertThat(entry.getDescription()).isNull();
	}

	@Test
	public void nullDescription2() {
		other.setDescription(null);
		Assertions.assertThat(other.getDescription()).isNotEqualTo(other2.getDescription());
	}

	@Test
	public void falseCompleted() {
		entry.setCompleted(false);
		Assertions.assertThat(entry.isCompleted()).isFalse();
	}

	@Test
	public void falseCompleted2() {
		entry.setCompleted(false);
		Assertions.assertThat(entry.isCompleted()).isNotEqualTo(other.isCompleted());
	}

	@Test
	public void trueCompleted() {
		Assertions.assertThat(entry.isCompleted()).isTrue();
	}

	@Test
	public void myList() {
		entry.setMyList(list);
		Assertions.assertThat(entry.getMyList()).isNotNull();
	}

	@Test
	public void myList2() {
		other.setMyList(list);
		other2.setMyList(list);
		Assertions.assertThat(other.getMyList()).isNotNull();
		Assertions.assertThat(other2.getMyList()).isNotNull();
		Assertions.assertThat(other.getMyList()).isEqualTo(other2.getMyList());
	}

	@Test
	public void constructorWithoutIdTest() {
		ToDoEntriesDomain entry4 = new ToDoEntriesDomain("entry", dueDate, true, null);
		Assertions.assertThat(entry4.getId()).isNull();
		Assertions.assertThat(entry4.getDescription()).isEqualTo("entry");
		Assertions.assertThat(entry4.isCompleted()).isTrue();
		Assertions.assertThat(entry4.getDueDate()).isEqualTo(dueDate);
	}
}
