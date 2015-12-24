package ru.tsystems.logiweb.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tsystems.logiweb.entities.*;
import ru.tsystems.logiweb.service.API.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
public class ManagerController {

    private Logger logger = Logger.getLogger(ManagerController.class);

    @Autowired
    private GoodService goodService;
    @Autowired
    private RoutService routService;
    @Autowired
    private RequestService requestService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private VanService vanService;
    @Autowired
    DriverService driverService;

    /**
     * Dispatch to the main jsp page for manager.
     *
     * @return specified jsp page
     */
    @RequestMapping(value = "main_manager")
    public String homeManager() {

        return "main_manager";
    }

    /**
     * Dispatch to jsp page where you can type good dates.
     *
     * @param model
     * @return specified jsp page
     */
    @RequestMapping(value = "new_request")
    public String newRequest(Model model) {
        //TODO доделать страничку. пользователь может не выбрать что-то. обязательно сделать проверку на заполнение и выбор кадждого поля. Плюс проблема с выпадающим списком
        return "new_request";
    }

    //TODO Герман. Можно ли просто везде метод пост использовать?
    @RequestMapping(value = "addNewRequest", method = RequestMethod.POST)
    public String addNewRequest(@RequestParam(value = "goods_name") String goodsName,
                                @RequestParam(value = "mass") Integer mass,
                                @RequestParam(value = "city1") String city1,
                                @RequestParam(value = "city2") String city2) {

        int goodNumber = goodService.addNewGood(goodsName, mass);

        Rout rout = routService.getByCities(city1, city2);

        requestService.addNewRequest(goodService.read(goodNumber), rout);

        //TODO Герман. Как сделать следующее: Нас возвращает на главную страницу, но поверх неё написано сообщение:
        //                                     Операция проведена успешно. Нажимаем ОК и окошко исчезает
        return "main_manager";
    }

    /**
     * Counts amount of Special Routs (by Route Labels) in Requests. Sort these requests to lists.
     *
     * @param request
     * @return specified jsp page
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "pick_up_requests")
    public String pickUpRequests(HttpServletRequest request) throws ServletException, IOException {
//TODO Герман. Надо ли что-то делать со всеми исключениями?
        List<Request> requestsWithYellowRout = requestService.findRequestsWithSpecialRout("yellow");
        List<Request> requestsWithGreenRout = requestService.findRequestsWithSpecialRout("green");
        List<Request> requestsWithPurpleRout = requestService.findRequestsWithSpecialRout("purple");
        List<Request> requestsWithBlueRout = requestService.findRequestsWithSpecialRout("blue");

        request.getSession().setAttribute("yellowRoutRequests", requestsWithYellowRout);
        request.getSession().setAttribute("greenRoutRequests", requestsWithGreenRout);
        request.getSession().setAttribute("purpleRoutRequests", requestsWithPurpleRout);
        request.getSession().setAttribute("blueRoutRequests", requestsWithBlueRout);

        return "current_requests";
    }

    @RequestMapping(value = "create_order")
    public String createOrder(HttpServletRequest httpRequest, @RequestParam(value = "htmlFormName") String htmlFormName) {
        logger.info("Picking " + htmlFormName + " requests");

        String routLabel = htmlFormName;

        List<Request> requests = new ArrayList<Request>();

        switch (routLabel) {
            case "yellow":
                requests = (List<Request>) httpRequest.getSession().getAttribute("yellowRoutRequests");
                break;
            case "green":
                requests = (List<Request>) httpRequest.getSession().getAttribute("greenRoutRequests");
                break;
            case "purple":
                requests = (List<Request>) httpRequest.getSession().getAttribute("purpleRoutRequests");
                break;
            case "blue":
                requests = (List<Request>) httpRequest.getSession().getAttribute("blueRoutRequests");
                break;
        }

        Order order = orderService.addNewOrder(requests);
        httpRequest.getSession().setAttribute("order", order);

        List appropriateVans = vanService.getAppropriateVans(htmlFormName);
        httpRequest.getSession().setAttribute("appropriateVans", appropriateVans);

        int mass = orderService.countOrderMass(requests);
        httpRequest.getSession().setAttribute("mass", mass);
        return "create_order";
    }

    @RequestMapping(value = "save_order")
    public String saveOrder(HttpServletRequest request){
        String[] appropriateVans = request.getParameterValues("selectedVan");
        logger.info(appropriateVans.length);

        return "main_manager";
    }

    /**
     * Shows current orders and you can see finished orders.
     *
     * @param request
     * @return specified jsp page
     */
    @RequestMapping(value = "orders_list")
    public String showOrdersList(HttpServletRequest request) {

        List ordersPROCESS = orderService.getOrdersProcess();
        List ordersDONE = orderService.getOrdersDone();

        logger.info("ordersPROCESS.size()= " + ordersPROCESS.size());
        logger.info("ordersDONE.size()" + ordersDONE.size());

        request.getSession().setAttribute("ordersPROCESS", ordersPROCESS);

        request.getSession().setAttribute("ordersDONE", ordersDONE);

        //TODO    Должна быть кнопка посмотреть выполненные заказы (ordersDONE) в orders_list.jsp

        return "orders_list";
    }

    /**
     * Shows all vans.
     *
     * @param request
     * @return specified jsp page
     */
    @RequestMapping(value = "vans")
    public String showVansList(HttpServletRequest request) {

        ArrayList<Van> vansList = (ArrayList<Van>) vanService.getAll();

        request.getSession().setAttribute("vansList", vansList);

        return "vans";
    }

    /**
     * Shows all drivers
     *
     * @param request
     * @return specified jsp page
     */
    @RequestMapping(value = "drivers")
    public String showDriversList(HttpServletRequest request) {

        Date date = new Date();
        date.toString();
        ArrayList<Driver> drivers = (ArrayList<Driver>) driverService.getAll();

        request.getSession().setAttribute("drivers", drivers);

        return "drivers";
    }
}
