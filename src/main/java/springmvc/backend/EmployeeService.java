package springmvc.backend;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

import org.springframework.stereotype.Service;

import springmvc.controller.EmployeeNotFoundException;
import springmvc.model.Employee;

@Service
public class EmployeeService {

	private AtomicInteger id = new AtomicInteger();

	private List<Employee> employees = Collections
			.synchronizedList(new ArrayList<>(List.of
					(new Employee(id.incrementAndGet(), "Jack Doe"), new Employee(id.incrementAndGet(), "Jane Doe"))));

	public void saveEmployee(String name) {
		employees.add(new Employee(id.incrementAndGet(), name));
	}

	public List<Employee> listEmployees() {
		return new ArrayList<>(employees);
	}

	public Employee findEmployeeById(long id) {
		return employees.stream().filter(e -> id == e.getId()).findFirst()
				.orElseThrow(() -> new EmployeeNotFoundException());
	}

	public void updateEmployee(Employee employee) {
		Employee employeeToModify = findEmployeeById(employee.getId());
		employeeToModify.setName(employee.getName());
	}

	public void reset() {
		id.set(0);
		employees.clear();
	}

}
