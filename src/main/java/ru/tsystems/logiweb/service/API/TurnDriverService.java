package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.TurnDriver;
import ru.tsystems.logiweb.exceptions.CustomLogiwebException;

public interface TurnDriverService extends GenericService<TurnDriver, Integer>{

    TurnDriver getTurnDriverByDriverNumber(Integer driverNumber) throws CustomLogiwebException;
}
