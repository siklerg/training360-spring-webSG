package springmvc.controller;

import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.util.List;
import java.util.stream.Collectors;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.web.servlet.ModelAndView;

import springmvc.backend.EmployeeService;
import springmvc.model.Employee;

@RunWith(MockitoJUnitRunner.class)
public class EmployeesControllerTest {

	@Mock
	private EmployeeService employeeService;
	
	@InjectMocks
	private EmployeesController employeesController;
	
	@Test
	public void testListEmployees() {
		when(employeeService.listEmployees()).thenReturn(List.of(new Employee("John Doe")));
		
		ModelAndView modelAndView = employeesController.listEmployees();
		assertEquals("index", modelAndView.getViewName());
		assertEquals(List.of("John Doe"), ((List<Employee>) modelAndView.getModel().get("employees")).stream().map(Employee::getName).collect(Collectors.toList()));
	}
}
