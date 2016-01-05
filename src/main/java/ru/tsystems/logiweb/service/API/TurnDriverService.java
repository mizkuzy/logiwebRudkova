package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.TurnDriver;

public interface TurnDriverService extends GenericService<TurnDriver, Integer>{

    TurnDriver getTurnDriverByDriverNumber(Driver driverNumber);
}
