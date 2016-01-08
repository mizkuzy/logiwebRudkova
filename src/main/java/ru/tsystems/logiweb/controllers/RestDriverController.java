package ru.tsystems.logiweb.controllers;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.TurnDriver;
import ru.tsystems.logiweb.entities.statusesAndStates.DriverState;
import ru.tsystems.logiweb.service.API.DriverService;
import ru.tsystems.logiweb.service.API.EmployeeService;
import ru.tsystems.logiweb.service.API.TurnDriverService;

import java.time.LocalDateTime;

@RequestMapping("/rest")
@RestController
public class RestDriverController {

    private Logger logger = Logger.getLogger(RestDriverController.class);

    @Autowired
    private TurnDriverService turnDriverService;
    @Autowired
    private DriverService driverService;
    @Autowired
    private EmployeeService employeeService;

    //TODO метод finishOrder доступен и через приложение для водителя

    /**
     * Sets beginning of the driver's turn.
     *
     *
     */
    @RequestMapping("/beginTurn")
    public String setBeginTurn(@RequestParam(value = "username") String username) {
        logger.info("hello from rest");
        Driver driver = employeeService.getEntityByEmail(username).getDriverFK();
        LocalDateTime beginTurnDateTime = LocalDateTime.now();
        TurnDriver turnDriver = new TurnDriver(driver.getId(), beginTurnDateTime);
        turnDriverService.create(turnDriver);

        driver.setState(DriverState.DRIVE);
        driverService.update(driver);
        return "OK";
    }

    /**
     * Sets end of the driver's turn.
     *
     * @param driver
     * @param endTurnDateTime
     */
    @RequestMapping("/endTurn")
    public void setBeginEnd(Driver driver, LocalDateTime endTurnDateTime) {

        TurnDriver turnDriver = turnDriverService.getTurnDriverByDriverNumber(driver);
        turnDriver.setEndTurn(endTurnDateTime);
        turnDriverService.update(turnDriver);

        driver.setState(DriverState.REST);
    }
}

