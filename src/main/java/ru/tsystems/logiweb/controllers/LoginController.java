package ru.tsystems.logiweb.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tsystems.logiweb.entities.Employee;
import ru.tsystems.logiweb.entities.statusesAndStates.POSITION;
import ru.tsystems.logiweb.service.API.EmployeeService;
import ru.tsystems.logiweb.service.IMPL.UserService;

import javax.persistence.NoResultException;
import javax.servlet.http.HttpServletRequest;

@Controller
public class LoginController {

    private Logger logger = Logger.getLogger(LoginController.class);

    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private UserService userService;

    /**
     * Dispatches to  login.jsp.
     *
     * @param model
     * @return login.jsp
     */
    @RequestMapping(value = "login")
    public String login(Model model) {
        return "login";
    }

    public String index(Model model){
        model.addAttribute("login","hi");
        return "index";
    }

    @RequestMapping(value = "/logout")
    public String logout() {
        return "login";
    }

    @RequestMapping(value = "/denied")
    public String denied(@RequestParam(value = "j_username") String usesrname,
                         @RequestParam(value = "j_password") String password,
                         Model model) {
        logger.info("Tried to log in");
        model.addAttribute("error_msg", "Access denied!");
        return "login";
    }

    /**
     * Decide what page app should show manager or driver or wrong authentication
     */
    @RequestMapping(value = "loginDispatcher", method = RequestMethod.POST)
    public String loginDispatcher(HttpServletRequest request) {

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = (User) userService.loadUserByUsername(user.getUsername());

        request.getSession().setAttribute("currentUser", currentUser);
        if (currentUser.getAuthorities().contains("ROLE_MANAGER")) {
            return "main_manager";
        } else if (currentUser.getAuthorities().contains("ROLE_DRIVER")) {
            return "main_driver";
        }
        return "login";
    }
}
