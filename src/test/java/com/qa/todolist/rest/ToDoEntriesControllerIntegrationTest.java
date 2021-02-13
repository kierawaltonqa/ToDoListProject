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
import com.qa.todolist.persistence.domain.ToDoEntriesDomain;
import com.qa.todolist.persistence.dto.ToDoEntriesDTO;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
@Sql(scripts = { "classpath:schema-test.sql",
		"classpath:data-test.sql" }, executionPhase = ExecutionPhase.BEFORE_TEST_METHOD)
@ActiveProfiles(profiles = "test")
public class ToDoEntriesControllerIntegrationTest {

	@Autowired
	private MockMvc mock;
	@Autowired
	private ModelMapper mapper;
	@Autowired
	private ObjectMapper jsonifier;

	Date date1 = Date.valueOf("2021-02-06");
	Date date2 = Date.valueOf("2021-02-12");
	Date date3 = Date.valueOf("2021-02-07");

	private final int ID = 1;

	private final ToDoEntriesDTO entry1 = new ToDoEntriesDTO(1L, "create back end", date1, true);
	private final ToDoEntriesDTO entry2 = new ToDoEntriesDTO(2L, "testing for back end", date2, false);
	private final ToDoEntriesDTO entry3 = new ToDoEntriesDTO(3L, "buy dad a birthday present", date3, true);

	private ToDoEntriesDTO mapToDTO(ToDoEntriesDomain model) {
		return this.mapper.map(model, ToDoEntriesDTO.class);
	}

	@Test
	public void readAll() throws Exception {
		// resources
		List<ToDoEntriesDTO> expectedResult = new ArrayList<>();
		expectedResult.add(entry1);
		expectedResult.add(entry2);
		expectedResult.add(entry3);
		// request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/entries/readAll");
		// expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// action
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void readOne() throws Exception {
		ToDoEntriesDTO expectedResult = entry1;
		// request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.GET,
				"http://localhost:8080/entries/read/" + ID);
		// expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isOk();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// action
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);

	}

	@Test
	public void create() throws Exception {
		// resources
		ToDoEntriesDomain contentBody = new ToDoEntriesDomain("complete front end", date1, false, null);
		ToDoEntriesDTO expectedResult = mapToDTO(contentBody);
		expectedResult.setId(4L);
		// request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.POST, "http://localhost:8080/entries/create")
				.contentType(MediaType.APPLICATION_JSON).content(jsonifier.writeValueAsString(contentBody))
				.accept(MediaType.APPLICATION_JSON);

		// expectations
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isCreated();
		ResultMatcher matchContent = MockMvcResultMatchers.content().json(jsonifier.writeValueAsString(expectedResult));
		// actions
		this.mock.perform(mockRequest).andExpect(matchStatus).andExpect(matchContent);
	}

	@Test
	public void update() throws Exception {
		// resources
		ToDoEntriesDomain contentBody = new ToDoEntriesDomain(1L, "create back end", date1, true, null);
		ToDoEntriesDomain updatedBody = new ToDoEntriesDomain(contentBody.getDescription(), contentBody.getDueDate(),
				contentBody.isCompleted(), contentBody.getMyList());
		updatedBody.setId(1L);
		ToDoEntriesDTO expectedResult = mapToDTO(updatedBody);
		// request
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders
				.request(HttpMethod.PUT, "http://localhost:8080/entries/update/" + ID)
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
				"http://localhost:8080/entries/delete/" + 2);
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isNoContent();
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}

	// delete if it doesn't exist
	@Test
	public void delete2() throws Exception {
		// resources
		Long nonExistantID = 50L;
		MockHttpServletRequestBuilder mockRequest = MockMvcRequestBuilders.request(HttpMethod.DELETE,
				"http://localhost:8080/entries/delete/" + nonExistantID);
		ResultMatcher matchStatus = MockMvcResultMatchers.status().isInternalServerError();
		this.mock.perform(mockRequest).andExpect(matchStatus);
	}
}
