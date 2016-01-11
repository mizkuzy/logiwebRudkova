package ru.tsystems.logiweb.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import ru.tsystems.logiweb.service.API.DriverService;
import ru.tsystems.logiweb.service.API.EmployeeService;

/**
 * Shows name of the project and button to start use it
 */
@Controller
public class HelloController {

    @Autowired
    DriverService driverService;
    @Autowired
    EmployeeService employeeService;

    @RequestMapping(value = "/")
    public String mainPage() {

        return "index";
    }

}
