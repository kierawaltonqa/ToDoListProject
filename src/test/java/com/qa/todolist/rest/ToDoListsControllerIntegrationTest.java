package com.qa.todolist.rest;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.todolist.persistence.dto.ToDoListsDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class ToDoListsControllerIntegrationTest {
	
	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;
	
	private final int ID = 1;
	private final ToDoListsDTO entry1
	
	@Test
	public void readAll() {
		//resources
		List<ToDoListsDTO> expectedResult = new ArrayList<>();
		
		// resources
//				List<ToDoEntriesDTO> expectedResult = new ArrayList<>();
//				expectedResult.add(entry1);
//				expectedResult.add(entry2);
//				expectedResult.add(entry3);
//				// request
//				MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
//						"http://localhost:8080/entries/readAll");
//				// expectations
//				ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
//				ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
//				// action
//				this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
//		
	}
	@Test
	public void readOne() {
		
	}
	@Test
	public void create() {
		
	}
	@Test
	public void update() {
		
	}
	@Test
	public void delete() {
		
	}
}
