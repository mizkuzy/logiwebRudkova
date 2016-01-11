package ru.tsystems.logiweb.rest;

import com.sun.jersey.spi.resource.Singleton;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.tsystems.logiweb.entities.*;
import ru.tsystems.logiweb.entities.statusesAndStates.*;
import ru.tsystems.logiweb.exceptions.CustomLogiwebException;
import ru.tsystems.logiweb.service.API.*;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

/**
 * Rest web service Jersey.
 */
@Component
@Path("/hello")
public class RestJersey extends SpringBeanAutowiringSupport {

    @Autowired
    private TurnDriverService turnDriverService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private OrderService orderService;
    @Autowired
    private VanService vanService;
    @Autowired
    private GoodService goodService;
    @Autowired
    private RequestService requestService;

    private Logger logger = Logger.getLogger(RestJersey.class);

    @GET
    @Path("begin/{param}")
    public Response setBeginTurn(@PathParam("param") String username) {

        logger.info("Hello from Jersey rest. Here is " + username);

        Driver driver = employeeService.getEntityByEmail(username).getDriverFK();
        LocalDateTime beginTurnDateTime = LocalDateTime.now();

        TurnDriver turnDriver;

        try {
            turnDriver = turnDriverService.getTurnDriverByDriverNumber(driver.getId());
            turnDriver.setBeginTurn(beginTurnDateTime);
            turnDriver.setDriverNumber(driver.getId());
        }
        catch (CustomLogiwebException e){
            logger.info("There is now records with driver " + driver, e);
            turnDriver = new TurnDriver(driver.getId(), beginTurnDateTime);
            turnDriverService.create(turnDriver);
        }

        driver.setState(DriverState.DRIVE);
        driverService.update(driver);

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.YYY HH:mm");
        String beginTurnDateTimeStr = beginTurnDateTime.format(f);

        String output = "Hello from main app! Username: " + username + " Status set to DRIVE. BeginTurnDateTime: " + beginTurnDateTimeStr;
        logger.info(output);
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/end/{username}")
    public Response setEndTurn(@PathParam("username") String username) {

        Driver driver = employeeService.getEntityByEmail(username).getDriverFK();
        TurnDriver turnDriver = turnDriverService.getTurnDriverByDriverNumber(driver.getId());

        LocalDateTime endTurnDateTime = LocalDateTime.now();
        turnDriver.setEndTurn(endTurnDateTime);
        turnDriverService.update(turnDriver);

        driver.setState(DriverState.REST);
        driverService.update(driver);

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.YYY HH:mm");
        String endTurnDateTimeStr = endTurnDateTime.format(f);

        String output = "Hello from main app! Username: " + username + " Status set to REST. EndTurnDateTime: " + endTurnDateTimeStr;
        logger.info(output);
        return Response.status(200).entity(output).build();
    }

    @GET
    @Path("/finish_order/{username}")
    public Response finishOrder(@PathParam("username") String username) {
        String output;
        Driver driver = employeeService.getEntityByEmail(username).getDriverFK();
        Order order = driver.getCurrentOrder();
        if (order != null) {
            order.setStatus(OrderStatus.DONE);
            orderService.update(order);

            List<Driver> busyDrivers = driverService.getBusyDrivers(order.getIdOrder());
            driverService.changeDriversStatuses(busyDrivers, DriverStatus.FREE);
            driverService.changeDriversStates(busyDrivers, DriverState.WORK);
            driverService.breakLinks(busyDrivers, order);

            vanService.changeVanStatus(order.getVan(), VanStatus.WAIT);
            orderService.breakLinkWithVan(order, order.getVan());
            List<Request> requestsToDelete = requestService.breakLinks(order);
            List<Good> goodsToDelete = requestService.breakLinksWithGoods(requestsToDelete);
            requestService.deleteSomeRequests(requestsToDelete);
            goodService.deleteSomeGoods(goodsToDelete);
            output = "Order " + order.getNumber() + " is finished";
            return Response.status(200).entity(output).build();

        } else {
            output = "This driver " + driver + " doesn't have current order.";
            return Response.status(200).entity(output).build();
        }
    }

    @GET
    @Path("/validate/{username}/{password}")
    public Response validateDriver(@PathParam("username") String username,
                                   @PathParam("password") String password) {
        String output;
        boolean validationIsOK = employeeService.checkEmailAndPassword(username, password);

        if (validationIsOK) {
            output = "OK";
        } else {
            output = "NO";
        }

        return Response.status(200).entity(output).build();
    }


}