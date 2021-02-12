package com.qa.todolist.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qa.todolist.persistence.domain.ToDoListsDomain;
import com.qa.todolist.persistence.dto.ToDoListsDTO;
import com.qa.todolist.persistence.repos.ToDoListsRepo;

@Service
public class ToDoListsService {

	private ToDoListsRepo repo;
	private ModelMapper mapper;

	@Autowired
	public ToDoListsService(ToDoListsRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private ToDoListsDTO mapToDTO(ToDoListsDomain model) {
		return this.mapper.map(model, ToDoListsDTO.class);
	}

	// GET - read all
	public List<ToDoListsDTO> readAll() {
		List<ToDoListsDomain> todoList = this.repo.findAll();
		List<ToDoListsDTO> todoDTOList = todoList.stream().map(this::mapToDTO).collect(Collectors.toList());
		return todoDTOList;
	}

	// GET - read by id
	public ToDoListsDTO readOne(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());
	}

	// POST - create
	public ToDoListsDTO create(ToDoListsDomain model) {
		return this.mapToDTO(this.repo.save(model));
	}

	// PUT - update
	public ToDoListsDTO update(Long id, ToDoListsDomain newDetails) {
		this.repo.findById(id).orElseThrow();
		newDetails.setId(id);
		ToDoListsDTO result = this.mapToDTO(this.repo.save(newDetails));
		return result;
	}

	// DELETE - delete
	public boolean delete(Long id) {
		try {
			this.repo.deleteById(id);
			boolean exists = this.repo.existsById(id);

			return !exists;

		} catch (EmptyResultDataAccessException e) {
			e.printStackTrace();
			return false;
		}
	}

}
