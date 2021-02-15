package com.qa.todolist.services;

import java.util.List;
import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;

import com.qa.todolist.persistence.domain.ToDoEntriesDomain;
import com.qa.todolist.persistence.dto.ToDoEntriesDTO;
import com.qa.todolist.persistence.repos.ToDoEntriesRepo;

@Service
public class ToDoEntriesService {

	// we call on the repo to extract data to and from the db

	private ToDoEntriesRepo repo;
	private ModelMapper mapper;

	@Autowired
	public ToDoEntriesService(ToDoEntriesRepo repo, ModelMapper mapper) {
		super();
		this.repo = repo;
		this.mapper = mapper;
	}

	private ToDoEntriesDTO mapToDTO(ToDoEntriesDomain model) {
		return this.mapper.map(model, ToDoEntriesDTO.class);
	}

	// GET - read all
	public List<ToDoEntriesDTO> readAll() {
		List<ToDoEntriesDomain> dbList = this.repo.findAll();
		List<ToDoEntriesDTO> resultList = dbList.stream().map(this::mapToDTO).collect(Collectors.toList());
		return resultList;
	}

	// GET - read by id
	public ToDoEntriesDTO readOne(Long id) {
		return mapToDTO(this.repo.findById(id).orElseThrow());
	}

	// POST - create
	public ToDoEntriesDTO create(ToDoEntriesDomain model) {
		return this.mapToDTO(this.repo.save(model));
	}

	// PUT - update
	public ToDoEntriesDTO update(Long id, ToDoEntriesDomain newDetails) {
		this.repo.findById(id).orElseThrow();
		newDetails.setId(id);
		ToDoEntriesDTO result = this.mapToDTO(this.repo.save(newDetails));
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
