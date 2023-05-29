package com.project.employeeSystem.service;

import com.project.employeeSystem.model.Employee;
import com.project.employeeSystem.repository.EmployeeRepository;
import org.springframework.data.relational.core.sql.In;

import java.util.List;

public interface IEmployeeService {
    void insertEmployee (Employee employee);

    void deleteEmployee(Integer id);

    void updateEmployee(Employee employee);

    List<Employee> getAllEmployee();

    List<Employee> selectLikeEmployee(String search);
}
