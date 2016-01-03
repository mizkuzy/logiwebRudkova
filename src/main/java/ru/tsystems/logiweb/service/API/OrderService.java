package ru.tsystems.logiweb.service.API;

import ru.tsystems.logiweb.entities.Driver;
import ru.tsystems.logiweb.entities.Order;
import ru.tsystems.logiweb.entities.Request;
import ru.tsystems.logiweb.entities.Van;

import java.util.ArrayList;
import java.util.List;

public interface OrderService extends GenericService<Order,Integer>{

    ArrayList<Order> getOrdersDone();

    ArrayList<Order> getOrdersProcess();

    Order getByNumber(Integer number);


    Order addNewOrder(List<Request> request);

    int countOrderMass(List<Request> requests);

    void setVanToOrder(Van van, Order order);


    void setDriversToOrder(List<Driver> selectedDrivers, Order order);

    void breakLinkWithVan(Order order, Van van);
}
