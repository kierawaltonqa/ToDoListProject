package com.qa.todolist.rest;

import java.util.ArrayList;
import java.util.List;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import com.qa.todolist.persistence.domain.ToDoListsDomain;
import com.qa.todolist.persistence.dto.ToDoListsDTO;
import com.qa.todolist.services.ToDoListsService;

@SpringBootTest
public class ToDoListsControllerUnitTest {

	@MockBean
	private ToDoListsService service;

	@MockBean
	private ModelMapper mapper;

	@Autowired
	ToDoListsController controller;

	private ToDoListsDTO mapToDTO(ToDoListsDomain model) {
		return this.mapper.map(model, ToDoListsDTO.class);
	}

	private final long ID = 1L;

	@Test
	public void readAll() {
		// resources
		List<ToDoListsDomain> test_list = new ArrayList<>();
		List<ToDoListsDTO> test_dtolist = new ArrayList<>();
		ToDoListsDomain item1 = new ToDoListsDomain(1L, "list 1", null);
		ToDoListsDomain item2 = new ToDoListsDomain(2L, "list 2", null);
		ToDoListsDTO dto1 = mapToDTO(item1);
		ToDoListsDTO dto2 = mapToDTO(item2);
		test_list.add(item1); 
		test_list.add(item2);
		test_dtolist.add(dto1);
		test_dtolist.add(dto2);
		// rules
		Mockito.when(service.readAll()).thenReturn(test_dtolist);
		// results
		ResponseEntity<List<ToDoListsDTO>> result = this.controller.readAll();
		ResponseEntity<List<ToDoListsDTO>> result2 = new ResponseEntity<List<ToDoListsDTO>>(test_dtolist,
				HttpStatus.OK);
		// assertions
		Assertions.assertThat(result).isEqualTo(result2);

		Mockito.verify(this.service, Mockito.times(1)).readAll();
	}

	@Test
	public void readOne() {
		// resources
		ToDoListsDomain test_list = new ToDoListsDomain(1L, "list 1", null);
		ToDoListsDTO test_dto = mapToDTO(test_list);
		// rules
		Mockito.when(this.service.readOne(ID)).thenReturn(test_dto);
		// results
		ResponseEntity<ToDoListsDTO> result = this.controller.readOne(ID);
		ResponseEntity<ToDoListsDTO> result2 = new ResponseEntity<ToDoListsDTO>(test_dto, HttpStatus.OK);
		// assertions
		Assertions.assertThat(result).isEqualTo(result2);

		Mockito.verify(this.service, Mockito.times(1)).readOne(ID);
	}

	@Test
	public void create() {
		// resources
		ToDoListsDomain test_list = new ToDoListsDomain(1L, "list 1", null);
		ToDoListsDTO test_dto = mapToDTO(test_list);
		// rules
		Mockito.when(this.service.create(test_list)).thenReturn(test_dto);
		// results
		ResponseEntity<ToDoListsDTO> result = this.controller.create(test_list);
		ResponseEntity<ToDoListsDTO> result2 = new ResponseEntity<ToDoListsDTO>(test_dto, HttpStatus.CREATED);
		// assertions
		Assertions.assertThat(result).isEqualTo(result2);

		Mockito.verify(this.service, Mockito.times(1)).create(test_list);
	}

	@Test
	public void update() {
		// resources
		ToDoListsDomain test_list = new ToDoListsDomain(1L, "list 1", null);
		ToDoListsDomain updated_list = new ToDoListsDomain(test_list.getTitle(), test_list.getToDoList());
		updated_list.setId(ID);
		ToDoListsDTO test_dto = mapToDTO(updated_list);
		// rules
		Mockito.when(this.service.update(ID, updated_list)).thenReturn(test_dto);
		// results
		ResponseEntity<ToDoListsDTO> result = this.controller.update(ID, updated_list);
		ResponseEntity<ToDoListsDTO> result2 = new ResponseEntity<ToDoListsDTO>(test_dto, HttpStatus.ACCEPTED);
		// assertions
		Assertions.assertThat(result).isEqualTo(result2);

		Mockito.verify(this.service, Mockito.times(1)).update(ID, updated_list);

	}

	@Test
	public void delete() {

	}
}
