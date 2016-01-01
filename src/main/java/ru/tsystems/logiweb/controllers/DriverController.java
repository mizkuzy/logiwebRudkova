package ru.tsystems.logiweb.controllers;

import org.apache.log4j.Logger;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;

import javax.servlet.http.HttpServletRequest;

@Controller
public class DriverController {

    private Logger logger = Logger.getLogger(DriverController.class);

    /**
     * Dispatch to the page main_driver.jsp.
     *
     * @return main_driver.jsp
     */
    @RequestMapping(value = "main_driver")
    public String mainDriver(Model model, HttpServletRequest request) {

        logger.info(request.getSession().getAttribute("currentUser"));
        User user = (User) request.getSession().getAttribute("currentUser");
        request.getSession().setAttribute("username", user.getUsername());

        return "driver/main_driver";
    }

}
