package ru.tsystems.logiweb.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tsystems.logiweb.service.API.EmployeeService;
import ru.tsystems.logiweb.service.IMPL.UserService;

import javax.servlet.http.HttpServletRequest;
import java.util.Collection;

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
     * @return login.jsp
     */
    @RequestMapping(value = "login")
    public String login() {
        return "login";
    }

    //todo сделать так, чтобы первая страница эта открывалась
    public String index(Model model) {
        model.addAttribute("login", "hi");
        return "index";
    }

    // todo герман. разрыва сесси не происходит при логАуте, при нажатии на бэкспейс я могу зайти в приложение
    @RequestMapping(value = "/logout")
    public String logout() {

        return "login";
    }

    @RequestMapping(value = "/login_error")
    public String login_error(Model model) {
        //todo герман. работает неправильно
        logger.info("Tried to log in");
        model.addAttribute("error_msg", "Incorrect pair Username and Password." + "\n" +
                "Please enter correct username and password");
        return "login";
    }

    /**
     * Decide what page app should show manager or driver or wrong authentication
     */
    @RequestMapping(value = "/loginDispatcher")
    public String loginDispatcher(HttpServletRequest request,
                                  Model model) {

        //todo герман. достаточно ли такой авторизации?

        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User currentUser = (User) userService.loadUserByUsername(user.getUsername());
        request.getSession().setAttribute("currentUser", currentUser);

        GrantedAuthority roleManager = new SimpleGrantedAuthority(
                "ROLE_MANAGER");
        GrantedAuthority roleDriver = new SimpleGrantedAuthority(
                "ROLE_DRIVER");

        logger.info("currentUser " + request.getSession().getAttribute("currentUser"));
        request.getSession().setAttribute("username", currentUser);

        if (currentUser.getAuthorities().contains(roleManager)) {
            return "forward:/main_manager";
        } else if (currentUser.getAuthorities().contains(roleDriver)) {
            return "forward:/main_driver";
        }

        return "login";
    }
}
