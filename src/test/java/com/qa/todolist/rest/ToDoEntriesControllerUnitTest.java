package com.qa.todolist.rest;

import java.sql.Date;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.todolist.persistence.domain.ToDoEntriesDomain;
import com.qa.todolist.persistence.dto.ToDoEntriesDTO;
import com.qa.todolist.services.ToDoEntriesService;

@SpringBootTest
public class ToDoEntriesControllerUnitTest {

	@MockBean
	private ToDoEntriesService service;

	@Autowired
	ToDoEntriesController controller;

	@MockBean
	private ModelMapper mapper;

	private ToDoEntriesDTO mapToDTO(ToDoEntriesDomain model) {
		return this.mapper.map(model, ToDoEntriesDTO.class);
	}

	@Test
	public void readOne() {
		// resources
		Date date = Date.valueOf("2021-02-06");
		ToDoEntriesDomain test_entry = new ToDoEntriesDomain(1L, "task 1", date, null);
		ToDoEntriesDTO test_dto = this.mapper.map(test_entry, ToDoEntriesDTO.class);
		//rules
		Mockito.when(this.service.readOne(1L)).thenReturn(test_dto);
		//results
		ResponseEntity<ToDoEntriesDTO> result = this.controller.readOne(1L);
		ResponseEntity<ToDoEntriesDTO> result2 = new ResponseEntity<ToDoEntriesDTO>(test_dto, HttpStatus.OK);
		//assertions
		Assertions.assertThat(result).isEqualTo(result2);	
	}

	@Test
	public void create() {
		// resources
		Date date = Date.valueOf("2021-02-06");
		ToDoEntriesDomain test_entry = new ToDoEntriesDomain(1L, "task 1", date, null);
		ToDoEntriesDTO test_dto = this.mapper.map(test_entry, ToDoEntriesDTO.class);
		// rules
		Mockito.when(this.service.create(test_entry)).thenReturn(test_dto);
		// results
		ResponseEntity<ToDoEntriesDTO> result = this.controller.create(test_entry);
		ResponseEntity<ToDoEntriesDTO> result2 = new ResponseEntity<ToDoEntriesDTO>(test_dto, HttpStatus.CREATED);
		// assertions
		Assertions.assertThat(result2).isEqualTo(result);

		Mockito.verify(this.service, Mockito.times(1)).create(test_entry);

	}
	@Test
	public void update() {
		
	}
	@Test
	public void delete() {
		
	}
	
}
