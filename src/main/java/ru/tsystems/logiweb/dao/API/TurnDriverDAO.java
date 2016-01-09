package ru.tsystems.logiweb.dao.API;

import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.TurnDriver;

public interface TurnDriverDAO extends GenericDAO<TurnDriver,Integer> {

    TurnDriver getTurnDriverByDriverNumber(Integer driverNumber);

}