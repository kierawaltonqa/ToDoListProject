package com.qa.todolist.rest;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.Test;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.jdbc.Sql.ExecutionPhase;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultMatcher;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.qa.todolist.persistence.domain.ToDoListsDomain;
import com.qa.todolist.persistence.dto.ToDoEntriesDTO;
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

	private ToDoListsDTO mapToDTO(ToDoListsDomain model) {
		return this.mapper.map(model, ToDoListsDTO.class);
	}

	private final int ID = 2;

	private final List<ToDoEntriesDTO> entryList = new ArrayList<>();
	private final List<ToDoEntriesDTO> entryList2 = new ArrayList<>();

	Date date1 = Date.valueOf("2021-02-06");
	Date date2 = Date.valueOf("2021-02-12");
	Date date3 = Date.valueOf("2021-02-07");

	private final ToDoEntriesDTO entry1 = new ToDoEntriesDTO(1L, "create back end", date1, true);
	private final ToDoEntriesDTO entry2 = new ToDoEntriesDTO(2L, "testing for back end", date2, false);
	private final ToDoEntriesDTO entry3 = new ToDoEntriesDTO(3L, "buy dad a birthday present", date3, true);

	@Test
	public void readAll() throws Exception {
		// resources
		List<ToDoListsDTO> expectedResult = new ArrayList<>();
		entryList.add(entry3);
		entryList2.add(entry1);
		entryList2.add(entry2);
		ToDoListsDTO list1 = new ToDoListsDTO(1L, "General Tasks", entryList);
		ToDoListsDTO list2 = new ToDoListsDTO(2L, "QA Project 2 tasks", entryList2);
		expectedResult.add(list1);
		expectedResult.add(list2);
		// request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/lists/readAll");
		// expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// action
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void readOne() throws Exception {
		// resources
		entryList2.add(entry1);
		entryList2.add(entry2);
		ToDoListsDTO expectedResult = new ToDoListsDTO(2L, "QA Project 2 tasks", entryList2);
		// request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/lists/read/" + ID);
		// expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// action
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void create() throws Exception {
		ToDoListsDomain contentBody = new ToDoListsDomain("Shopping List", null);
		ToDoListsDTO expectedResult = mapToDTO(contentBody);
		expectedResult.setId(3L);
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "http://localhost:8080/lists/create").contentType(MediaType.APPLICATION_JSON)
				.content(jsonifier.writeValueAsString(contentBody)).accept(MediaType.APPLICATION_JSON);
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void update() throws Exception {
		// resources
		ToDoListsDomain contentBody = new ToDoListsDomain(1L, "list 1", null);
		ToDoListsDomain updatedBody = new ToDoListsDomain(contentBody.getTitle(), contentBody.getToDoList());
		updatedBody.setId(1L);
		ToDoListsDTO expectedResult = mapToDTO(updatedBody);
		// request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "http://localhost:8080/lists/update/" + 1)
				.contentType(MediaType.APPLICATION_JSON).content(jsonifier.writeValueAsString(contentBody))
				.accept(MediaType.APPLICATION_JSON);
		// expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isAccepted();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// action
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void delete() throws Exception {
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"http://localhost:8080/lists/delete/" + 2);
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}

	// delete if it doesn't exist
	@Test
	public void delete2() throws Exception {
		// resources
		Long nonExistantID = 50L;
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"http://localhost:8080/lists/delete/" + nonExistantID);
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isInternalServerError();
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
}
