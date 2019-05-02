package springmvc.backend;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import springmvc.model.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {

	//nem Iterable-t, hanem listát ad vissza
}
