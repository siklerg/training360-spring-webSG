package springmvc.controller;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

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

import com.fasterxml.jackson.databind.ObjectMapper;

import springmvc.backend.BackendConfig;
import springmvc.model.Employee;


@RunWith(SpringJUnit4ClassRunner.class)
@ContextHierarchy({@ContextConfiguration(classes = BackendConfig.class), @ContextConfiguration(classes = Webconfig.class)})
@WebAppConfiguration
@Sql(statements = "delete from employees")
public class EmployeesRestIntegrationTest {

	@Autowired
	private WebApplicationContext webApplicationContext;
	
	private MockMvc mockMvc;
	
	@Before
	public void setup() {
		mockMvc = MockMvcBuilders.webAppContextSetup(webApplicationContext).build();
	}
	
	@Test
	public void testSavethenListEmployees() throws Exception {
		String json = new ObjectMapper().writeValueAsString(new Employee("Johnny Doe"));
		System.out.println(json);
		mockMvc.perform(post("/api/employees")
		.header("Content-Type", "application/json")
		.content(json));
		
		mockMvc.perform(get("/api/employees"))
		.andExpect(status().isOk())
		.andDo(r ->System.out.println(r.getResponse().getContentAsString()))
		.andExpect(jsonPath("$[0].name", equalTo("Johnny Doe")));
		
	}
}
