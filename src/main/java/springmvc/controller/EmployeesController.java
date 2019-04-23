package springmvc.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import springmvc.backend.EmployeeService;
import springmvc.model.Employee;

@Controller
public class EmployeesController {

	private EmployeeService employeeService;

	public EmployeesController(EmployeeService employeeService) {
		this.employeeService = employeeService;
	}

	@ModelAttribute
	public Employee employee() {
		return new Employee();
	}

	@RequestMapping(value = "/", method = RequestMethod.GET)
	public ModelAndView listEmployees() {
		return new ModelAndView("index", "employees", employeeService.listEmployees());
	}

	@RequestMapping(value = "/", method = RequestMethod.POST)
	public String saveEmployee(@ModelAttribute Employee employee, RedirectAttributes redirectAttributes) {
		employeeService.saveEmployee(employee.getName());
		redirectAttributes.addFlashAttribute("message", "Employee has saved: " + employee.getName());
		return "redirect:/";
	}
}
