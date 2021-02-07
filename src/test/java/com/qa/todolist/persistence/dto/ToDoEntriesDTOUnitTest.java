package com.qa.todolist.persistence.dto;

import java.sql.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class ToDoEntriesDTOUnitTest {

	private java.util.Date dueDate = Date.valueOf("2021-02-06");
	private java.util.Date dueDate2 = Date.valueOf("2021-02-07");
	
	private ToDoEntriesDTO entry = new ToDoEntriesDTO(1L,"task1", dueDate);
	private ToDoEntriesDTO other = new ToDoEntriesDTO(2L,"task2", dueDate2);
	private ToDoEntriesDTO other2 = new ToDoEntriesDTO(2L,"task2", dueDate2);
	
	@Test
	public void settersTest() {
		Assertions.assertThat(entry).isNotNull();
		entry.setId(1L);
		entry.setDescription("task1");
		entry.setDueDate(dueDate);
		
		Assertions.assertThat(entry.getId()).isNotNull();
		Assertions.assertThat(entry.getDescription()).isNotNull();
		Assertions.assertThat(entry.getDueDate()).isNotNull();
	}
	@Test
	public void gettersTest() {
		Assertions.assertThat(entry.getId()).isNotNull();
		Assertions.assertThat(entry.getDescription()).isNotNull();
		Assertions.assertThat(entry.getDueDate()).isNotNull();
	}
	@Test
	public void createEntry() {
		Assertions.assertThat(entry.getId()).isEqualTo(1L);
		Assertions.assertThat(entry.getDescription()).isEqualTo("task1");
		Assertions.assertThat(entry.getDueDate()).isEqualTo(dueDate);
	}
	@Test
	public void checkEquality() {
		Assertions.assertThat(other.getId()).isEqualTo(other2.getId());
		Assertions.assertThat(other.getDescription()).isEqualTo(other2.getDescription());
		Assertions.assertThat(other.getDueDate()).isEqualTo(other2.getDueDate());
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
}
