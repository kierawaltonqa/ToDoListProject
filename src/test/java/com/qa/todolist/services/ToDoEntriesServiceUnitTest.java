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
//		CatDomain test_cat = new CatDomain(1L, "Suki", 3, 2.5f, null);
//		CatDTO test_dto = this.mockedMapper.map(test_cat, CatDTO.class);
//		Mockito.when(this.mockedRepo.findById(test_cat.getId())).thenReturn(Optional.of(test_cat));
//		CatDTO result = this.service.readOne(1L);
//		Assertions.assertThat(result).isEqualTo(test_dto);
//
//		Mockito.verify(this.mockedRepo, Mockito.times(1)).findById(1L);
//	}

}
