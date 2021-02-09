package com.qa.todolist.services;

import java.util.List;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.todolist.persistence.domain.ToDoListsDomain;
import com.qa.todolist.persistence.dto.ToDoListsDTO;
import com.qa.todolist.persistence.repos.ToDoListsRepo;

@SpringBootTest
public class ToDoListsServiceUnitTest {

	@MockBean
	private ModelMapper mockedMapper;

	@MockBean
	private ToDoListsRepo mockedRepo;

	@Autowired
	ToDoListsService service;
	
	@Test
	public void readAll() {
		Long id = 1L; 
		ToDoListsDomain test_list = new ToDoListsDomain(1L, "list 1", null);
		test_list.setId(id);
		List<ToDoListsDomain> lists = this.mockedRepo.findAll(); 
		ToDoListsDTO test_dto = this.mockedMapper.map(lists, ToDoListsDTO.class);
		Mockito.when(this.mockedRepo.findAll()).thenReturn(lists);
		Mockito.when(this.mockedMapper.map(lists, ToDoListsDTO.class)).thenReturn(test_dto);
		Assertions.assertThat(lists).isNotNull();
		Assertions.assertThat(this.service.readAll()).isEqualTo(lists);
		Mockito.verify(this.mockedRepo, Mockito.times(2)).findAll();
	}

	@Test
	public void readOne() {
		ToDoListsDomain test_list = new ToDoListsDomain(1L, "list 1", null);
		ToDoListsDTO test_dto = this.mockedMapper.map(test_list, ToDoListsDTO.class);
		Mockito.when(this.mockedRepo.findById(test_list.getId())).thenReturn(Optional.of(test_list));
		ToDoListsDTO result = this.service.readOne(1L);
		Assertions.assertThat(result).isEqualTo(test_dto);
		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);
	}

	@Test
	public void create() {
		// resources
		ToDoListsDomain test_list = new ToDoListsDomain(1L, "list 1", null);
		ToDoListsDTO test_dto = new ToDoListsDTO(1L, "list 1", null);
		// rules
		Mockito.when(this.mockedRepo.save(Mockito.any(ToDoListsDomain.class))).thenReturn(test_list);
		Mockito.when(this.mockedMapper.map(test_list, ToDoListsDTO.class)).thenReturn(test_dto);
		// actions
		ToDoListsDTO result = this.service.create(test_list);
		// assertions
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(test_dto);
		
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(test_list, ToDoListsDTO.class);
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(ToDoListsDomain.class));
	}

	@Test
	public void update() {
		// resources
		ToDoListsDomain test_list = new ToDoListsDomain(1L, "list 1", null);
		ToDoListsDomain updated_list = new ToDoListsDomain(test_list.getTitle(), test_list.getToDoList());
		updated_list.setId(1L);
		ToDoListsDTO test_dto = this.mockedMapper.map(updated_list, ToDoListsDTO.class);
		// rules
		Mockito.when(this.mockedRepo.findById(1L))
				.thenReturn(Optional.of(new ToDoListsDomain(test_list.getTitle(), test_list.getToDoList())));
		Mockito.when(this.mockedMapper.map(updated_list, ToDoListsDTO.class)).thenReturn(test_dto);
		Mockito.when(this.mockedRepo.save(updated_list)).thenReturn(updated_list);
		// actions
		ToDoListsDTO result = this.service.update(1L, test_list);
		// assertions
		Assertions.assertThat(result).isEqualTo(test_dto);

		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(updated_list, ToDoListsDTO.class);
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(ToDoListsDomain.class));
	}

	@Test
	public void delete() {
		Long id = 1L;
		Mockito.when(this.mockedRepo.existsById(id)).thenReturn(false);
		Assertions.assertThat(this.service.delete(id)).isTrue();
		Mockito.verify(this.mockedRepo, Mockito.times(1)).existsById(id);
	}

}
