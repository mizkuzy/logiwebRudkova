package ru.tsystems.logiweb.dao.API;

import ru.tsystems.logiweb.entities.TurnDriver;
import ru.tsystems.logiweb.exceptions.CustomLogiwebException;

public interface TurnDriverDAO extends GenericDAO<TurnDriver,Integer> {

    /**
     * Gets turn driver by driver number.
     *
     * @param driverNumber
     * @return driver number
     */
    TurnDriver getTurnDriverByDriverNumber(Integer driverNumber) throws CustomLogiwebException;

}