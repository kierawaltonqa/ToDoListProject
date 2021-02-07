package com.qa.todolist.rest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

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
	public void readAll() {
		//resources
		List<ToDoEntriesDomain> test_list = new ArrayList<>();
		List<ToDoEntriesDTO> test_dtolist = new ArrayList<>();
		Date date = Date.valueOf("2021-02-06");
		ToDoEntriesDomain test_entry = new ToDoEntriesDomain(1L, "task 1", date, null);
		ToDoEntriesDTO test_dto = mapToDTO(test_entry);
		Date date2 = Date.valueOf("2021-02-07");
		ToDoEntriesDomain test_entry2 = new ToDoEntriesDomain(2L, "task 2", date2, null);
		ToDoEntriesDTO test_dto2 = mapToDTO(test_entry2);
		test_list.add(test_entry);
		test_list.add(test_entry2);
		test_dtolist.add(test_dto);
		test_dtolist.add(test_dto2); 
		//rules
		Mockito.when(service.readAll()).thenReturn(test_dtolist);
		//results
		ResponseEntity <List<ToDoEntriesDTO>> result = this.controller.readAll();
		ResponseEntity <List<ToDoEntriesDTO>> result2 = new ResponseEntity<List<ToDoEntriesDTO>>(test_dtolist, HttpStatus.OK);
		//assertions
		Assertions.assertThat(result).isEqualTo(result2);
		
		Mockito.verify(this.service, Mockito.times(1)).readAll();
	}

	@Test
	public void readOne() {
		// resources
		Date date = Date.valueOf("2021-02-06");
		ToDoEntriesDomain test_entry = new ToDoEntriesDomain(1L, "task 1", date, null);
		ToDoEntriesDTO test_dto = mapToDTO(test_entry);
		// rules
		Mockito.when(this.service.readOne(1L)).thenReturn(test_dto);
		// results
		ResponseEntity<ToDoEntriesDTO> result = this.controller.readOne(1L);
		ResponseEntity<ToDoEntriesDTO> result2 = new ResponseEntity<ToDoEntriesDTO>(test_dto, HttpStatus.OK);
		// assertions
		Assertions.assertThat(result).isEqualTo(result2);
		
		Mockito.verify(this.service, Mockito.times(1)).readOne(1L);
	}

	@Test
	public void create() {
		// resources
		Date date = Date.valueOf("2021-02-06");
		ToDoEntriesDomain test_entry = new ToDoEntriesDomain(1L, "task 1", date, null);
		ToDoEntriesDTO test_dto = mapToDTO(test_entry);
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
		// resources
		Date date = Date.valueOf("2021-02-06");
		ToDoEntriesDomain test_entry = new ToDoEntriesDomain(1L, "task 1", date, null);
		ToDoEntriesDomain updated_entry = new ToDoEntriesDomain(test_entry.getDescription(), test_entry.getDueDate(), test_entry.getMyList());
		updated_entry.setId(1L);
		ToDoEntriesDTO test_dto = mapToDTO(updated_entry);
		// rules
		Mockito.when(this.service.update(1L, updated_entry)).thenReturn(test_dto);
		// results
		ResponseEntity<ToDoEntriesDTO> result = this.controller.update(1L, updated_entry);
		ResponseEntity<ToDoEntriesDTO> result2 = new ResponseEntity<ToDoEntriesDTO>(test_dto, HttpStatus.ACCEPTED);
		//assertions
		Assertions.assertThat(result).isEqualTo(result2);
		
		Mockito.verify(this.service, Mockito.times(1)).update(1L, updated_entry);
	}

	@Test
	public void delete() {
//		//resources
//		Date date = Date.valueOf("2021-02-06");
//		ToDoEntriesDomain test_entry = new ToDoEntriesDomain(1L, "task 1", date, null);
//		//rules
//		Mockito.when(this.controller.delete(test_entry.getId())).then(null);
	}

}
