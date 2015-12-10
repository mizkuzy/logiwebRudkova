package dao.API;

import entities.Order;

public interface OrderGenericDAO extends GenericDAO<Order, Integer> {
    Order getByNumber(Integer number);
}
