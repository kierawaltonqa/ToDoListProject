package com.qa.todolist.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.todolist.persistence.domain.ToDoEntriesDomain;

@Repository
public interface ToDoEntriesRepo extends JpaRepository<ToDoEntriesDomain, Long> {

	// CRUD -> h2 database

}
