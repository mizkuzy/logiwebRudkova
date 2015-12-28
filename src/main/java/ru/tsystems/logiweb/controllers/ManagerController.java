package ru.tsystems.logiweb.controllers;

//TODO убрать httpRequest и поменять на model, где это возможно

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
     * @return specified jsp page
     */
    @RequestMapping(value = "new_request")
    public String newRequest(HttpServletRequest request) {
        /*List<String> cities = routService.getCities();
        request.getSession().setAttribute("cities", cities);
        logger.info("Got cities list and set in session");*/

        //TODO хорошо бы по-человечески сделать страничку с городами
        //TODO доделать страничку. пользователь может не выбрать что-то. обязательно сделать проверку на заполнение и выбор кадждого поля.
        // Плюс проблема с выпадающим списком
        return "new_request";
    }

    //TODO Герман. Можно ли просто везде метод пост использовать?
    @RequestMapping(value = "addNewRequest", method = RequestMethod.POST)
    public String addNewRequest(@RequestParam(value = "goods_name") String goodsName,
                                @RequestParam(value = "mass") Integer mass,
                                @RequestParam(value = "city1") String city1,
                                @RequestParam(value = "city2") String city2) {

        int goodNumber = goodService.addNewGood(goodsName, mass);

        //TODO потестировать обработку несуществующего маршрута?
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

        //TODO сделать неактивной кнопку handle, если заявок нет
        return "current_requests";
    }

    /**
     * Sets choosed requests, van and drivers to order.
     *
     * @param httpRequest
     * @param currentRoutLabel
     * @return specified jsp page
     */
    @RequestMapping(value = "create_order")
    public String createOrder(Model model, HttpServletRequest httpRequest, @RequestParam(value = "currentRoutLabel") String currentRoutLabel) {
        logger.info("Picking " + currentRoutLabel + " requests");

        String routLabel = currentRoutLabel;

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

        int mass = orderService.countOrderMass(requests);
        model.addAttribute("mass", mass);

        List appropriateVans = vanService.getAppropriateVans(currentRoutLabel);
        model.addAttribute("appropriateVans", appropriateVans);

        //TODO не учитывается время, которое водитель потратит на поездку в спб в случае, если конечный пункт не СПБ,
        // а начальный у нас должен быть СПБ, что кстати тоже не учитывается, если нач. точка не спб. тут тупо считаются расстояния между пунктами
        int totalRequestAmount = requestService.getTotalRequestsAmount(requests);

        //TODO пока не помню зачем я добавила DriverStatus(rest,work). на текущий момент обошлась DriverState(resr,work,drive)
        //TODO при подсчёте рабочих часов не уxитывается, если заказ совпадёт на переход с месяца на месяц
        List appropriateDrivers = driverService.getAppropriateDrivers(totalRequestAmount);
        model.addAttribute("appropriateDrivers", appropriateDrivers);

        int maxCheckboxSelections = vanService.getDriversCapacity(currentRoutLabel) + 1;//+1 means 1 van
        /*model.addAttribute("maxCheckboxSelections", maxCheckboxSelections);*/
        httpRequest.getSession().setAttribute("maxCheckboxSelections", maxCheckboxSelections);
        return "create_order";
    }

    @RequestMapping(value = "save_order")
    public String saveOrder(HttpServletRequest request) {

        //поменять статусы реквестов и у воидтелей, и у фур, и добавить всем номера заказов
        String[] selectedVans = request.getParameterValues("selectedVan");
        logger.info("selectedVans.length=" + selectedVans.length);

        String[] selectedDrivers = request.getParameterValues("selectedDriver");
        logger.info("selectedDrivers.length=" + selectedDrivers.length);

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
