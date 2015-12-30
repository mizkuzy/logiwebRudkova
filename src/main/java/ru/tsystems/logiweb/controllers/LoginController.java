package ru.tsystems.logiweb.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import ru.tsystems.logiweb.entities.Employee;
import ru.tsystems.logiweb.entities.statusesAndStates.POSITION;
import ru.tsystems.logiweb.service.API.EmployeeService;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private EmployeeService employeeService;

    /**
     * Dispatch to specified jsp page
     *
     * @param model
     * @return specified jsp page
     */
    @RequestMapping(value = "login")
    public String login(Model model) {
        model.addAttribute("test", "test");
        return "login";
    }

    /**
     * Decide what page app should show manager or driver or wrong authentication
     *
     * @param model
     * @return specified jsp page
     */
    @RequestMapping(value = "loginDispatcher", method = RequestMethod.POST)
    public String loginDispatcher(Model model, HttpServletRequest request) {
        boolean validationIsOK = false;
        String email = request.getParameter("user");
        String password = request.getParameter("pswd");

        //todo убрать обработку исключений в контроллере. создать свои исключения, и обрабатывать ихх фильтром
        try {
            validationIsOK = employeeService.checkEmailAndPassword(email, password);
            if (validationIsOK) {
                Employee employee = employeeService.getEntityByEmail(email);
                if (employee.getPosition().equals(POSITION.DRIVER)) {
                    return "driver/main_driver";
                } else return "manager/main_manager";
            } else return "login";
        } catch (NoResultException e) {
            logger.info("Invalid login or password in loginDispatcher()");
            throw new NoResultException();
        }
    }
}
