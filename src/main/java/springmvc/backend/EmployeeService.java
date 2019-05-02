package springmvc.backend;

import java.util.List;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import springmvc.controller.EmployeeNotFoundException;
import springmvc.model.Employee;

@Service
public class EmployeeService {

	private EmployeeRepository employeeRepository;

	public EmployeeService(EmployeeRepository employeeRepository) {
		this.employeeRepository = employeeRepository;
	}

	public void saveEmployee(String name) {
		employeeRepository.save(new Employee(name));
	}

	public List<Employee> listEmployees() {
		return employeeRepository.findAll();
	}

	public Employee findEmployeeById(long id) {
		return employeeRepository.findById(id).orElseThrow(() -> new EmployeeNotFoundException());
	}

	@Transactional
	public void updateEmployee(Employee employee) {
		Employee employeeToModify = employeeRepository.getOne(employee.getId());
		employeeToModify.setName(employee.getName());
	}
}
