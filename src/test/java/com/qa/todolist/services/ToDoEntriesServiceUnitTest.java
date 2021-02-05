package com.qa.todolist.services;

import java.sql.Date;
import java.text.SimpleDateFormat;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import com.qa.todolist.persistence.domain.ToDoEntriesDomain;
import com.qa.todolist.persistence.dto.ToDoEntriesDTO;
import com.qa.todolist.persistence.repos.ToDoEntriesRepo;

@SpringBootTest
public class ToDoEntriesServiceUnitTest {

	@MockBean
	private ModelMapper mockedMapper;

	@MockBean
	private ToDoEntriesRepo mockedRepo;

	@Autowired
	ToDoEntriesService service;

//	private final Date date = new Date(2021-02-07);
//	SimpleDateFormat DateFor = new SimpleDateFormat("dd/MM/yyyy");
//	String stringDate= DateFor.format(date);

	// CRUD TESTS

	// read all

	// read one
	@Test
	public void readOne() {
		Date date = new Date(2021 - 02 - 07);
		ToDoEntriesDomain test_entry = new ToDoEntriesDomain(1L, "task 1", date, null);
		ToDoEntriesDTO test_dto = this.mockedMapper.map(test_entry, ToDoEntriesDTO.class);
		Mockito.when(this.mockedRepo.findById(test_entry.getId())).thenReturn(Optional.of(test_entry));
		ToDoEntriesDTO result = this.service.readOne(1L);
		Assertions.assertThat(result).isEqualTo(test_dto);

		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);
	}

	// create
	@Test
	public void create() {
		// resources
		Date date = new Date(2021 - 02 - 07);
		ToDoEntriesDomain test_entry = new ToDoEntriesDomain(1L, "task 1", date, null);
		ToDoEntriesDTO test_dto = new ToDoEntriesDTO(1L, "task 1", date);
		// rules
		Mockito.when(this.mockedRepo.save(Mockito.any(ToDoEntriesDomain.class))).thenReturn(test_entry);
		Mockito.when(this.mockedMapper.map(test_entry, ToDoEntriesDTO.class)).thenReturn(test_dto);
		// actions
		ToDoEntriesDTO result = this.service.create(test_entry);
		// assertions
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(test_dto);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(test_dto);

		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(test_entry, ToDoEntriesDTO.class);
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(ToDoEntriesDomain.class));
	}

}
