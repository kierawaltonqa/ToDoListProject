package com.qa.todolist.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.qa.todolist.persistence.domain.ToDoListsDomain;
import com.qa.todolist.persistence.dto.ToDoListsDTO;
import com.qa.todolist.services.ToDoListsService;

@RestController
@RequestMapping("/lists")
public class ToDoListsController {

	private ToDoListsService service;

	@Autowired
	public ToDoListsController(ToDoListsService service) {
		super();
		this.service = service;
	}

	// GET - read all
	@GetMapping("/readAll")
	public ResponseEntity<List<ToDoListsDTO>> readAll() {
		return new ResponseEntity<List<ToDoListsDTO>>(this.service.readAll(), HttpStatus.OK);
	}

	// GET - read by id
	@GetMapping("read/{id}")
	public ResponseEntity<ToDoListsDTO> readOne(@PathVariable("id") Long id) {
		return ResponseEntity.ok(this.service.readOne(id));
	}

	// POST
	@PostMapping("/create")
	public ResponseEntity<ToDoListsDTO> create(@RequestBody ToDoListsDomain entry) {
		return new ResponseEntity<ToDoListsDTO>(this.service.create(entry), HttpStatus.CREATED);
	}

	// PUT
	@PutMapping("/update/{id}")
	public ResponseEntity<ToDoListsDTO> update(@PathVariable Long id, @RequestBody ToDoListsDomain entry) {
		return new ResponseEntity<ToDoListsDTO>(this.service.update(id, entry), HttpStatus.ACCEPTED);
	}

	// DELETE
	@DeleteMapping("/delete/{id}")
	public ResponseEntity<Object> delete(@PathVariable Long id) {
		return this.service.delete(id) ? new ResponseEntity<>(HttpStatus.NO_CONTENT)
				: new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
	}
}
