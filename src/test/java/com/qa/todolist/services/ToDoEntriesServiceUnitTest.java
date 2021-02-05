package com.qa.todolist.services;

import java.sql.Date;
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

	@Test
	public void update() {
		//resources
		Date date = new Date(2021 - 02 - 07);
		ToDoEntriesDomain test_entry = new ToDoEntriesDomain(1L, "task 1", date, null);
		ToDoEntriesDomain updated_entry = new ToDoEntriesDomain(test_entry.getDescription(), test_entry.getDueDate(),
				test_entry.getMyList());
		updated_entry.setId(1L);
		ToDoEntriesDTO test_dto = this.mockedMapper.map(updated_entry, ToDoEntriesDTO.class);
		//rules
		Mockito.when(this.mockedRepo.findById(1L)).thenReturn(Optional.of(
				new ToDoEntriesDomain(test_entry.getDescription(), test_entry.getDueDate(), test_entry.getMyList())));
		Mockito.when(this.mockedMapper.map(updated_entry, ToDoEntriesDTO.class)).thenReturn(test_dto);
		Mockito.when(this.mockedRepo.save(updated_entry)).thenReturn(updated_entry);
		//actions
		ToDoEntriesDTO result = this.service.update(1L, test_entry);
		//assertions
		Assertions.assertThat(result).isEqualTo(test_dto);
		
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(updated_entry, ToDoEntriesDTO.class);
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(ToDoEntriesDomain.class));
	}

	@Test
	public void delete() {
		Date date = new Date(2021 - 02 - 07);
		ToDoEntriesDomain test_entry = new ToDoEntriesDomain(1L, "task 1", date, null);
		ToDoEntriesDTO test_dto = new ToDoEntriesDTO(1L, "task 1", date);

		Mockito.when(this.mockedRepo.existsById(test_entry.getId())).thenReturn(true);

		Assertions.assertThat(this.service.delete(test_dto.getId())).isEqualTo(!true);

		Mockito.verify(this.mockedRepo, Mockito.times(1)).deleteById(test_entry.getId());
		Mockito.verify(this.mockedRepo, Mockito.times(1)).existsById(test_entry.getId());
	}
}
