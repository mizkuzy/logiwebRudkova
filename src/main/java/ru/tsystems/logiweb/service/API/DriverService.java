package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Driver;

import java.util.List;

public interface DriverService extends GenericService<Driver, Integer> {
    List getAppropriateDrivers(int totalRequestAmount);
}
