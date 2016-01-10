package ru.tsystems.logiweb.dao.API;

import ru.tsystems.logiweb.entities.Order;
import ru.tsystems.logiweb.exceptions.CustomLogiwebException;

public interface OrderGenericDAO extends GenericDAO<Order, Integer> {
    Order getByNumber(Integer number) throws CustomLogiwebException;
}
