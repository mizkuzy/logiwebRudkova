package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Order;
import ru.tsystems.logiweb.entities.Van;
import ru.tsystems.logiweb.entities.statusesAndStates.VanStatus;

import java.util.List;

public interface VanService extends GenericService<Van, Integer> {
    List<Van> getAppropriateVans(String routLabelType);

    int getDriversCapacity(String currentRoutLabel);

    Van getSelectedVan(List<Van> vans, int idVan);

    void changeVanStatus(Van van, VanStatus status);

    /*void breakLinks(Van van, Order order);*/
}