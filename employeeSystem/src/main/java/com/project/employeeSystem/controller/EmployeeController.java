package com.project.employeeSystem.controller;

import com.project.employeeSystem.model.Employee;
import com.project.employeeSystem.repository.EmployeeRepository;
import com.project.employeeSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;


@RestController
@RequestMapping(value = "/employee")
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private EmployeeRepository employeeRepository;

    @GetMapping(value = "/select")
    public ModelAndView selectLike(String search){
        ModelAndView view = new ModelAndView("index::employeeTable");
        view.addObject("employee_list",employeeService.selectLikeEmployee(search));
        return view;
    }

    @PostMapping(value = "/delete")
    public ModelAndView deleteEmployee(@RequestParam Integer id){
        employeeService.deleteEmployee(id);
        ModelAndView view = new ModelAndView("index::employeeTable");
        view.addObject("employee_list",employeeService.getAllEmployee());
        return view;
    }

    @PostMapping(value = "/insert")
    @ResponseBody
    public ModelAndView insertEmployee(Employee employee){
        System.out.println(employee);
        employeeService.insertEmployee(employee);

        ModelAndView view = new ModelAndView("index::employeeTable");
        view.addObject("employee_list",employeeService.getAllEmployee());
        return view;
    }

    @PostMapping(value = "/update")
    @ResponseBody
    public ModelAndView updateEmployee(Employee employee){
        System.out.println(employee);
        employeeService.updateEmployee(employee);

        ModelAndView view = new ModelAndView("index::employeeTable");
        view.addObject("employee_list",employeeService.getAllEmployee());
        return view;
    }
}
