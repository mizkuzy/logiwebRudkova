package ru.tsystems.logiweb.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tsystems.logiweb.entities.Good;
import ru.tsystems.logiweb.service.API.GoodService;

@Controller
public class ManagerController {

    private Logger logger = Logger.getLogger(ManagerController.class);

    @Autowired
    private GoodService goodService;

    /**
     * Dispatch to specified jsp page
     *
     * @param model
     * @return specified jsp page
     */
    @RequestMapping(value = "new_request")
    public String newRequest(Model model) {
        return "new_request";
    }

    //TODO Герман. Можно ли просто везде метод пост использовать?
    @RequestMapping(value = "addNewRequest", method = RequestMethod.POST)
    public String addNewRequest(@RequestParam(value = "goods_name") String goodsName,
                                @RequestParam(value = "mass") Integer mass,
                                @RequestParam(value = "city1") String city1,
                                @RequestParam(value = "city1") String city2) {


        Good good = new Good(goodsName,mass);
        goodService.create(good);

        logger.info("Good is created " );
        //TODO Герман. Как сделать следующее: Нас возвращает на главную страницу, но поверх неё написано сообщение:
        //                                     Операция проведена успешно. Нажимаем ОК и окошко исчезает
        return "main_manager";
    }
}
