package springmvc.controller;

import java.util.Locale;

import javax.validation.Valid;

import org.springframework.context.MessageSource;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
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

	private MessageSource messageSource;
	
	public EmployeesController(EmployeeService employeeService, MessageSource messageSource) {
		super();
		this.employeeService = employeeService;
		this.messageSource = messageSource;
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
	public ModelAndView saveEmployee(@Valid Employee employee, BindingResult bindingresult,
			RedirectAttributes redirectAttributes, Locale locale) {

		if (bindingresult.hasErrors()) {
			String messageAlert = messageSource.getMessage("employee.emptyName",  new Object[] {}, locale);
			redirectAttributes.addFlashAttribute("messageAlert", messageAlert);
			return new ModelAndView("redirect:/", "employees", employeeService.listEmployees());
		}
		
		employeeService.saveEmployee(employee.getName());
		String message = messageSource.getMessage("employee.saved", new Object[] { employee.getName() }, locale);

		redirectAttributes.addFlashAttribute("message", message);
		return new ModelAndView("redirect:/");
	}
}
