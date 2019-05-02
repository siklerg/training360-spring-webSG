package springmvc.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.hasItem;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.model;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.view;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.ContextHierarchy;
import org.springframework.test.context.jdbc.Sql;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.web.context.WebApplicationContext;
import static org.hamcrest.Matchers.hasProperty;

import springmvc.backend.BackendConfig;
import springmvc.backend.EmployeeService;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({@ContextConfiguration(classes = BackendConfig.class), @ContextConfiguration(classes = Webconfig.class)})
@WebAppConfiguration
@Sql(statements = "delete from employees")
public class EmployeesIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	@Autowired
	private EmployeeService employeeService;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testSavethenListEmployees() throws Exception {
		mockMvc.perform(post("/").param("name", "John Doe"));
		
		mockMvc.perform(get("/"))
		.andExpect(status().isOk())
		.andExpect(view().name("index"))
		.andExpect(model().attribute("employees", hasItem(hasProperty("name", equalTo("John Doe")))))
		.andExpect(content().string(containsString("John Doe")));
	}
	
}
