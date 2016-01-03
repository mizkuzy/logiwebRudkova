package ru.tsystems.logiweb.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.Employee;
import ru.tsystems.logiweb.service.API.DriverService;
import ru.tsystems.logiweb.service.API.EmployeeService;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DriverController {

    @Autowired
    private EmployeeService employeeService;

    /**
     * Dispatch to the page main_driver.jsp.
     *
     * @return main_driver.jsp
     */
    @RequestMapping(value = "main_driver")
    public String mainDriver(Model model, HttpServletRequest request) {

        User user = (User) request.getSession().getAttribute("currentUser");

        Employee employee = employeeService.getEntityByEmail(user.getUsername());
        Driver driver = employee.getDriverFK();

        String driverNameSurname = driver.getName() + " " + driver.getSurname();
        model.addAttribute("driverNameSurname", driverNameSurname);
        model.addAttribute("driverPersonalNumber", employee.getPersonalNumber());
        model.addAttribute("currentOrder", driver.getCurrentOrder());

        return "driver/main_driver";
    }
}
