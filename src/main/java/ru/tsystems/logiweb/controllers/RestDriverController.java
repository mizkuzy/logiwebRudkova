package ru.tsystems.logiweb.controllers;

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

@RestController
public class RestDriverController {

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
     * @param username
     * @param beginTurnDateTime
     */
    @RequestMapping("/beginTurn")
    public void setBeginTurn(@RequestParam(value = "username") String username,
                             LocalDateTime beginTurnDateTime) {

        Driver driver = employeeService.getEntityByEmail(username).getDriverFK();//TODO проверить как это работает
        beginTurnDateTime = LocalDateTime.now();//TODO удалить этустроку. значение beginTurnDateTime нужно из сессии вытаскивать
        TurnDriver turnDriver = new TurnDriver(driver.getId(), beginTurnDateTime);
        turnDriverService.create(turnDriver);

        driver.setState(DriverState.DRIVE);
        driverService.update(driver);
    }

    /**
     * Sets end of the driver's turn.
     *
     * @param driver
     * @param endTurnDateTime
     */
    public void setBeginEnd(Driver driver, LocalDateTime endTurnDateTime) {

        TurnDriver turnDriver = turnDriverService.getTurnDriverByDriverNumber(driver);
        turnDriver.setEndTurn(endTurnDateTime);
        turnDriverService.update(turnDriver);

        driver.setState(DriverState.REST);
    }
}

