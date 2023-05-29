package com.project.employeeSystem.service;

import com.project.employeeSystem.model.Employee;
import com.project.employeeSystem.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.ExampleMatcher;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class EmployeeService implements IEmployeeService{
    @Autowired
    private EmployeeRepository employeeRepository;
    @Override
    public void insertEmployee(Employee employee) {
        employeeRepository.save(employee);
    }

    @Override
    public void deleteEmployee(Integer id) {
        employeeRepository.deleteById(id);
    }

    @Override
    public void updateEmployee(Employee employee) {
        employeeRepository.saveAndFlush(employee);
    }

    @Override
    public List<Employee> getAllEmployee() {
        return employeeRepository.findAll();
    }

    @Override
    public List<Employee> selectLikeEmployee(String search) {
        List<Employee> searchList = new ArrayList<>();
        Employee employee = new Employee();
        employee.setFirstName(search);
        employee.setLastName(search);
        employee.setEmail(search);
        employee.setPhoneNumber(search);
        try {
            Integer id = Integer.parseInt(search);
            Optional<Employee> optionalEmployee = employeeRepository.findById(id);
            if (!optionalEmployee.isPresent()){
                searchList = selectVague(employee);
            }else{
                searchList.add(optionalEmployee.get());
            }
        }catch (NumberFormatException e){
            searchList = selectVague(employee);
        }
        return searchList;
    }

    private List<Employee> selectVague(Employee employee){
        List<Employee> list = null;
        ExampleMatcher matcher = ExampleMatcher.matchingAny()
                .withMatcher("firstName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("lastName", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("email", ExampleMatcher.GenericPropertyMatchers.contains())
                .withMatcher("phoneNumber", ExampleMatcher.GenericPropertyMatchers.contains())
                .withIgnoreCase("id");
        Example<Employee> example = Example.of(employee, matcher);
        list = employeeRepository.findAll(example);
        return list;

    }
}
