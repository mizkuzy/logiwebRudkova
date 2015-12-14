package ru.tsystems.logiweb.dao.API;

import ru.tsystems.logiweb.entities.Order;

public interface OrderGenericDAO extends GenericDAO<Order, Integer> {
    Order getByNumber(Integer number);
}
