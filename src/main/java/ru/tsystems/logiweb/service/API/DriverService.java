package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.Order;
import ru.tsystems.logiweb.entities.statusesAndStates.DriverState;
import ru.tsystems.logiweb.entities.statusesAndStates.DriverStatus;

import java.util.List;

public interface DriverService extends GenericService<Driver, Integer> {
    List getAppropriateDrivers(int totalRequestAmount);

    List<Driver> getSelectedDrivers(List<Driver> drivers, String[] selectedDriversID);

    void changeDriversStatuses(List<Driver> selectedDrivers, DriverStatus driverStatus);

    List<Driver> getBusyDrivers(int order);

    void breakLinks(List<Driver> busyDrivers, Order order);

    void changeDriversStates(List<Driver> busyDrivers, DriverState work);

    void setWorkHours(List<Driver> selectedDrivers, Integer totalHoursAmount);

    /**
     * Sets work hours to zero to all drivers.
     * This method executes every 1st day every month.
     */
    void setWorksHoursToZeroAllDrivers();
}