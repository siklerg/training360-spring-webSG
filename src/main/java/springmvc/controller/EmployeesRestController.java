package springmvc.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import springmvc.backend.EmployeeService;
import springmvc.model.Employee;

@RestController //minden metódus @ResponseBody annotációt kap ez által
@RequestMapping("/api")
public class EmployeesRestController {

	private EmployeeService employeeService;

	public EmployeesRestController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}
	
	@GetMapping("/employees")
	public List<Employee> employees(){
		return employeeService.listEmployees();
	}
	
	@GetMapping("/employees/{id}")
	public Employee findEmployeeById(@PathVariable("id") long id){
		return employeeService.findEmployeeById(id);
	}
	
	@PostMapping("/employees")
	public void saveEmployee(@RequestBody Employee employee) {
		employeeService.saveEmployee(employee.getName());
	}
	
}
