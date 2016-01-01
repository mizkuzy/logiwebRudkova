package ru.tsystems.logiweb.controllers;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class DriverController {

    private Logger logger = Logger.getLogger(DriverController.class);

    /**
     * Dispatch to the page main_driver.jsp.
     *
     * @return main_driver.jsp
     */
    @RequestMapping(value = "main_driver")
    public String mainDriver() {
        return "driver/main_driver";
    }

}
