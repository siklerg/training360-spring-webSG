package springmvc.controller;

import java.util.List;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import springmvc.backend.EmployeeService;
import springmvc.model.Employee;

@Controller
public class EmployeesController {
	
	private EmployeeService employeeService;
	
	

	public EmployeesController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}



	@RequestMapping("/")
	public ModelAndView listEmployees() {
		return new ModelAndView("index", "employees", employeeService.listEmployees());
	}
}
