package com.project.employeeSystem.controller;

import com.project.employeeSystem.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
@RestController
public class indexController {

    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/index")
    public ModelAndView toIndex(){
        ModelAndView view = new ModelAndView("index");
        view.addObject("employee_list", employeeService.getAllEmployee());
        return view;
    }
}
