package com.gilbert.test.accessingdatamongodb;

import static org.assertj.core.api.Assertions.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Example;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

@AutoConfigureMockMvc
@SpringBootTest
public class AccessingDataMongodbControllerTests {

	@Autowired
	EmployeeRepository repository;

	@Autowired
	private MockMvc mockMvc;

	@BeforeEach
	public void setUp() {

		repository.deleteAll();
		repository.save(new Employee("Dave", "Matthews"));
		repository.save(new Employee("Oliver August", "Matthews"));
		repository.save(new Employee("Carter", "Beauford"));
		repository.save(new Employee("Alice", "Smith"));
		repository.save(new Employee("Bob", "Smith"));
	}

	@Test
	public void setsIdOnSave() {

		Employee dave = repository.save(new Employee("Dave", "Matthews"));
		assertThat(dave.id).isNotNull();
	}

	@Test
	public void findsByLastName() {

		List<Employee> result = repository.findByLastName("Beauford");
		assertThat(result).hasSize(1).extracting("firstName").contains("Carter");
	}

	@Test
	public void findsByExample() {

		Employee probe = new Employee(null, "Matthews");
		List<Employee> result = repository.findAll(Example.of(probe));
		assertThat(result).hasSize(2).extracting("firstName").contains("Dave", "Oliver August");
	}

	@Test
	public void findByFirstName() throws Exception {
		this.mockMvc.perform(get("/mongodb/first")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Bob"))
				.andExpect(jsonPath("$.lastName").value("Smith"));
	}

	@Test
	public void findByLastNameTest() throws Exception {
		this.mockMvc.perform(get("/mongodb/last")).andDo(print()).
				andExpect(status().isOk())
				.andExpect(jsonPath("$.firstName").value("Alice"))
				.andExpect(jsonPath("$.lastName").value("Smith"));
	}
}
