package ru.tsystems.logiweb.controllers;

//todo удалить requests со статусом finished и определённым routlabel после выполнения заказа
// todo заменить одинарный амперсанд на двойной везде и или тоже

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import ru.tsystems.logiweb.entities.*;
import ru.tsystems.logiweb.entities.statusesAndStates.POSITION;
import ru.tsystems.logiweb.service.API.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
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
    @Autowired
    EmployeeService employeeService;
    @Autowired
    RoutLabelService routLabelService;

    /**
     * Dispatch to the main jsp page for manager.
     *
     * @return specified jsp page
     */
    @RequestMapping(value = "main_manager")
    public String homeManager() {
        //todo здесь потом парсить по ролям кого куда!

        return "manager/main_manager";
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

        //todo если в названии товара пробел, то вылетит ошибка

        //TODO хорошо бы по-человечески сделать страничку с городами
        //TODO доделать страничку. пользователь может не выбрать что-то. обязательно сделать проверку на заполнение и выбор кадждого поля.
        // Плюс проблема с выпадающим списком
        return "manager/new_request";
    }

    /**
     * Adds new request.
     *
     * @param goodsName
     * @param mass
     * @param city1
     * @param city2
     * @return
     */
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

        return "manager/main_manager";
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
        return "manager/current_requests";
    }

    /**
     * Finds and Sets choosed requests , creates new order, counts order's mass, finds appropriate vans and drivers
     *
     * @param httpRequest
     * @param currentRoutLabel
     * @return specified jsp page
     */
    @RequestMapping(value = "create_order")
    public String createOrder(Model model, HttpServletRequest httpRequest,
                              @RequestParam(value = "currentRoutLabel") String currentRoutLabel) {

        logger.info("Picking " + currentRoutLabel + " requests");
//todo сделать защиту от дабл клика

        List<Request> requests = new ArrayList<>();
        httpRequest.getSession().setAttribute("currentRoutLabel", currentRoutLabel);

        switch (currentRoutLabel) {
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
        httpRequest.getSession().setAttribute("appropriateVans", appropriateVans);

        //TODO не учитывается время, которое водитель потратит на поездку в спб в случае, если конечный пункт не СПБ,
        // а начальный у нас должен быть СПБ, что кстати тоже не учитывается, если нач. точка не спб. тут тупо считаются расстояния между пунктами
        int totalRequestAmount = requestService.getTotalRequestsAmount(requests);

        //TODO пока не помню зачем я добавила DriverStatus(rest,work). на текущий момент обошлась DriverState(resr,work,drive)
        //TODO при подсчёте рабочих часов не уxитывается, если заказ совпадёт на переход с месяца на месяц
        List appropriateDrivers = driverService.getAppropriateDrivers(totalRequestAmount);
        httpRequest.getSession().setAttribute("appropriateDrivers", appropriateDrivers);

        int maxCheckboxSelections = vanService.getDriversCapacity(currentRoutLabel) + 1;//+1 means 1 van
        /*model.addAttribute("maxCheckboxSelections", maxCheckboxSelections);*/
        httpRequest.getSession().setAttribute("maxCheckboxSelections", maxCheckboxSelections);
        return "manager/create_order";
    }

    /**
     * Changes request status to FINISHED, Sets choosed drivers and van to order
     *
     * @param request
     * @return
     */
    @RequestMapping(value = "save_order")
    public String saveOrder(HttpServletRequest request) {
        //Changes request status to FINISHED
        requestService.changeRequestsStatuses((String) request.getSession().getAttribute("currentRoutLabel"));

        //Sets choosed van to order
        String[] selectedVans = request.getParameterValues("selectedVan");
        logger.info("selectedVans.length=" + selectedVans.length);
        int idVan = Integer.valueOf(selectedVans[0]) - 1;
        logger.info(idVan);
        Van van = vanService.getSelectedVan((List<Van>) request.getSession().getAttribute("appropriateVans"), idVan);
        logger.info("Choosed this " + van);
        vanService.changeVanStatus(van);
        Order order = (Order) request.getSession().getAttribute("order");
        orderService.setVanToOrder(van, order);
        logger.info("Van is setted to order");

        //Sets choosed drivers to order
        String[] selectedDriversID = request.getParameterValues("selectedDriver");
        logger.info("selectedDrivers.length=" + selectedDriversID.length);
        List<Driver> selectedDrivers = driverService.getSelectedDrivers((List<Driver>) request.getSession().getAttribute("appropriateDrivers"),
                selectedDriversID);
        logger.debug("Drivers selected:");
        for (Driver d :
                selectedDrivers) {
            logger.debug(d);
        }
        //если водителю назначена заявка, то статус меняется на busy. на рабочие часы это не влияет
        driverService.changeDriversStatuses(selectedDrivers);
        orderService.setDriversToOrder(selectedDrivers, order);

        for (Driver d :
                order.getDrivers()) {
            logger.debug(d);
        }

        return "manager/main_manager";
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

        //TODO Должна быть кнопка посмотреть выполненные заказы (ordersDONE) в orders_list.jsp

        return "manager/orders_list";
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

        //todo добавить запрет на редактирование и удаление, если статус BUSY
        return "manager/vans";
    }

    /**
     * Gets selected van from the DB.
     *
     * @param idVanStr
     * @return specified jsp page
     */
    @RequestMapping(value = "getVanForEdit")
    public String getVanForEdit(@RequestParam(value = "selectedVan") String idVanStr, HttpServletRequest request) {

        Van van = vanService.read(Integer.valueOf(idVanStr));
        request.getSession().setAttribute("selectedVan", van);
        return "manager/editVan";
    }

    /**
     * Sets new parameters to van and updates it.
     *
     * @param vanNumber
     * @param driversAmount
     * @param capacity
     * @param request
     * @return main jsp page
     */
    @RequestMapping(value = "editVan")
    public String editVan(@RequestParam(value = "vanNumber") String vanNumber,
                          @RequestParam(value = "driversAmount") String driversAmount,
                          @RequestParam(value = "capacity") String capacity,
                          HttpServletRequest request) {

        Van van = (Van) request.getSession().getAttribute("selectedVan");
        //todo проверять номер в соответствии с паттерном
        van.setVanNumber(vanNumber);
        van.setDriversAmount(Integer.valueOf(driversAmount));
        van.setCapacity(Integer.valueOf(capacity));

        vanService.update(van);

        return "manager/main_manager";
    }

    /**
     * Deletes selected van.
     *
     * @param request
     * @return specified jsp page
     */
    @RequestMapping(value = "deleteVan")
    public String deleteVan(@RequestParam(value = "selectedVan") String idVanStr,
                            HttpServletRequest request) {

        Van van = vanService.read(Integer.valueOf(idVanStr));
        vanService.delete(van);

        return "manager/main_manager";
    }

    /**
     * Dispatches to specified jsp page where you can type new van's date.
     *
     * @return specified jsp page
     */
    @RequestMapping(value = "createVan")
    public String createVan() {
        //todo т.к. у нас есть зависимость routLabel от driversCapacity, то надо бы с этим что-то сделать
        return "manager/createVan";
    }

    /**
     * Adds new van to DB.
     *
     * @param vanNumber
     * @param driversAmountStr
     * @param capacityStr
     * @return main jsp page
     */
    @RequestMapping(value = "addVan")
    public String addVan(@RequestParam(value = "vanNumber") String vanNumber,
                         @RequestParam(value = "driversAmount") String driversAmountStr,
                         @RequestParam(value = "capacity") String capacityStr,
                         @RequestParam(value = "routLabel") String routLabel) {

        int driversAmount = Integer.valueOf(driversAmountStr);
        RouteLabel routeLabel = routLabelService.getByName(routLabel);

        Van van = new Van(vanNumber, driversAmount, Integer.valueOf(capacityStr), routeLabel);
        vanService.create(van);

        return "manager/main_manager";
    }

    /**
     * Shows all drivers.
     *
     * @param request
     * @return specified jsp page
     */
    @RequestMapping(value = "drivers")
    public String showDriversList(HttpServletRequest request) {


        ArrayList<Driver> drivers = (ArrayList<Driver>) driverService.getAll();

        request.getSession().setAttribute("drivers", drivers);

        //todo добавить запрет на редактирование и удаление, если статус BUSY
        return "manager/drivers";
    }

    /**
     * Gets selected driver from the DB.
     *
     * @param idDriverStr
     * @param request
     * @return specified jsp page
     */
    @RequestMapping(value = "getDriverForEdit")
    public String getDriverForEdit(@RequestParam(value = "selectedDriver") String idDriverStr, HttpServletRequest request) {
        Driver driver = driverService.read(Integer.valueOf(idDriverStr));
        request.getSession().setAttribute("selectedDriver", driver);

        return "manager/editDriver";
    }

    /**
     * Sets new parameters to driver and updates it.
     *
     * @param driverName
     * @param driverSurname
     * @param request
     * @return main jsp page
     */
    @RequestMapping(value = "editDriver")
    public String editDriver(@RequestParam(value = "driverName") String driverName,
                             @RequestParam(value = "driverSurname") String driverSurname,
                             HttpServletRequest request) {

        Driver driver = (Driver) request.getSession().getAttribute("selectedDriver");
        driver.setName(driverName);
        driver.setSurname(driverSurname);

        driverService.update(driver);

        return "manager/main_manager";
    }

    /**
     * Deletes selected driver.
     *
     * @param request
     * @return specified jsp page
     */
    @RequestMapping(value = "deleteDriver")
    public String deleteDriver(HttpServletRequest request) {

        Driver driver = (Driver) request.getSession().getAttribute("selectedDriver");

        logger.info(driver.getName());

        //сначала нужно удалить ссылку на этого водителя в таблице Employee,
        // затем запись в таблице Employee, а затем запись в таблице drivers
        driverService.delete(driver);

        return "manager/main_manager";
    }

    /**
     * Dispatches to specified jsp page where you can type new driver's date.
     *
     * @return specified jsp page
     */
    @RequestMapping(value = "createDriver")
    public String createDriver() {

        return "manager/createDriver";
    }

    /**
     * Adds new driver to DB.
     *
     * @param driverName
     * @param driverSurname
     * @param email
     * @param password
     * @return main jsp page
     */
    @RequestMapping(value = "addDriver")
    public String addDriver(@RequestParam(value = "driverName") String driverName,
                            @RequestParam(value = "driverSurname") String driverSurname,
                            @RequestParam(value = "email") String email,
                            @RequestParam(value = "password") String password) {

        Employee employee = new Employee(email, password, POSITION.DRIVER);
        Driver driver = new Driver(driverName, driverSurname);

        employee.setDriverFK(driver);

        driverService.create(driver);
        employee.setPersonalNumber(driver.getId());
        employeeService.create(employee);

        return "manager/main_manager";
    }
}