package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.Van;

import java.util.List;

public interface DriverService extends GenericService<Driver, Integer> {
    List getAppropriateDrivers(int totalRequestAmount);

    List<Driver> getSelectedDrivers(List<Driver> drivers, String[] selectedDriversID);

    void changeDriversStatuses(List<Driver> selectedDrivers);
}
