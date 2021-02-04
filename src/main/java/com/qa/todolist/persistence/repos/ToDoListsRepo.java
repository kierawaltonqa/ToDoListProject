package com.qa.todolist.persistence.repos;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.qa.todolist.persistence.domain.ToDoListsDomain;

@Repository
public interface ToDoListsRepo extends JpaRepository<ToDoListsDomain, Long> {

}
