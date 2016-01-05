package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.TurnDriver;

public interface TurnDriverService {

    TurnDriver getTurnDriverByDriverNumber(Driver driverNumber);
}
