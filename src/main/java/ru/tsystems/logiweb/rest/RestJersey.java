package ru.tsystems.logiweb.rest;

import com.sun.jersey.spi.resource.Singleton;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;
import org.springframework.web.context.support.SpringBeanAutowiringSupport;
import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.Employee;
import ru.tsystems.logiweb.entities.TurnDriver;
import ru.tsystems.logiweb.entities.statusesAndStates.DriverState;
import ru.tsystems.logiweb.entities.statusesAndStates.POSITION;
import ru.tsystems.logiweb.service.API.DriverService;
import ru.tsystems.logiweb.service.API.EmployeeService;
import ru.tsystems.logiweb.service.API.TurnDriverService;

import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.Response;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Path("/hello")
public class RestJersey extends SpringBeanAutowiringSupport {

    @Autowired
    private TurnDriverService turnDriverService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private EmployeeService employeeService;


    private Logger logger = Logger.getLogger(RestJersey.class);

    @GET
    @Path("begin/{param}")
    public Response getMsg(@PathParam("param") String username) {

        logger.info("Hello from Jersey rest. Here is " + username);

        Driver driver = employeeService.getEntityByEmail(username).getDriverFK();
        LocalDateTime beginTurnDateTime = LocalDateTime.now();
        TurnDriver turnDriver = new TurnDriver(driver.getId(), beginTurnDateTime);
        turnDriverService.create(turnDriver);

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
    public Response setBeginTurn(@PathParam("username") String username) {

        Driver driver = employeeService.getEntityByEmail(username).getDriverFK();
        TurnDriver turnDriver = turnDriverService.getTurnDriverByDriverNumber(driver.getId());

        LocalDateTime endTurnDateTime = LocalDateTime.now();
        turnDriver.setEndTurn(endTurnDateTime);
        turnDriverService.update(turnDriver);

        driver.setState(DriverState.REST);
        driverService.update(driver);

        DateTimeFormatter f = DateTimeFormatter.ofPattern("dd.MM.YYY HH:mm");
        String endTurnDateTimeStr = endTurnDateTime.format(f);

        String output = "Hello from main app! Username: " + username + " Status set to DRIVE. EndTurnDateTime: " + endTurnDateTimeStr;
        logger.info(output);
        return Response.status(200).entity(output).build();
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