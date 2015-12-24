package ru.tsystems.logiweb.controllers;

//TODO как деплоить через мавен?
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

/**
 * Shows name of the project and button to start use it
 */
@Controller
public class HelloController {

    @RequestMapping(value = "/")
    public String mainPage(Model model){
        model.addAttribute("test", "test");
        return "index";
    }

}
