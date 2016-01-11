package ru.tsystems.logiweb.controllers;
//todo герман. при первом запуске приложения и первой попытке входа вылетает ошибка про favicon.ico (см. скриншот)

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.logiweb.entities.*;
import ru.tsystems.logiweb.entities.statusesAndStates.*;
import ru.tsystems.logiweb.exceptions.CustomLogiwebException;
import ru.tsystems.logiweb.service.API.*;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Controller
@EnableScheduling
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
    private DriverService driverService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private RoutLabelService routLabelService;

    /**
     * Sets work hours to zero to all drivers on 1st day in 0:00 every month.
     */
    @Scheduled(cron = "0 0 0 1 * ?")
    public void setWorksHoursToZeroAllDrivers() {
        logger.info("Hi from cron!");
        driverService.setWorksHoursToZeroAllDrivers();//todo Why this method is executed 2 times?
    }

    /**
     * Dispatch to the main jsp page for manager.
     *
     * @return main_manager.jsp
     */
    @RequestMapping(value = "main_manager")
    public String homeManager() {
        return "manager/main_manager";
    }

    /**
     * Dispatch to jsp page where you can type good dates.
     *
     * @return new_request.jsp
     */
    @RequestMapping(value = "new_request")
    public String newRequest(Model model) {
        List<String> cities = routService.getCities();
        model.addAttribute("cities", cities);
        logger.info("Got cities list and set in session");
        model.addAttribute("spb", routService.getCitiesForSPb());
        model.addAttribute("velikyNovgorod", routService.getCitiesForVelikyNovgorod());
        model.addAttribute("pskov", routService.getCitiesForPskov());
        model.addAttribute("petrozavodsk", routService.getCitiesForPetrozavodsk());
       /* model.addAttribute("arhangelsk", routService.getCitiesForArhangelsk());
        //TODO фиг знает почему, но нижеследующие списки не отображаются/ возможно не хватает памяти
        model.addAttribute("vologda",routService.getCitiesForVologda());
        model.addAttribute("siktivkar",routService.getCitiesForSiktivkar());
        model.addAttribute("naryan-Mar",routService.getCitiesForNaryanMar());
        model.addAttribute("murmansk",routService.getCitiesForMurmansk());
        model.addAttribute("kaliningrad",routService.getCitiesForKaliningrad());
        model.addAttribute("cherepovec",routService.getCitiesForCherepovec());*/

        //todo если в названии товара пробел, то вылетит ошибка
        return "manager/new_request";
    }

    /*@RequestMapping(value = "get_corresponding_cities/{city1}")
    @ResponseBody
    public List<String> getCorrespondingCities(@PathVariable String city1) {
        logger.info("Chosen city" + city1);
        List<String> citiesTo = routService.getCitiesForSPb();

        return citiesTo;
    }*/

    /**
     * Adds new request.
     *
     * @param goodsName
     * @param mass
     * @param city1
     * @param city2
     * @return main_manager.jsp
     */
    @RequestMapping(value = "addNewRequest", method = RequestMethod.POST)
    public String addNewRequest(@RequestParam(value = "goods_name") String goodsName,
                                @RequestParam(value = "mass") Integer mass,
                                @RequestParam(value = "city1") String city1,
                                @RequestParam(value = "city2") String city2,
                                Model model) throws NullPointerException {

        int goodNumber = goodService.addNewGood(goodsName, mass);

        Rout rout = routService.getByCities(city1, city2);

        requestService.addNewRequest(goodService.read(goodNumber), rout);

        model.addAttribute("info_msg", "New request successfully created");

        return "manager/main_manager";
    }

    @ExceptionHandler
    public String handleAllExceptions(Exception ex, Model model) {
        logger.info("Exception: " + ex.getMessage());
        model.addAttribute("error_msg", "Sorry something wrong" + ex);
        return "redirect: main_manager"; //todo потестить!
    }

    /**
     * Counts amount of Special Routs (by Route Labels) in Requests. Sort these requests to lists.
     *
     * @param request
     * @return current_requests.jsp
     * @throws ServletException
     * @throws IOException
     */
    @RequestMapping(value = "pick_up_requests")
    public String pickUpRequests(HttpServletRequest request) throws NullPointerException {

        ArrayList<Request> requestsWithYellowRout = (ArrayList<Request>) requestService.findRequestsWithSpecialRout("yellow");
        ArrayList<Request> requestsWithGreenRout = (ArrayList<Request>) requestService.findRequestsWithSpecialRout("green");
        ArrayList<Request> requestsWithPurpleRout = (ArrayList<Request>) requestService.findRequestsWithSpecialRout("purple");
        ArrayList<Request> requestsWithBlueRout = (ArrayList<Request>) requestService.findRequestsWithSpecialRout("blue");

        request.getSession().setAttribute("yellowRoutRequests", requestsWithYellowRout);
        request.getSession().setAttribute("yellowRoutRequestsSize", requestsWithYellowRout.size());
        request.getSession().setAttribute("greenRoutRequests", requestsWithGreenRout);
        request.getSession().setAttribute("greenRoutRequestsSize", requestsWithGreenRout.size());
        request.getSession().setAttribute("purpleRoutRequests", requestsWithPurpleRout);
        request.getSession().setAttribute("purpleRoutRequestsSize", requestsWithPurpleRout.size());
        request.getSession().setAttribute("blueRoutRequests", requestsWithBlueRout);
        request.getSession().setAttribute("blueRoutRequestsSize", requestsWithBlueRout.size());

        return "manager/current_requests";
    }

    /**
     * Finds and Sets choosed requests , creates new order, counts order's mass, finds appropriate vans and drivers
     *
     * @param httpRequest
     * @param currentRoutLabel
     * @return create_order.jsp
     */
    @RequestMapping(value = "create_order")
    public String createOrder(Model model, HttpServletRequest httpRequest,
                              @RequestParam(value = "currentRoutLabel") String currentRoutLabel) throws NullPointerException {

        //todo если мы попали на эту страницу, но не нажали saveOrder, а решили вернуться,
        // то в базе данных у нас остаются не до конца оформленные заказы. Надо придумать как их удалять

        logger.info("Picking " + currentRoutLabel + " requests");

        ArrayList<Request> requests = new ArrayList<>();
        httpRequest.getSession().setAttribute("currentRoutLabel", currentRoutLabel);

        switch (currentRoutLabel) {
            case "yellow":
                requests = (ArrayList<Request>) httpRequest.getSession().getAttribute("yellowRoutRequests");
                break;
            case "green":
                requests = (ArrayList<Request>) httpRequest.getSession().getAttribute("greenRoutRequests");
                break;
            case "purple":
                requests = (ArrayList<Request>) httpRequest.getSession().getAttribute("purpleRoutRequests");
                break;
            case "blue":
                requests = (ArrayList<Request>) httpRequest.getSession().getAttribute("blueRoutRequests");
                break;
        }

        Order order = orderService.addNewOrder(requests);
        httpRequest.getSession().setAttribute("order", order);

        int mass = orderService.countOrderMass(requests);
        model.addAttribute("mass", mass);

        ArrayList<Van> appropriateVans = (ArrayList<Van>) vanService.getAppropriateVans(currentRoutLabel);
        httpRequest.getSession().setAttribute("appropriateVans", appropriateVans);

        int totalHoursAmount = requestService.getTotalTimeRequests(requests);
        httpRequest.getSession().setAttribute("totalHoursAmount", totalHoursAmount);

        ArrayList<Driver> appropriateDrivers = (ArrayList<Driver>) driverService.getAppropriateDrivers(totalHoursAmount);
        httpRequest.getSession().setAttribute("appropriateDrivers", appropriateDrivers);

        int maxCheckboxSelections = vanService.getDriversCapacity(currentRoutLabel) + 1;//+1 means 1 van
        httpRequest.getSession().setAttribute("maxCheckboxSelections", maxCheckboxSelections);
        return "manager/create_order";
    }

    /**
     * Changes requests statuses to FINISHED, Sets chose drivers and van to order
     *
     * @param request
     * @return main_manager.jsp
     */
    @RequestMapping(value = "save_order")
    public String saveOrder(HttpServletRequest request,
                            Model model) {
        //Changes request status to FINISHED
        requestService.changeRequestsStatuses((String) request.getSession().getAttribute("currentRoutLabel"));

        //Sets chose van to order
        String[] selectedVans = request.getParameterValues("selectedVan");
        logger.info("selectedVans.length=" + selectedVans.length);
        int idVan = Integer.valueOf(selectedVans[0]) - 1;
        logger.info("idVan" + idVan);
        Van van = vanService.getSelectedVan((List<Van>) request.getSession().getAttribute("appropriateVans"), idVan);
        logger.info("Choosed this " + van);
        vanService.changeVanStatus(van, VanStatus.BUSY);
        Order order = (Order) request.getSession().getAttribute("order");
        orderService.setVanToOrder(van, order);
        logger.info("Van is setted to order");

        //Sets chose drivers to order
        String[] selectedDriversID = request.getParameterValues("selectedDriver");
        logger.info("selectedDrivers.length=" + selectedDriversID.length);
        List<Driver> selectedDrivers = driverService.getSelectedDrivers((List<Driver>) request.getSession().getAttribute("appropriateDrivers"),
                selectedDriversID);

        //если водителю назначена заявка, то статус меняется на busy. на рабочие часы это не влияет
        driverService.changeDriversStatuses(selectedDrivers, DriverStatus.BUSY);
        Integer totalHoursAmount = (Integer) request.getSession().getAttribute("totalHoursAmount");
        driverService.setWorkHours(selectedDrivers, totalHoursAmount);
        orderService.setDriversToOrder(selectedDrivers, order);

        for (Driver d :
                order.getDrivers()) {
            logger.debug(d);
        }

        model.addAttribute("info_msg", "New order successfully created.");

        return "manager/main_manager";
    }

    /**
     * Shows current orders and you can see finished orders.
     *
     * @param request
     * @return orders_list.jsp
     */
    @RequestMapping(value = "orders_list")
    public String showOrdersList(HttpServletRequest request) {

        ArrayList<Order> ordersPROCESS = (ArrayList<Order>) orderService.getOrdersProcess();
        ArrayList<Order> ordersDONE = (ArrayList<Order>) orderService.getOrdersDone();

        logger.info("ordersPROCESS.size()= " + ordersPROCESS.size());
        logger.info("ordersDONE.size()" + ordersDONE.size());

        request.getSession().setAttribute("ordersPROCESS", ordersPROCESS);
        request.getSession().setAttribute("ordersDONE", ordersDONE);
        // TODO Можно сделать кнопку посмотреть выполненные заказы (ordersDONE) в orders_list.jsp

        return "manager/orders_list";
    }

    /**
     * Changes orderStatus to DONE.
     * Breaks links with drivers, van, requests and goods.
     * @param orderIDStr
     * @param model
     * @return main_manager.jsp
     * @throws NullPointerException
     */
    @RequestMapping(value = "finishOrder")
    public String finishOrder(@RequestParam(value = "selectedOrder") String orderIDStr,
                              Model model) throws CustomLogiwebException {

        logger.info("Selected order ID: " + orderIDStr);
        int orderID = Integer.parseInt(orderIDStr);
        Order order = orderService.read(orderID);
        order.setStatus(OrderStatus.DONE);
        orderService.update(order);

        List<Driver> busyDrivers = driverService.getBusyDrivers(orderID);
        driverService.changeDriversStatuses(busyDrivers, DriverStatus.FREE);
        driverService.changeDriversStates(busyDrivers, DriverState.WORK);
        driverService.breakLinks(busyDrivers, order);

        vanService.changeVanStatus(order.getVan(), VanStatus.WAIT);
        orderService.breakLinkWithVan(order, order.getVan());
        List<Request> requestsToDelete = requestService.breakLinks(order);
        List<Good> goodsToDelete = requestService.breakLinksWithGoods(requestsToDelete);
        requestService.deleteSomeRequests(requestsToDelete);
        goodService.deleteSomeGoods(goodsToDelete);

        model.addAttribute("info_msg", "Order successfully finished.");

        return "manager/main_manager";
    }

    /**
     * Shows all vans.
     *
     * @param request
     * @return vans.jsp
     */
    @RequestMapping(value = "vans")
    public String showVansList(HttpServletRequest request) {

        ArrayList<Van> vansList = (ArrayList<Van>) vanService.getAll();

        request.getSession().setAttribute("vansList", vansList);

        return "manager/vans";
    }

    /**
     * Gets selected van from the DB.
     *
     * @param idVanStr
     * @return editVan.jsp
     */
    @RequestMapping(value = "getVanForEdit")
    public String getVanForEdit(@RequestParam(value = "selectedVan") String idVanStr,
                                HttpServletRequest request) {

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
     * @return main_manager.jsp
     */
    @RequestMapping(value = "editVan")
    public String editVan(@RequestParam(value = "vanNumber") String vanNumber,
                          @RequestParam(value = "driversAmount") String driversAmount,
                          @RequestParam(value = "capacity") String capacity,
                          HttpServletRequest request,
                          Model model) {

        Van van = (Van) request.getSession().getAttribute("selectedVan");

        //todo проверять номер в соответствии с паттерном
        van.setVanNumber(vanNumber);
        van.setDriversAmount(Integer.valueOf(driversAmount));
        van.setCapacity(Integer.valueOf(capacity));

        vanService.update(van);

        model.addAttribute("info_msg", "Van successfully updated.");

        return "manager/main_manager";
    }

    /**
     * Deletes selected van.
     *
     * @return main_manager.jsp
     */
    @RequestMapping(value = "deleteVan")
    public String deleteVan(@RequestParam(value = "selectedVan") String idVanStr,
                            Model model) {

        Van van = vanService.read(Integer.valueOf(idVanStr));
        vanService.delete(van);

        model.addAttribute("info_msg", "Van successfully deleted.");

        return "manager/main_manager";
    }

    /**
     * Dispatches to specified jsp page where you can type new van's date.
     *
     * @return specified createVan.jsp page
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
     * @return main_manager.jsp
     */
    @RequestMapping(value = "addVan")
    public String addVan(@RequestParam(value = "vanNumber") String vanNumber,
                         @RequestParam(value = "driversAmount") String driversAmountStr,
                         @RequestParam(value = "capacity") String capacityStr,
                         @RequestParam(value = "routLabel") String routLabel,
                         Model model) {

        int driversAmount = Integer.parseInt(driversAmountStr);
        RouteLabel routeLabel = routLabelService.getByName(routLabel);

        Van van = new Van(vanNumber, driversAmount, Integer.valueOf(capacityStr), routeLabel);
        vanService.create(van);

        model.addAttribute("info_msg", "Van successfully created.");

        return "manager/main_manager";
    }

    /**
     * Shows all drivers.
     *
     * @param model
     * @return drivers.jsp
     */
    @RequestMapping(value = "drivers")
    public String showDriversList(Model model) {

        ArrayList<Driver> drivers = (ArrayList<Driver>) driverService.getAll();

        model.addAttribute("drivers", drivers);
        //todo добавить запрет на редактирование и удаление, если статус BUSY

        return "manager/drivers";
    }

    /**
     * Gets selected driver from the DB.
     *
     * @param idDriverStr
     * @return editDriver.jsp
     */
    @RequestMapping(value = "getDriverForEdit")
    public String getDriverForEdit(@RequestParam(value = "selectedDriver") String idDriverStr,
                                   HttpServletRequest request) {

        Driver driver = driverService.read(Integer.valueOf(idDriverStr));
        logger.info("selectedDriver: " + driver + ", ID=" + driver.getId());
        request.getSession().setAttribute("selectedDriver", driver);
        return "manager/editDriver";
    }

    /**
     * Sets new parameters to driver and updates it.
     *
     * @param driverName
     * @param driverSurname
     * @param request
     * @return main_manager.jsp
     */
    @RequestMapping(value = "editDriver")
    public String editDriver(@RequestParam(value = "driverName") String driverName,
                             @RequestParam(value = "driverSurname") String driverSurname,
                             HttpServletRequest request,
                             Model model) {

        Driver driver = (Driver) request.getSession().getAttribute("selectedDriver");
        driver.setName(driverName);
        driver.setSurname(driverSurname);

        driverService.update(driver);

        model.addAttribute("info_msg", "Driver successfully updated.");

        return "manager/main_manager";
    }

    /**
     * Deletes selected driver.
     *
     * @param idDriverStr
     * @return main_manager.jsp
     */
    @RequestMapping(value = "deleteDriver")
    public String deleteDriver(@RequestParam(value = "selectedDriver") String idDriverStr,
                               Model model) {


        Driver driver = driverService.read(Integer.valueOf(idDriverStr));
        logger.info("selectedDriver: " + driver + ", ID=" + driver.getId());

        Employee employee = employeeService.getEntityByEmail(driver.getEmployee().getEmail());
        //todo герман. если водителя добавить, а потом снова посмотреть список, то employee.getEmployeeId()=NULL
        //соответственно и удалить его нельзя, т.к. нулПойнтер вылетает
        //но если передеплоить, то всё ок
        logger.info("ID employee=" + employee.getEmployeeId());
        employee.setDriverFK(null);
        employeeService.update(employee);
        driverService.delete(driver);

        employeeService.delete(employee);

        model.addAttribute("info_msg", "Driver successfully deleted.");

        return "manager/main_manager";
    }

    /**
     * Dispatches to specified jsp page where you can type new driver's date.
     *
     * @return createDriver.jsp
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
     * @return main_manager.jsp
     */
    @RequestMapping(value = "addDriver")
    public String addDriver(@RequestParam(value = "driverName") String driverName,
                            @RequestParam(value = "driverSurname") String driverSurname,
                            @RequestParam(value = "email") String email,
                            @RequestParam(value = "password") String password,
                            Model model) {

        Employee employee = new Employee(email, password, POSITION.DRIVER);
        employeeService.create(employee);

        Driver driver = new Driver(driverName, driverSurname);
        driverService.create(driver);

        employee.setDriverFK(driver);

        employee.setPersonalNumber(driver.getId());
        employeeService.update(employee);

        model.addAttribute("info_msg", "Driver successfully created.");

        return "manager/main_manager";
    }
}