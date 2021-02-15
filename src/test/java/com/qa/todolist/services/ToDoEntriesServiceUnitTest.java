package com.qa.todolist.services;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
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

	// CRUD TESTS

	@Test
	public void readAll() {
		// resources
		Date date = new Date(2021 - 02 - 07);
		Date date2 = new Date(2021 - 03 - 07);
		List<ToDoEntriesDomain> test_list = new ArrayList<>();
		List<ToDoEntriesDTO> test_dto = new ArrayList<>();
		ToDoEntriesDomain test_entry1 = new ToDoEntriesDomain(1L, "task 1", date, true, null);
		ToDoEntriesDomain test_entry2 = new ToDoEntriesDomain(2L, "task 2", date2, true, null);
		test_list.add(test_entry1);
		test_list.add(test_entry2);
		ToDoEntriesDTO test_dto1 = new ToDoEntriesDTO(1L, "task 1", date, true);
		ToDoEntriesDTO test_dto2 = new ToDoEntriesDTO(2L, "task 2", date2, true);
		test_dto.add(test_dto1);
		test_dto.add(test_dto2);

		// rules
		Mockito.when(this.mockedMapper.map(test_entry1, ToDoEntriesDTO.class)).thenReturn(test_dto1);
		Mockito.when(this.mockedMapper.map(test_entry2, ToDoEntriesDTO.class)).thenReturn(test_dto2);
		Mockito.when(this.mockedRepo.findAll()).thenReturn(test_list);

		// actions
		List<ToDoEntriesDTO> result = this.service.readAll();

		// assertions
		Assertions.assertThat(result).isNotNull();
		Assertions.assertThat(result).isEqualTo(test_dto);
		Assertions.assertThat(result).usingRecursiveComparison().isEqualTo(test_dto);

		Mockito.verify(this.mockedRepo, Mockito.times(1)).findAll();
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(test_entry1, ToDoEntriesDTO.class);
		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(test_entry2, ToDoEntriesDTO.class);

	}

	@Test
	public void readOne() {
		Date date = new Date(2021 - 02 - 07);
		ToDoEntriesDomain test_entry = new ToDoEntriesDomain(1L, "task 1", date, true, null);
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
		ToDoEntriesDomain test_entry = new ToDoEntriesDomain(1L, "task 1", date, true, null);
		ToDoEntriesDTO test_dto = new ToDoEntriesDTO(1L, "task 1", date, true);
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
		// resources
		Date date = new Date(2021 - 02 - 07);
		ToDoEntriesDomain test_entry = new ToDoEntriesDomain(1L, "task 1", date, true, null);
		ToDoEntriesDomain updated_entry = new ToDoEntriesDomain(test_entry.getDescription(), test_entry.getDueDate(),
				test_entry.isCompleted(), test_entry.getMyList());
		updated_entry.setId(1L);
		ToDoEntriesDTO test_dto = this.mockedMapper.map(updated_entry, ToDoEntriesDTO.class);
		// rules
		Mockito.when(this.mockedRepo.findById(1L))
				.thenReturn(Optional.of(new ToDoEntriesDomain(test_entry.getDescription(), test_entry.getDueDate(),
						test_entry.isCompleted(), test_entry.getMyList())));
		Mockito.when(this.mockedMapper.map(updated_entry, ToDoEntriesDTO.class)).thenReturn(test_dto);
		Mockito.when(this.mockedRepo.save(updated_entry)).thenReturn(updated_entry);
		// actions
		ToDoEntriesDTO result = this.service.update(1L, test_entry);
		// assertions
		Assertions.assertThat(result).isEqualTo(test_dto);

		Mockito.verify(this.mockedMapper, Mockito.times(1)).map(updated_entry, ToDoEntriesDTO.class);
		Mockito.verify(this.mockedRepo, Mockito.times(1)).save(Mockito.any(ToDoEntriesDomain.class));
	}

	@Test
	public void delete() {
		Date date = new Date(2021 - 02 - 07);
		ToDoEntriesDomain test_entry = new ToDoEntriesDomain(1L, "task 1", date, true, null);
		ToDoEntriesDTO test_dto = new ToDoEntriesDTO(1L, "task 1", date, true);

		Mockito.when(this.mockedRepo.existsById(test_entry.getId())).thenReturn(true);

		Assertions.assertThat(this.service.delete(test_dto.getId())).isEqualTo(!true);

		Mockito.verify(this.mockedRepo, Mockito.times(1)).deleteById(test_entry.getId());
		Mockito.verify(this.mockedRepo, Mockito.times(1)).existsById(test_entry.getId());
	}
}
